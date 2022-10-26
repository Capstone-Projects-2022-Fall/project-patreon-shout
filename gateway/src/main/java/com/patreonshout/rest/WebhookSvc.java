package com.patreonshout.rest;

import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreon.resources.User;
import com.patreonshout.PSException;
import com.patreonshout.beans.PostBean;
import com.patreonshout.jpa.Posts;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.patreon.CustomPatreonAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebhookSvc extends BaseSvc {

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	WebAccountFunctions webAccountFunctions;

	@Autowired
	Posts posts;

	/**
	 * oauthClient is a communication layer to Patreon utilized for acquiring tokens when users link accounts via OAuth
	 */
	@Autowired
	private PatreonOAuth oauthClient;

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

			for (Campaign campaign : client.fetchCampaigns().get()) {
				for (PostBean post : client.fetchPosts(campaign.getId()).get()) {
//					post.setCreator_page_url(user.getFullName());
					/* TODO:
					 * Change user.getFullName() to the campaign url...  Maybe use:
					 * client.fetchCampaigns().get().get(0).getPledgeUrl()
					 */
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
