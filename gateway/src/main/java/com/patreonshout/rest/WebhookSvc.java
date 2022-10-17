package com.patreonshout.rest;

import com.patreon.PatreonOAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebhookSvc extends BaseSvc {

	@Autowired
	private PatreonOAuth oauthClient;

	@GetMapping("/webhook")
	public String WebhookReceiver(
			// Used to fetch access tokens for the session that just signed in with Patreon.
			@RequestParam(required = false, name = "code") String code,

			// Transparently appended from the state param provided in PatreonShout Client from Dev Portal
			@RequestParam(required = false, name = "state") String state
	) throws IOException {
		// OAuth
		if (code != null && state != null) {
//			try {
			PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code); // Should we handle IOException?

			//Store the refresh TokensResponse in your data store
			String accessToken = tokens.getAccessToken();
			String refreshToken = tokens.getRefreshToken();



//			} catch (HttpStatusException ex) { // Can occur when retrieving TokensResponse object if code was used already
//				System.out.println("HTTP error fetching URL");
//			} catch (IOException ex) { // Can occur when retrieving TokensResponse object
//				System.out.println("IOException error.");
//			}
		}

		// Webhook
		return "";
	}
}
