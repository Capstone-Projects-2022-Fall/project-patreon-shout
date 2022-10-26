package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorTokensBean;
import com.patreonshout.beans.WebAccountBean;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.config.SecurityConfiguration;
import com.patreonshout.jpa.constants.IntegrationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Functions for WebAccount endpoints that allow interaction with the database
 */
@Component
public class WebAccountFunctions {

	/**
	 * webAccountRepository is the {@link OldWebAccountFunctions} class that handles all logic regarding
	 * database connections with the webaccounts table
	 */
	@Autowired
	OldWebAccountFunctions oldWebAccountFunctions;

	/**
	 * webAccountRepository is the {@link OldWebAccountFunctions} class that handles all logic regarding
	 * database connections with the webaccounts table
	 */
	@Autowired
	NewWebAccountRepository newWebAccountRepository;

	/**
	 * securityConfiguration is the {@link SecurityConfiguration} that handles encrypting password with
	 * {@link BCryptPasswordEncoder} hashing
	 */
	@Autowired
	SecurityConfiguration securityConfiguration;

	/**
	 * Adds a {@link WebAccountFunctions} to the database
	 *
	 * @param registerRequest {@link RegisterRequest} object that contains the desired login details for a new
	 *                        {@link HttpStatus} 409 if the account already exists
	 */
	public void putAccount(RegisterRequest registerRequest) {
		WebAccountBean webAccountBean = new WebAccountBean();
		String salt = securityConfiguration.createSalt();

		webAccountBean.setUsername(registerRequest.getUsername());
		webAccountBean.setPassword(securityConfiguration.encodePassword(registerRequest.getPassword(), salt));
		webAccountBean.setPasswordSalt(salt);

		newWebAccountRepository.save(webAccountBean);
	}

	/**
	 * Adds a login token to a {@link WebAccountFunctions} matching the given username in the database
	 *
	 * @param loginRequest {@link LoginRequest} object that contains the desired login details to check
	 */
	public String readAccount(LoginRequest loginRequest) throws PSException {
		WebAccountBean webAccountBean = newWebAccountRepository.findByUsername(loginRequest.getUsername());

		// Username does not exist.
		if (webAccountBean == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Username does not exist.");

		// Username exists, check if given password matches.
		if (!securityConfiguration.passwordMatches(loginRequest.getPassword(), webAccountBean.getPasswordSalt(), webAccountBean.getPassword()))
			throw new PSException(HttpStatus.UNAUTHORIZED, "Incorrect password.");

		// TODO: Username and password match, WebAccount was found.
		String loginToken = securityConfiguration.SHA1Encoder(System.currentTimeMillis() + loginRequest.getUsername());

		webAccountBean.setLoginToken(loginToken);
		newWebAccountRepository.save(webAccountBean);

		return loginToken;
	}

	/**
	 * Removes a login token from a {@link WebAccountBean}
	 *
	 * @param loginToken Login token to delete from a {@link WebAccountBean}
	 */
	public void deleteLoginToken(String loginToken) {
		WebAccountBean webAccountBean = newWebAccountRepository.findByLoginToken(loginToken);

		webAccountBean.setLoginToken(null);

		newWebAccountRepository.save(webAccountBean);

//		webAccountRepository.deleteLoginToken(loginToken);
	}

	/**
	 * Adds a social integration
	 *
	 * @param type Integration type
	 * @param data Webhook URL or access token
	 */
	public void putIntegration(int webAccountId, IntegrationType type, String data) {
		oldWebAccountFunctions.putIntegration(webAccountId, type, data);
	}

	/**
	 * Attempts to add Patreon access and refresh tokens into a {@link WebAccountFunctions} by checking for a matching login token
	 *
	 * @param accessToken  Patreon access token - can be null
	 * @param refreshToken Patreon refresh token - can be null
	 * @param loginToken   {@link WebAccountBean} login token
	 */
	public void putPatreonTokens(String accessToken, String refreshToken, String loginToken) {
		oldWebAccountFunctions.putPatreonTokens(accessToken, refreshToken, loginToken);
	}

	/**
	 * Attempts to acquire Patreon access and refresh tokens by checking for a matching {@link WebAccountFunctions} with the
	 * given login token
	 *
	 * @param loginToken login token belonging to a {@link WebAccountFunctions}
	 * @return {@link CreatorTokensBean} containing the access and refresh tokens for the {@link WebAccountFunctions} that contains
	 * the given login token
	 */
	public CreatorTokensBean getPatreonTokens(String loginToken) throws PSException {
		return oldWebAccountFunctions.getPatreonTokens(loginToken);
	}
}
