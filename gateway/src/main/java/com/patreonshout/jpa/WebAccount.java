package com.patreonshout.jpa;

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
	 * @param username is the username for the {@link WebAccount}
	 * @param password is the password for the {@link WebAccount}
	 * @return {@link HttpStatus} 200 if the registration was successful
	 * {@link HttpStatus} 409 if the account already exists
	 */
	public HttpStatus putAccount(String username, String password) {
		return webAccountRepository.putAccount(username, password);
	}

	/**
	 * Adds a social integration
	 *
	 * @param type Integration type
	 * @param data Webhook URL or access token
	 * @return
	 */
	public HttpStatus putIntegration(int webAccountId, IntegrationType type, String data) {
		return webAccountRepository.putIntegration(webAccountId, type, data);
	}
}
