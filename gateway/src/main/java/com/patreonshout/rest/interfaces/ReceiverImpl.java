package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.request.receivers.patreon.WebhookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(value = "/receivers")
@Tag(name = "Receiver Service",
		description = "Handles all Webhook related functions.")
public interface ReceiverImpl {

	/**
	 * asdf
	 *
	 * @param code  is used to fetch access tokens for the session that just signed in with Patreon
	 * @param state is transparently appended from the state param provided in PatreonShout Client from Dev Portal
	 * @return a json body telling the user that their Patreon was successfully OAuth'd
	 * @throws IOException when we cannot parse the input
	 * @throws PSException when there is an internal error with Patreon Shout
	 */
	@GetMapping("/patreon/oauth")
	@Operation(summary = "")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "HTML message saying 'Patreon linked!'")
	})
	String PatreonOAuth(
			@RequestParam(required = false, name = "code") String code,
			@RequestParam(required = false, name = "state") String state
	) throws IOException, PSException;


	@PostMapping("/patreon/webhook/{webaccountId}")
	ResponseEntity<?> PatreonWebhook(
			@RequestHeader("x-patreon-signature") String patreonSignature,
			@RequestHeader("x-patreon-event") String patreonEvent,
			@RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
			@RequestBody WebhookRequest webhookRequest,
			@PathVariable String webaccountId);
}
