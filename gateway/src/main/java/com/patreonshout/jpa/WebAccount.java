package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.WebAccountBean;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.jpa.constants.IntegrationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Functions for WebAccount endpoints that allow interaction with the database
 */
@Component
public class WebAccount {

	/**
	 * webAccountRepository is the {@link WebAccountRepository} class that handles all logic regarding
	 * database connections with the webaccounts table
	 */
	@Autowired
	WebAccountRepository webAccountRepository;

	/**
	 * Attempts to add a {@link WebAccount} into the database
	 *
	 * @param registerRequest {@link RegisterRequest} object that contains the desired login details for a new
	 *                        {@link HttpStatus} 409 if the account already exists
	 */
	public void putAccount(RegisterRequest registerRequest) {
		webAccountRepository.putAccount(registerRequest);
	}

	/**
	 * Ensures a {@link WebAccount} with a matching username and password exists in the database
	 *
	 * @param loginRequest {@link LoginRequest} object that contains the desired login details to check
	 */
	public String readAccount(LoginRequest loginRequest) throws PSException {
		return webAccountRepository.readAccount(loginRequest);
	}

	/**
	 * Adds a social integration
	 *
	 * @param type Integration type
	 * @param data Webhook URL or access token
	 */
	public void putIntegration(int webAccountId, IntegrationType type, String data) {
		webAccountRepository.putIntegration(webAccountId, type, data);
	}

	/**
	 * Attempts to add Patreon access and refresh tokens into a {@link WebAccount} by checking for a matching login token
	 *
	 * @param accessToken  Patreon access token - can be null
	 * @param refreshToken Patreon refresh token - can be null
	 * @param loginToken   {@link WebAccountBean} login token
	 */
	public void putPatreonTokens(String accessToken, String refreshToken, String loginToken) {
		webAccountRepository.putPatreonTokens(accessToken, refreshToken, loginToken);
	}

	/**
	 * Attempts to acquire Patreon access and refresh tokens by checking for a matching {@link WebAccount} with the
	 * given login token
	 *
	 * @param loginToken login token belonging to a {@link WebAccount}
	 * @return {@link WebAccountBean} containing the access and refresh tokens for the {@link WebAccount} that contains
	 * the given login token
	 */
	public WebAccountBean getPatreonTokens(String loginToken) throws PSException {
		return webAccountRepository.getPatreonTokens(loginToken);
	}
}
