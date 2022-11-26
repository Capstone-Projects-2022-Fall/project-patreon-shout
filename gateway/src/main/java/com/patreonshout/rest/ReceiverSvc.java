package com.patreonshout.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreonshout.PSException;
import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.SocialIntegration;
import com.patreonshout.beans.SocialIntegrationMessages;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.facebook_api.FacebookAccessToken;
import com.patreonshout.beans.facebook_api.FacebookUserPage;
import com.patreonshout.beans.facebook_api.FacebookUserPages;
import com.patreonshout.beans.patreon_api.*;
import com.patreonshout.beans.request.PutSocialIntegrationRequest;
import com.patreonshout.beans.request.receivers.patreon.WebhookRequest;
import com.patreonshout.config.credentials.InstagramCredentials;
import com.patreonshout.config.credentials.PatreonCredentials;
import com.patreonshout.config.credentials.RedditCredentials;
import com.patreonshout.config.credentials.TwitterCredentials;
import com.patreonshout.jpa.CreatorPageFunctions;
import com.patreonshout.jpa.PatreonCampaignsFunctions;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import com.patreonshout.patreon.CustomPatreonAPI;
import com.patreonshout.rest.interfaces.ReceiverImpl;
import com.patreonshout.utils.DiscordWebhookUtil;
import com.patreonshout.utils.RedditApiUtil;
import com.patreonshout.utils.TwitterApiUtil;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
	 * patreonCredentials is a Spring bean that holds our Patreon client credentials
	 */
	@Autowired
	private PatreonCredentials patreonCredentials;

	/**
	 * Spring Bean that holds our Instagram app credentials
	 */
	@Autowired
	private InstagramCredentials instagramCredentials;

	/**
	 * Spring Bean that holds our Reddit app credentials
	 */
	@Autowired
	private RedditCredentials redditCredentials;

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

			//Store the refresh TokensResponse in your data store
			String accessToken = tokens.getAccessToken();
			String refreshToken = tokens.getRefreshToken();

			// Acquire campaign data
			PatreonDataArrayEntryV2 campaign = objectMapper.convertValue(getCampaignData(accessToken), PatreonDataArrayEntryV2.class);
			PatreonCampaignV2 campaignData = objectMapper.convertValue(campaign.getAttributes(), PatreonCampaignV2.class);

			WebAccount webAccount = webAccountFunctions.getAccount(loginToken);

			// Store their creator page information
			patreonCampaignsFunctions.putCampaign(webAccount, campaign);

			// put content creator posts in database
			creatorPageFunctions.putCreatorPage(campaign.getId(), campaignData.getVanity());
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
	public String InstagramOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String loginToken) throws PSException {
		// OAuth
		if (code != null && loginToken != null) {
			// Read webaccount
			WebAccount webAccount = webAccountFunctions.getAccount(loginToken);

			if (webAccount == null)
				return "The PatreonShout account you attempted to link Instagram to was invalid!";

			FacebookAccessToken accessTokenObj;

			// * HTTP GET an access token with the given code
			try {
				accessTokenObj = WebClient
						.create("https://graph.facebook.com/oauth/")
						.get()
						.uri(uriBuilder -> uriBuilder
								.path("access_token")
								.queryParam("client_id", instagramCredentials.getClientID())
								.queryParam("client_secret", instagramCredentials.getClientSecret())
								.queryParam("redirect_uri", instagramCredentials.getRedirectUri())
								.queryParam("code", code)
								.build())
						.retrieve()
						.bodyToMono(FacebookAccessToken.class)
						.share()
						.block();
			} catch (Exception ex) {
				ex.printStackTrace();
				return "An error occurred requesting an access token from Facebook.  Is it reachable at the moment?";
			}

			if (accessTokenObj == null)
				return "An error occurred requesting an access token from Facebook.  Is it reachable at the moment?";

			// Read access token
			String accessToken = accessTokenObj.getAccessToken();

			if (accessToken == null || accessToken.isEmpty())
				return "Access token received from Facebook is invalid.";

			FacebookUserPages userPages;

			// * HTTP GET Request all Facebook Pages
			try {
				userPages = WebClient
						.create("https://graph.facebook.com/me/")
						.get()
						.uri(uriBuilder -> uriBuilder
								.path("accounts")
								.queryParam("access_token", accessToken)
								.build())
						.retrieve()
						.bodyToMono(FacebookUserPages.class)
						.share()
						.block();
			} catch (Exception ex) {
				ex.printStackTrace();
				return "An error occurred requesting Facebook Pages.  Is it reachable at the moment?";
			}

			if (userPages == null)
				return "An error occurred requesting Facebook Pages.  Is it reachable at the moment?";

			if (userPages.getDataObjects().length == 0 || userPages.getDataObjects()[0] == null)
				return "No Facebook Pages were found on your account when requesting Facebook for them.";

			// * HTTP GET Facebook Page's ig-user-id
			FacebookUserPage userPage;
			try {
				userPage = WebClient
						.create("https://graph.facebook.com/")
						.get()
						.uri(uriBuilder -> uriBuilder
								.path(userPages.getDataObjects()[0].getId())
								.queryParam("access_token", accessToken)
								.queryParam("fields", "instagram_business_account")
								.build())
						.retrieve()
						.bodyToMono(FacebookUserPage.class)
						.share()
						.block();
			} catch (Exception ex) {
				ex.printStackTrace();
				return "An error occurred requesting Facebook Page.  Is it reachable at the moment?";
			}

			if (userPage == null || userPage.getInstagramBusinessAccount() == null)
				return "An error occurred requesting the Facebook Page's Instagram Business Account.  Is it reachable at the moment?";

			// Save access token to DB
			webAccountFunctions.putSocialIntegration(loginToken, SocialIntegrationName.INSTAGRAM, accessToken + ':' + userPage.getInstagramBusinessAccount().getId());

			return "Instagram linked!  Close this pop-up and refresh the PatreonShout webpage.!";
		}

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
				.headers(httpHeaders -> {
					httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
					httpHeaders.setBasicAuth(finalBasicAuth);
				})
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JSONParser parser = new JSONParser();
		JSONObject objResponse = (JSONObject) parser.parse(response);

		PutSocialIntegrationRequest putTwitter = PutSocialIntegrationRequest.builder()
				.data(objResponse.get("access_token") + ":" + objResponse.get("refresh_token"))
				.loginToken(state)
				.socialIntegrationName(SocialIntegrationName.TWITTER)
				.build();

		webAccountFunctions.putSocialIntegration(putTwitter);

		return "Twitter linked!  Close this pop-up and refresh the PatreonShout webpage.";
	}

	/**
	 * {@inheritDoc}
	 */
	public String RedditOAuth(String code, String state) throws ParseException, PSException {

		if (code != null && state != null) {

			WebAccount webAccount = webAccountFunctions.getAccount(state);

			if (webAccount == null) {
				return "The PatreonShout account you attempted to link Reddit to was invalid!";
			}

			String basicAuth = redditCredentials.getClientID() + ":" + redditCredentials.getClientSecret();
			String finalBasicAuth = Base64.getEncoder().encodeToString(basicAuth.getBytes());

			// get the access_token and refresh_token of the user
			String response = WebClient.create("https://www.reddit.com/api/v1/access_token")
					.method(HttpMethod.POST)
					.headers(httpHeaders -> {
						httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
						httpHeaders.setBasicAuth(finalBasicAuth);
					})
					.body(BodyInserters.fromFormData("grant_type", "authorization_code").with("code", code).with("redirect_uri", redditCredentials.getRedirectUri()))
					.retrieve()
					.bodyToMono(String.class)
					.block();

			JSONParser parser = new JSONParser();
			JSONObject objResponse = (JSONObject) parser.parse(response);

			PutSocialIntegrationRequest putReddit = PutSocialIntegrationRequest.builder()
					.data(objResponse.get("access_token") + ":" + objResponse.get("refresh_token"))
					.loginToken(state)
					.socialIntegrationName(SocialIntegrationName.REDDIT)
					.build();

			webAccountFunctions.putSocialIntegration(putReddit);

			return "Reddit linked!  Close this pop-up and refresh the PatreonShout webpage";
		}


		return "Invalid parameters";
	}

	/**
	 * Creates the Patreon webhook trigger needed to get post information when a post is published
	 *
	 * @param webAccount  is the user's {@link com.patreonshout.beans.WebAccount} object
	 * @param accessToken is the access token of the user
	 * @param campaignId  is the Patreon campaign id of the user
	 */
	private void createWebhookForPatreon(WebAccount webAccount, String accessToken, int campaignId) {
		PatreonObjectV2 outputObject = new PatreonObjectV2();
		PatreonDataV2 patreonData = new PatreonDataV2();
		patreonData.setType("webhook");

		PatreonWebhookV2 patreonWebhook = new PatreonWebhookV2();
		patreonWebhook.setTriggers(new String[]{"posts:publish"});
		patreonWebhook.setUri(patreonCredentials.getRedirectUri() + "/receivers/patreon/webhook/" + webAccount.getWebAccountId());
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

		/*PatreonObjectV2 retObject = */
		WebClient
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
	 * Gets the campaign data for a content creator on patreon
	 *
	 * @param accessToken the content creator's access token, needed to gain information
	 * @return an {@link com.patreonshout.beans.patreon_api.PatreonDataV2} object
	 * @throws PSException in case there is a problem with the database or a user mismatch
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
			@PathVariable long webaccountId
	) throws PSException, ParseException {
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

		WebAccount webAccount;

		try {
			webAccount = webAccountFunctions.getAccount(webaccountId);
		} catch (PSException ex) { // * getAccount() threw an error -- webaccountId does not exist in the database!
			System.out.println("not good man");
			return new ResponseEntity<>(HttpStatus.OK);
			// TODO: Send a DELETE request to delete the webhook from Patreon
		} catch (
				Exception ex) { // TODO: Unknown exception occurred...!  Return 200 OK so Patreon keeps using this Webhook until we fix whatever is wrong
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
				.publishedAt(patreonPost.getPublishedAt())
				.title(patreonPost.getTitle())
				.url(patreonPost.getUrl())
				.build();

		postsRepository.save(postBean);

		switch (patreonEvent) {
			case "posts:publish":
				System.out.println("Received: " + patreonEvent);

				SocialIntegration integration = webAccountFunctions.getSocialIntegration(webAccount.getLoginToken());
				SocialIntegrationMessages socialIntegrationMessages = webAccountFunctions.getSocialIntegrationMessages(webAccount.getLoginToken());
				if (integration.getDiscord() != null) {
					sendDiscordMessage(integration.getDiscord(), patreonPost, socialIntegrationMessages);
				}

				if (integration.getTwitterAccessToken() != null) {
					sendTwitterPost(patreonPost, socialIntegrationMessages, webAccount);
				}

				if (integration.getRedditAccessToken() != null) {
					sendRedditPost(patreonPost, socialIntegrationMessages, webAccount);
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

	/**
	 * sends a message to Discord for a specific content creator
	 *
	 * @param patreonPost               is the post data we want to send
	 * @param socialIntegrationMessages are the messages we send along with a post for a particular user
	 * @param webhookUrl                is the Discord webhook url, used for sending a message to a specific Discord channel
	 */
	void sendDiscordMessage(String webhookUrl, PatreonPostV2 patreonPost, SocialIntegrationMessages socialIntegrationMessages) {
		// TODO: Get user's webhook urls
		new DiscordWebhookUtil(
				webhookUrl,
				patreonPost,
				socialIntegrationMessages
		).send();
	}

	/**
	 * sends a message to Twitter for a specific content creator
	 *
	 * @param patreonPost               is the post data we want to send
	 * @param webAccount                is the user we want to send a tweet for
	 * @param socialIntegrationMessages are the messages we send along with a post for a particular user
	 * @throws PSException in case of a database problem or a user mismatch
	 */
	void sendTwitterPost(PatreonPostV2 patreonPost, SocialIntegrationMessages socialIntegrationMessages, WebAccount webAccount) throws PSException {
		SocialIntegration socialIntegration = webAccount.getSocialIntegration();

		FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();

		String body = (patreonPost.getIsPublic() ? socialIntegrationMessages.getTwitterPublicMessage() : socialIntegrationMessages.getTwitterPrivateMessage());
		body = body.replaceAll("\\n", "\n");

		String postContent = converter.convert(patreonPost.getContent());
		if (postContent.substring(postContent.length() - 2).equals("\\n")) {
			postContent = postContent.substring(0, postContent.length() - 2);
		}

		body = body.replaceAll("\\{content}", postContent);
		body += " https://www.patreon.com" + patreonPost.getUrl();
//		System.out.println("body text sent: [" + body + "]");

		new TwitterApiUtil().sendTweet(twitterCredentials.getClientID(), twitterCredentials.getClientSecret(), socialIntegration.getTwitterAccessToken(), socialIntegration.getTwitterRefreshToken(), body);
	}

	void sendRedditPost(PatreonPostV2 patreonPost, SocialIntegrationMessages socialIntegrationMessages, WebAccount webAccount) throws ParseException {
		SocialIntegration socialIntegration = webAccount.getSocialIntegration();

		FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();

		String body = (patreonPost.getIsPublic() ? socialIntegrationMessages.getTwitterPublicMessage() : socialIntegrationMessages.getTwitterPrivateMessage());
		body = body.replaceAll("\\n", "\n");

		String postContent = converter.convert(patreonPost.getContent());
		if (postContent.substring(postContent.length() - 2).equals("\\n")) {
			postContent = postContent.substring(0, postContent.length() - 2);
		}

		body = body.replaceAll("\\{content}", postContent);
		body += " https://www.patreon.com" + patreonPost.getUrl();

		new RedditApiUtil().sendPost(socialIntegration.getRedditAccessToken(), body, patreonPost.getTitle());
	}
}