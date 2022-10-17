package com.patreonshout.rest;

import com.patreon.PatreonOAuth;
import com.patreonshout.jpa.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WebhookSvc extends BaseSvc {
	/**
	 * webAccount is the wrapper class for {@link com.patreonshout.jpa.WebAccountRepository}
	 */
	@Autowired
	WebAccount webAccount;

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
	) throws IOException {
		// OAuth
		if (code != null && state != null) {
			PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code); // Should we handle IOException?

			//Store the refresh TokensResponse in your data store
			String accessToken = tokens.getAccessToken();
			String refreshToken = tokens.getRefreshToken();

			webAccount.putPatreonTokens(accessToken, refreshToken, state);

			return "Patreon linked!  Close this pop-up and refresh the PatreonShout webpage.";
//			throw new PSException(HttpStatus.CREATED, "Token created");
		}

		// Webhook
		return "";
	}
}
