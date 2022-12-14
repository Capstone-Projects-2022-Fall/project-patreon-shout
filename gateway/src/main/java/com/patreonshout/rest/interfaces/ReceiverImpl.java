package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.request.receivers.patreon.WebhookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(value = "/receivers")
@Tag(name = "Receiver Service",
		description = "Handles all Webhook related functions.")
public interface ReceiverImpl {

	/**
	 * Is called by Patreon in order to give us information for linking a user's account via OAuth
	 *
	 * @param code  is used to fetch access tokens for the session that just signed in with Patreon
	 * @param state is transparently appended from the state param provided in PatreonShout Client from Dev Portal
	 * @return a json body telling the user that their Patreon was successfully OAuth'd
	 * @throws IOException when we cannot parse the input
	 * @throws PSException when there is an internal error with Patreon Shout
	 */
	@GetMapping("/patreon/oauth")
	@Operation(summary = "Receives information from Patreon after OAuth")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "HTML message saying 'Patreon linked!'")
	})
	String PatreonOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String state
	) throws IOException, PSException;

	/**
	 * Is called by Facebook/Instagram in order to give us information for linking a user's account via OAuth
	 *
	 * @param code  is used to fetch access tokens for the session that just signed in with Patreon
	 * @param state is transparently appended from the state param provided in PatreonShout Client from Dev Portal
	 * @return a json body telling the user that their Patreon was successfully OAuth'd
	 * @throws PSException when there is an internal error with Patreon Shout
	 */
	@GetMapping("/instagram/oauth")
	@Operation(summary = "Receives information from Facebook/Instagram after OAuth")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "HTML message saying 'Facebook Page/Instagram linked!'")
	})
	String InstagramOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String state
	) throws PSException;

	/**
	 * Is called by Twitter in order to give us information for linking a user's account via OAuth
	 *
	 * @param code is used to fetch access tokens for the session that just signed in with Patreon
	 * @param state is transparently appended from the state param provided in PatreonShout Client from Dev Portal
	 * @return a json body telling the user that their Twitter was successfully OAuth'd
	 */
	@GetMapping("twitter/oauth")
	@Operation(summary = "Receives information from Twitter after OAuth")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "HTML message saying 'Twitter linked!'"
			)
	})
	String TwitterOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String state
	) throws ParseException, PSException;

	/**
	 * Is called by Reddit in order to give us information for linking a user's account via OAuth
	 *
	 * @param code is used to fetch access tokens for the session that just signed in with Reddit
	 * @param state is transparently appended from the state param provided in PatreonShout Client from Reddit dev page
	 * @return a json body telling the user that their Reddit was succesfully OAuth'd
	 */
	@GetMapping("reddit/oauth")
	@Operation(summary = "Receives information from Twitter after OAuth")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "HTML message telling the user that Reddit is linked"
			)
	})
	String RedditOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String state
	) throws ParseException, PSException;

	/**
	 * Receives post information from Patreon after a post is either published, updated, or deleted, initiates cross posting
	 *
	 * @param patreonSignature is the HEX digest of the message body HMAC signed (with MD5) using your webhook's secret
	 * @param patreonEvent is the event happening for the trigger request
	 * @param userAgent is the user agent of the Patreon request
	 * @param webhookRequest is the webhook request from Patreon
	 * @param webaccountId is the user's web account id for the request
	 * @return {@link org.springframework.http.HttpStatus#OK} if successful
	 */
	@PostMapping("/patreon/webhook/{webaccountId}")
	@Operation(summary = "Receives post information from Patreon after a post is either published, updated, or deleted")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "none (patreon doesn't care)")
	})
	ResponseEntity<?> PatreonWebhook(
			@RequestHeader("x-patreon-signature") String patreonSignature,
			@RequestHeader("x-patreon-event") String patreonEvent,
			@RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
			@RequestBody WebhookRequest webhookRequest,
			@PathVariable long webaccountId) throws PSException, ParseException;
}
