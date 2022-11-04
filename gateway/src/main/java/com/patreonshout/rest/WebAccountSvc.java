package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.PatreonTokens;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.beans.request.ResetPasswordRequest;
import com.patreonshout.beans.request.SocialIntegrationRequest;
import com.patreonshout.beans.response.LoginResponse;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.WebAccountImpl;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
@CrossOrigin
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
	public HttpStatus Integration(@RequestBody SocialIntegrationRequest socialIntegrationRequest) throws PSException {
		webAccountFunctions.putSocialIntegration(socialIntegrationRequest);
		return HttpStatus.OK;
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