//package com.patreonshout;
//
//import com.patreon.PatreonAPI;
//import com.patreon.PatreonOAuth;
//import com.patreonshout.patreon.endpoints.EndpointWrapper;
//import org.jsoup.HttpStatusException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
///**
// * Testing Patreon OAuth through an endpoint
// */
//@RestController
//@RequestMapping(value = "/v1")
//public class OAuthTest {
//
//	/**
//	 * oauthClient is the {@link com.patreon.PatreonOAuth} object used for Patreon OAuth requests
//	 */
//	@Lazy
//	@Autowired
//	private PatreonOAuth oauthClient;
//
//	/**
//	 * Testing the OAuth request using an endpoint like we will be doing in Patreon Shout
//	 *
//	 * @param code is used to fetch access tokens for the session that just signed in with Patreon
//	 * @param state is transparently appended from the state param provided in PatreonShout Client from Dev Portal
//	 * @return a String containing a hello message to let the user know that the endpoint worked
//	 */
//	@GetMapping("/webhook")
//	public String WebhookReceiver(
//			// Used to fetch access tokens for the session that just signed in with Patreon.
//			@RequestParam(required = false, name = "code") String code,
//
//			// Transparently appended from the state param provided in PatreonShout Client from Dev Portal
//			@RequestParam(required = false, name = "state") String state
//	) {
//		// OAuth
//		if (code != null && state != null) {
//			try {
//				PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code); // Should we handle IOException?
//
//				//Store the refresh TokensResponse in your data store
//				String accessToken = tokens.getAccessToken();
//				String refreshToken = tokens.getRefreshToken();
//
//				PatreonAPI apiClient = new PatreonAPI(accessToken);
//
//				StringBuilder output = new StringBuilder();
//
//				output.append("APIClient successful!<br/>")
//						.append("<b>Scope</b>: ").append(tokens.getScope()).append("<br/>")
//						.append("<b>Access</b>: ").append(accessToken).append("<br/>")
//						.append("<b>Refresh</b>: ").append(refreshToken).append("<br/>")
//						.append(EndpointWrapper.fetchUser(accessToken).toString());
//
//				return output.toString();
//			} catch (
//					HttpStatusException ex) { // Can occur when retrieving TokensResponse object if code was used already
//				System.out.println("HTTP error fetching URL");
//			} catch (IOException ex) { // Can occur when retrieving TokensResponse object
//				System.out.println("IOException error.");
//			}
//		}
//
//		// Webhook
//		return "Hello from /webhook dev!";
//	}
//}
