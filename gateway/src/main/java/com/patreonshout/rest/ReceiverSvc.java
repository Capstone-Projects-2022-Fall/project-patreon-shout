package com.patreonshout.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreonshout.PSException;
import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.SocialIntegration;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.patreon_api.*;
import com.patreonshout.beans.request.PutSocialIntegrationRequest;
import com.patreonshout.beans.request.receivers.patreon.WebhookRequest;
import com.patreonshout.config.credentials.TwitterCredentials;
import com.patreonshout.jpa.CreatorPageFunctions;
import com.patreonshout.jpa.PatreonCampaignsFunctions;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import com.patreonshout.patreon.CustomPatreonAPI;
import com.patreonshout.rest.interfaces.ReceiverImpl;
import com.patreonshout.utils.DiscordWebhookUtil;
import com.patreonshout.utils.TwitterApiUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Patreon Webhook RESTful Endpoint Interface
 */
@RestController
public class ReceiverSvc extends BaseSvc implements ReceiverImpl {

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	WebAccountFunctions webAccountFunctions;

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	CreatorPageFunctions creatorPageFunctions;

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	PatreonCampaignsFunctions patreonCampaignsFunctions;

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	PostsRepository postsRepository;

	/**
	 * oauthClient is a communication layer to Patreon utilized for acquiring tokens when users link accounts via OAuth
	 */
	@Autowired
	private PatreonOAuth oauthClient;

	/**
	 * twitterCredentials is a Spring bean that holds our Twitter app credentials
	 */
	@Autowired
	private TwitterCredentials twitterCredentials;

	/**
	 * Jackson object mapper that allows converting Java type {@link Object} to custom POJOs.
	 */
	@Autowired
	ObjectMapper objectMapper;


	/**
	 * {@inheritDoc}
	 */
	public String PatreonOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String loginToken
	) throws IOException, PSException {
		// OAuth
		if (code != null && loginToken != null) {
			PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code); // Should we handle IOException?
//					new PatreonOAuth.TokensResponse("Owt7HgouCPfQnXqcudbZes8wKj0IbG",
//							"7cd1bd26e1fd771d002c48688acecf61b96ae392",
//							1,
//							"1",
//							 "");


			//Store the refresh TokensResponse in your data store
			String accessToken = tokens.getAccessToken();
			String refreshToken = tokens.getRefreshToken();

			// put content creator posts in database
//			CustomPatreonAPI client = new CustomPatreonAPI(accessToken);

			// Acquire campaign data
			PatreonDataArrayEntryV2 campaign = objectMapper.convertValue(getCampaignData(accessToken), PatreonDataArrayEntryV2.class);

			System.out.println("");

			WebAccount webAccount = webAccountFunctions.getAccount(loginToken);

			// Store their creator page information
			patreonCampaignsFunctions.putCampaign(webAccount, campaign);

			// put content creator posts in database
			creatorPageFunctions.putCreatorPage(campaign.getId());
			saveCampaignPosts(accessToken, campaign.getId());

			System.out.println("Access token: " + accessToken);
			// Put Patreon tokens in database
			webAccountFunctions.putPatreonTokens(loginToken, accessToken, refreshToken);

			// Send Patreon a POST request to create a webhook for this user's campaign
			createWebhookForPatreon(webAccount, accessToken, campaign.getId());

			return "Patreon linked!  Close this pop-up and refresh the PatreonShout webpage.";
		}

		// Unknown case, but required for compilation
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	public String TwitterOAuth(String code, String state) throws ParseException, PSException {

		String basicAuth = twitterCredentials.getClientID() + ":" + twitterCredentials.getClientSecret();
		String finalBasicAuth = Base64.getEncoder().encodeToString(basicAuth.getBytes());

		// get the access_token and refresh_token of the user
		String response = WebClient.create("https://api.twitter.com/2/oauth2/")
				.method(HttpMethod.POST)
				.uri(uriBuilder -> uriBuilder.path("token").queryParam("code", code).queryParam("grant_type", "authorization_code").queryParam("redirect_uri", twitterCredentials.getRedirectUri()).queryParam("code_verifier", "challenge").build())
				.headers(httpHeaders -> {httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded")); httpHeaders.setBasicAuth(finalBasicAuth);})
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JSONParser parser = new JSONParser();
		JSONObject objResponse = (JSONObject) parser.parse(response);

		PutSocialIntegrationRequest putTwitter = new PutSocialIntegrationRequest();
		putTwitter.setData(objResponse.get("access_token") + ":" + objResponse.get("refresh_token"));
		putTwitter.setLoginToken(state);
		putTwitter.setSocialIntegrationName(SocialIntegrationName.TWITTER);

		webAccountFunctions.putSocialIntegration(putTwitter);

		return "Twitter linked!  Close this pop-up and refresh the PatreonShout webpage.";
	}

	/**
	 * TODO
	 *
	 * @param webAccount
	 * @param accessToken
	 * @param campaignId
	 */
	private void createWebhookForPatreon(WebAccount webAccount, String accessToken, int campaignId) {
		PatreonObjectV2 outputObject = new PatreonObjectV2();
		PatreonDataV2 patreonData = new PatreonDataV2();
		patreonData.setType("webhook");

		PatreonWebhookV2 patreonWebhook = new PatreonWebhookV2();
		patreonWebhook.setTriggers(new String[]{"posts:publish", "posts:update", "posts:delete"});
		patreonWebhook.setUri("https://ayser.backend.outofstonk.com/receivers/patreon/webhook/" + webAccount.getWebAccountId());
		patreonData.setAttributes(patreonWebhook);

		PatreonRelationshipsV2 patreonRelationships = new PatreonRelationshipsV2();
		PatreonCampaignV2 patreonCampaign = new PatreonCampaignV2();
		PatreonDataV2 patreonCampaignData = new PatreonDataV2();
		patreonCampaignData.setType("campaign");
		patreonCampaignData.setId(campaignId);

		patreonCampaign.setData(patreonCampaignData);
		patreonRelationships.setCampaign(patreonCampaign);
		patreonData.setRelationships(patreonRelationships);
		outputObject.setData(patreonData);

		PatreonObjectV2 retObject = WebClient
				.create("https://www.patreon.com/api/oauth2/v2/")
				.post()
				.uri("webhooks")
				.contentType(MediaType.APPLICATION_JSON)
				.headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
				.body(BodyInserters.fromValue(outputObject))
				.retrieve()
				.bodyToMono(PatreonObjectV2.class)
				.block();
	}

	/**
	 * TODO
	 *
	 * @param accessToken
	 * @return
	 * @throws PSException
	 */
	private Object getCampaignData(String accessToken) throws PSException {
		/*
			 TODO:
			    Fix this!!  This is IMPROPER!
			    This patch resides here to allow others to work on extra functionality
			 */
		// Send GET request to Patreon v2 web API
		PatreonObjectArrayV2 patreonURL = WebClient
				.create("https://www.patreon.com/api/oauth2/v2/")
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("campaigns")
						.queryParam("fields[campaign]", "vanity")
						.build())
				.headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(PatreonObjectArrayV2.class)
				.share()
				.block();

		if (patreonURL == null)
			throw new PSException(HttpStatus.BAD_REQUEST, "An error occurred while retrieving Patreon page URL for this user.");

		if (patreonURL.getData() == null || patreonURL.getData().length == 0)
			throw new PSException(HttpStatus.BAD_REQUEST, "Successfully retrieved Patreon Page URL object, but it was empty");
		// * End of TODO

		return objectMapper.convertValue(patreonURL.getData()[0], PatreonDataV2.class); //campaignData;
	}

	/**
	 * fetches posts from patreon and saves them in the database
	 *
	 * @param accessToken Patreon access token for a creator
	 * @param campaignId  Patreon creator's campaign ID
	 */
	public void saveCampaignPosts(String accessToken, int campaignId) throws IOException {
		CustomPatreonAPI client = new CustomPatreonAPI(accessToken);

		for (Campaign campaign : client.fetchCampaigns().get()) {

			List<PostBean> pbList = client.fetchPosts(campaign.getId()).get();
			List<PostBean> existingPosts = postsRepository.getExistingPosts(pbList);
			pbList.removeAll(existingPosts);

			for (PostBean post : pbList) {
				post.setCampaignId(campaignId);
				System.out.println("p: " + post);

				postsRepository.save(post);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> PatreonWebhook(
			@RequestHeader("x-patreon-signature") String patreonSignature,
			@RequestHeader("x-patreon-event") String patreonEvent,
			@RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
			@RequestBody WebhookRequest webhookRequest,
			@PathVariable String webaccountId
	) throws PSException {
		// Ensure someone that isn't Patreon is hitting this endpoint
		if (!userAgent.equals("Patreon HTTP Robot")) {
			// TODO: Log the user agent for whoever hit this endpoint
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		/*
		TODO:
		   Note that there will be a X-Patreon-Signature header, which is the HEX digest of the message body HMAC
		   signed (with MD5) using your webhook's secret. We suggest you use this to verify authenticity of the
		   webhook event. Webhook secrets should not be shared.
		 */

		WebAccount webAccount = null;

		try {
			webAccount = webAccountFunctions.getAccount(webaccountId);
		} catch (PSException ex) { // * getAccount() threw an error -- webaccountId does not exist in the database!
			// TODO: Send a DELETE request to delete the webhook from Patreon
		} catch (Exception ex) { // TODO: Unknown exception occurred...!  Return 200 OK so Patreon keeps using this Webhook until we fix whatever is wrong
			ex.printStackTrace();
			System.out.println("Unknown exception occurred");
			return new ResponseEntity<>(HttpStatus.OK);
		}

		// * Initiate post creation
		PatreonDataV2 patreonData;
		PatreonPostV2 patreonPost;

		// Convert the data attribute to a Patreon Post POJO
		try {
			patreonData = objectMapper.convertValue(webhookRequest.getData(), PatreonDataV2.class);
			patreonPost = objectMapper.convertValue(patreonData.getAttributes(), PatreonPostV2.class);
		} catch (
				Exception e) { // * We want to catch these Exceptions and return 200 OK because if we time out 3 times, Patreon will stop using our webhook.
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.OK);
		}

		// * Save a converted Post object to the database
		PostBean postBean = PostBean.builder()
				.appId(patreonPost.getAppId())
				.appStatus(patreonPost.getAppStatus())
				.content(patreonPost.getContent())
				.campaignId(patreonData.getRelationships().getCampaign().getData().getId())
				.embedData(patreonPost.getEmbedData())
				.embedUrl(patreonPost.getEmbedUrl())
				.isPaid(patreonPost.getIsPaid())
				.isPublic(patreonPost.getIsPublic())
				.publishDate(patreonPost.getPublishedAt())
				.title(patreonPost.getTitle())
				.url(patreonPost.getUrl())
				.build();

		postsRepository.save(postBean);

		switch (patreonEvent) {
			case "posts:publish":
				System.out.println("Received: " + patreonEvent);

				String integration;
				if ((integration = webAccount.getSocialIntegration().getDiscord()) != null) {
					sendDiscordMessage(patreonPost, integration);
				}
				if (webAccount.getSocialIntegration().getTwitterAccessToken() != null) {
					sendTwitterPost(patreonPost, webAccount);
				}

				break;
			case "posts:update":
				break;
			case "posts:delete":
				System.out.println("Received: " + patreonEvent);
				break;
			default:
				System.out.println("RECEIVED UNKNOWN EVENT: " + patreonEvent);
				break;
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	void sendDiscordMessage(PatreonPostV2 patreonPost, String webhookUrl) {
		// TODO: Get user's webhook urls
		new DiscordWebhookUtil(
				webhookUrl,
				patreonPost
		).send();
	}

	void sendTwitterPost(PatreonPostV2 patreonPost, WebAccount webAccount) throws PSException {
		SocialIntegration socialIntegration = webAccount.getSocialIntegration();
		String body = "I just made a new Patreon Post!\n" + patreonPost.getTitle() + ": " + (patreonPost.getIsPublic() ? patreonPost.getContent() : "This post is private, go to the post to see more");

		new TwitterApiUtil().sendTweet(twitterCredentials.getClientID(), twitterCredentials.getClientSecret(), socialIntegration.getTwitterAccessToken(), socialIntegration.getTwitterRefreshToken(), body);
	}
}