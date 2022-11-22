package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.PatreonTokens;
import com.patreonshout.beans.SocialIntegration;
import com.patreonshout.beans.SocialIntegrationMessages;
import com.patreonshout.beans.request.*;
import com.patreonshout.beans.response.LoginResponse;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.WebAccountImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Web Account  RESTful Endpoint Interface
 *
 * <p>
 * Responsibilities:
 * 1) Add items to our webaccounts table in our database
 * 2) Direct communication between the frontend portion of our application with the backend
 * </p>
 */
@RestController
public class WebAccountSvc extends BaseSvc implements WebAccountImpl {

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	WebAccountFunctions webAccountFunctions;

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus Register(@RequestBody RegisterRequest registerRequest) throws PSException {
		// TODO: Ensure username and password are sanitized and fit specific requirements
		webAccountFunctions.putAccount(registerRequest);

		return HttpStatus.CREATED; // Http 201
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest) throws PSException {
		String loginToken = webAccountFunctions.readAccount(loginRequest);

		return new ResponseEntity<>(new LoginResponse(loginToken), HttpStatus.CREATED);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> Logout(@RequestParam(name = "login_token") String loginToken) {
		webAccountFunctions.deleteLoginToken(loginToken);

		return ResponseUtil.Generic(HttpStatus.OK, "Token deleted if it existed.");
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus PutSocialIntegration(@RequestBody PutSocialIntegrationRequest putSocialIntegrationRequest) throws PSException {
		webAccountFunctions.putSocialIntegration(putSocialIntegrationRequest);
		return HttpStatus.OK;
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> GetSocialIntegration(@RequestParam(name = "login_token") String loginToken) throws PSException {
		SocialIntegration socialIntegration = webAccountFunctions.getSocialIntegration(loginToken);

		Map<String, String> response = new LinkedHashMap<>();

		response.put("discord", socialIntegration.getDiscord());
		response.put("twitter_access_token", socialIntegration.getTwitterAccessToken());
		response.put("twitter_refresh_token", socialIntegration.getTwitterRefreshToken());
		response.put("instagram", socialIntegration.getInstagram());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	public HttpStatus PutSocialIntegrationMessages(@RequestBody PutSocialIntegrationMessageRequest putSocialIntegrationMessageRequest) throws PSException {
		webAccountFunctions.putSocialIntegrationMessages(putSocialIntegrationMessageRequest);
		return HttpStatus.OK;
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> GetSocialIntegrationMessages(@RequestParam(name = "login_token") String loginToken) throws PSException {
		SocialIntegrationMessages socialIntegrationMessages = webAccountFunctions.getSocialIntegrationMessage(loginToken);
		return new ResponseEntity<>(socialIntegrationMessages, HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> GetPatreonTokens(@RequestParam(name = "login_token") String loginToken) throws PSException {
		PatreonTokens tokens = webAccountFunctions.getPatreonTokens(loginToken);

		Map<String, String> response = new HashMap<>();
		response.put("access", tokens.getAccessToken());
		response.put("refresh", tokens.getRefreshToken());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<?> ResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws PSException {
		webAccountFunctions.putNewPassword(
				resetPasswordRequest.getLoginToken(),
				resetPasswordRequest.getCurrentPassword(),
				resetPasswordRequest.getNewPassword());

		Map<String, String> response = new HashMap<>();
		response.put("success", "true");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}