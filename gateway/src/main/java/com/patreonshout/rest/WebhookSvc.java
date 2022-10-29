package com.patreonshout.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreon.resources.User;
import com.patreonshout.PSException;
import com.patreonshout.beans.PostBean;
import com.patreonshout.jpa.CreatorPageFunctions;
import com.patreonshout.jpa.Posts;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.patreon.CustomPatreonAPI;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

/**
 * Patreon Webhook RESTful Endpoint Interface
 */
@RestController
public class WebhookSvc extends BaseSvc {

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
	Posts posts;

	/**
	 * oauthClient is a communication layer to Patreon utilized for acquiring tokens when users link accounts via OAuth
	 */
	@Autowired
	private PatreonOAuth oauthClient;

	/**
	 *
	 *
	 * @param code is used to fetch access tokens for the session that just signed in with Patreon
	 * @param state is transparently appended from the state param provided in PatreonShout Client from Dev Portal
	 * @return a json body telling the user that their Patreon was successfully OAuth'd
	 * @throws IOException when we cannot parse the input
	 * @throws PSException when there is an internal error with Patreon Shout
	 */
	@GetMapping("/webhook")
	public String WebhookReceiver(
			// Used to fetch access tokens for the session that just signed in with Patreon.
			@RequestParam(required = false, name = "code") String code,

			// Transparently appended from the state param provided in PatreonShout Client from Dev Portal
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


			User user = client.fetchUser().get();

			/**
			 * TODO: Fix this!!  This is IMPROPER!
			 * This patch resides here to allow others to work on extra functionality
			 */
			// Send GET request to Patreon v2 web API
			String baseUrl = "https://www.patreon.com/api/oauth2/v2/";

			PatreonURL patreonURL = WebClient
					.create(baseUrl)
					.get()
					.uri(uriBuilder -> uriBuilder
							.path("campaigns")
							.queryParam("fields[campaign]", "vanity")
							.build())
					.headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
					.retrieve()
					.bodyToMono(PatreonURL.class)
					.share()
					.block();

			if (patreonURL == null)
				throw new PSException(HttpStatus.BAD_REQUEST, "An error occurred while retrieving Patreon page URL for this user.");

			PatreonURL.Data[] finalPatreonUrl = patreonURL.getData();

			if (finalPatreonUrl.length == 0)
				throw new PSException(HttpStatus.BAD_REQUEST, "Successfully retrieved Patreon Page URL object, but it was empty");

			String pageUrl = finalPatreonUrl[0].getAttributes().getVanity();

			// Store their creator page information
			creatorPageFunctions.putCreatorPage(pageUrl);

			for (Campaign campaign : client.fetchCampaigns().get()) {

				List<PostBean> pbList = client.fetchPosts(campaign.getId()).get();
				List<PostBean> existingPosts = posts.getExistingPosts(pbList);
				pbList.removeAll(existingPosts);

				for (PostBean post : pbList) {
					post.setCreator_page_url(pageUrl);
					System.out.println("p: " + post);

					posts.putPost(post);
				}
			}

			// put patreon tokens in database
			webAccountFunctions.putPatreonTokens(accessToken, refreshToken, state);

			return "Patreon linked!  Close this pop-up and refresh the PatreonShout webpage.";
//			throw new PSException(HttpStatus.CREATED, "Token created");
		}

		// Webhook
		return "";
	}
}

/**
 * Class to store the data received from Patreon sending us an http request
 */
@Data
class PatreonURL {

	@JsonProperty("data")
	Data[] data;

	@lombok.Data
	static class Data {

		@JsonProperty("attributes")
		Attributes attributes;

		@lombok.Data
		static class Attributes {

			@JsonProperty("vanity")
			String vanity;
		}
	}
}
