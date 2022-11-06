package com.patreonshout.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreonshout.PSException;
import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.patreon_api.PatreonCampaignV2;
import com.patreonshout.beans.patreon_api.PatreonPostV2;
import com.patreonshout.beans.request.receivers.patreon.WebhookRequest;
import com.patreonshout.jpa.CreatorPageFunctions;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.utils.DiscordWebhookUtil;
import com.patreonshout.patreon.CustomPatreonAPI;
import com.patreonshout.rest.interfaces.ReceiverImpl;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
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
	PostsRepository postsRepository;

	/**
	 * oauthClient is a communication layer to Patreon utilized for acquiring tokens when users link accounts via OAuth
	 */
	@Autowired
	private PatreonOAuth oauthClient;

	/**
	 * Jackson object mapper that allows converting Java type {@link Object} to custom POJOs.
	 */
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * TODO
	 */
	FlexmarkHtmlConverter converter = FlexmarkHtmlConverter.builder().build();

	/**
	 * {@inheritDoc}
	 */
	public String PatreonOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String state
	) throws IOException, PSException {
		// OAuth
		if (code != null && state != null) {
			PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code); // Should we handle IOException?

			//Store the refresh TokensResponse in your data store
			String accessToken = tokens.getAccessToken();
			String refreshToken = tokens.getRefreshToken();

			// put content creator posts in database
			CustomPatreonAPI client = new CustomPatreonAPI(accessToken);

			/*
			 TODO:
			    Fix this!!  This is IMPROPER!
			    This patch resides here to allow others to work on extra functionality
			 */
			// Send GET request to Patreon v2 web API
			String baseUrl = "https://www.patreon.com/api/oauth2/v2/";

			PatreonCampaignV2 patreonURL = WebClient
					.create(baseUrl)
					.get()
					.uri(uriBuilder -> uriBuilder
							.path("campaigns")
							.queryParam("fields[campaign]", "vanity")
							.build())
					.headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
					.retrieve()
					.bodyToMono(PatreonCampaignV2.class)
					.share()
					.block();

			if (patreonURL == null)
				throw new PSException(HttpStatus.BAD_REQUEST, "An error occurred while retrieving Patreon page URL for this user.");

			PatreonCampaignV2.Data[] finalPatreonUrl = patreonURL.getData();

			if (finalPatreonUrl.length == 0)
				throw new PSException(HttpStatus.BAD_REQUEST, "Successfully retrieved Patreon Page URL object, but it was empty");

			// * End of TODO

			String pageUrl = finalPatreonUrl[0].getAttributes().getVanity();

			// Store their creator page information
			creatorPageFunctions.putCreatorPage(pageUrl);

			for (Campaign campaign : client.fetchCampaigns().get()) {

				List<PostBean> pbList = client.fetchPosts(campaign.getId()).get();
				List<PostBean> existingPosts = postsRepository.getExistingPosts(pbList);
				pbList.removeAll(existingPosts);

				for (PostBean post : pbList) {
					post.setCreator_page_url(pageUrl);
					System.out.println("p: " + post);

					postsRepository.putPost(post);
				}
			}

			// Put Patreon tokens in database
			webAccountFunctions.putPatreonTokens(accessToken, refreshToken, state);

			return "Patreon linked!  Close this pop-up and refresh the PatreonShout webpage.";
		}

		// Unknown case, but required for compilation
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> PatreonWebhook(
			@RequestHeader("x-patreon-signature") String patreonSignature,
			@RequestHeader("x-patreon-event") String patreonEvent,
			@RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
			@RequestBody WebhookRequest webhookRequest
	) {
		// Ensure someone that isn't Patreon is hitting this endpoint
		if (!userAgent.equals("Patreon HTTP Robot"))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		/*
		TODO:
		   Note that there will be a X-Patreon-Signature header, which is the HEX digest of the message body HMAC
		   signed (with MD5) using your webhook's secret. We suggest you use this to verify authenticity of the
		   webhook event. Webhook secrets should not be shared.
		 */

		// Initiate post creation
		DiscordWebhookUtil discordWebhookUtil = new DiscordWebhookUtil("https://discord.com/api/webhooks/1038528862289657906/zSVQAw3DI3AYdBVAL1BgQnD8lAavFvsZ-BItnQgrqH82XyfxuZQwRXSjA0cjPRK0-xCs");
		PatreonPostV2 patreonPost;
		String content = "";

		switch(patreonEvent) {
			case "posts:publish":
				System.out.println("Received: " + patreonEvent);
				break;
			case "posts:update":
				// Convert the data attribute to a Patreon Post POJO
				try {
					patreonPost = objectMapper.convertValue(webhookRequest.getData().getAttributes(), PatreonPostV2.class);
				} catch (Exception e) {
					e.printStackTrace();
					// * We want to catch these Exceptions and return 200 OK because if we time out 3 times, Patreon will stop using our webhook.
					return new ResponseEntity<>(HttpStatus.OK);
				}


				content += converter.convert(patreonPost.getContent());

				discordWebhookUtil.addField(patreonPost.getTitle(), content);
				discordWebhookUtil.setColor(patreonPost.getIsPublic() ? 0x00FF00 : 0xFF0000);
				discordWebhookUtil.setTitle(patreonPost.getTitle());

				discordWebhookUtil.send();
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
}