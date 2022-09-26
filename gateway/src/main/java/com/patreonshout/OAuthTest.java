package com.patreonshout;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.PatreonAPI;
import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreon.resources.User;
import org.jsoup.HttpStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class OAuthTest {

	@Lazy
	@Autowired
	private PatreonOAuth oauthClient;

	@GetMapping("/webhook")
	public String WebhookReceiver(
			// Used to fetch access tokens for the session that just signed in with Patreon.
			@RequestParam(required = false, name = "code") String code,

			// Transparently appended from the state param provided in PatreonShout Client from Dev Portal
			@RequestParam(required = false, name = "state") String state
	) {
		// OAuth
		if (code != null && state != null) {
			try {
				PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code); // Should we handle IOException?

				//Store the refresh TokensResponse in your data store
				String accessToken = tokens.getAccessToken();
				String refreshToken = tokens.getRefreshToken();

				PatreonAPI apiClient = new PatreonAPI(accessToken);
				System.out.println("APIClient successful!");
				System.out.println("Scope: " + tokens.getScope());
				System.out.println("Access: " + accessToken);
				System.out.println("Refresh: " + refreshToken);

				apiClient.fetchUser();
				// ! Campaigns stuff
				JSONAPIDocument<List<Campaign>> campaigns = apiClient.fetchCampaigns();

				StringBuilder output = new StringBuilder();

				if (campaigns.get() != null)
					for (Campaign camp : campaigns.get()) {
						output.append(camp.getSummary());
					}

				JSONAPIDocument<User> userResponse = apiClient.fetchUser();

				System.out.println("User Response successful!");

				return "Hi!";
//				User user = userResponse.get();

//				return "Name: " + user.getFullName() +
//						"<br/>Pledge Count: " + user.getPledges().size() +
//						"<br/>Campaign Count: " + apiClient.fetchCampaigns().get().size() +
//						"<br/>Refresh Token: " + refreshToken +
//						"<br/>Access Token: " + accessToken;
			} catch (
					HttpStatusException ex) { // Can occur when retrieving TokensResponse object if code was used already
				System.out.println("HTTP error fetching URL");
			} catch (IOException ex) { // Can occur when retrieving TokensResponse object
				System.out.println("IOException error.");
			}
		}

		// Webhook
		return "Hello from /webhook dev!";
	}
}
