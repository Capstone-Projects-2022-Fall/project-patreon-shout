package com.patreonshout.jpa;

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
	 * webAccountRepository is the {@link com.patreonshout.jpa.WebAccountRepository} class that handles all logic regarding
	 * database connections with the webaccounts table
	 */
	@Autowired
	WebAccountRepository webAccountRepository;

	/**
	 * Attempts to add a {@link WebAccount} into the database
	 *
	 * @param registerRequest {@link RegisterRequest} object that contains the desired login details for a new
	 * {@link HttpStatus} 409 if the account already exists
	 */
	public void putAccount(RegisterRequest registerRequest) {
		webAccountRepository.putAccount(registerRequest);
	}

	/**
	 * Attempts to acquire a token by checking for a matching {@link WebAccount} with the given username and password
	 * in the database
	 *
	 * @param loginRequest {@link LoginRequest} object that contains the desired login details to check
	 */
	public void readAccount(LoginRequest loginRequest) {
		webAccountRepository.readAccount(loginRequest);
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
}
