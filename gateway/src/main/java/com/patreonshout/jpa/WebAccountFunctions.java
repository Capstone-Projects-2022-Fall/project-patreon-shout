package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.PatreonTokens;
import com.patreonshout.beans.request.SocialIntegrationRequest;
import com.patreonshout.beans.SocialIntegration;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.config.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Component that contains functions for WebAccount endpoints that allow interaction with the database
 */
@Component
public class WebAccountFunctions {

	/**
	 * An autowired Spring repository that handles all database CRUD operations with the webaccounts
	 */
	@Autowired
	WebAccountRepository webAccountRepository;

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
	@Transactional
	public void putAccount(RegisterRequest registerRequest) {
		WebAccount webAccount = new WebAccount();
		String salt = securityConfiguration.createSalt();

		webAccount.setUsername(registerRequest.getUsername());
		webAccount.setPassword(securityConfiguration.encodePassword(registerRequest.getPassword(), salt));
		webAccount.setPasswordSalt(salt);

		webAccountRepository.save(webAccount);
	}

	/**
	 * Adds a login token to a {@link WebAccountFunctions} matching the given username in the database
	 *
	 * @param loginRequest {@link LoginRequest} object that contains the desired login details to check
	 */
	@Transactional
	public String readAccount(LoginRequest loginRequest) throws PSException {
		WebAccount webAccount = webAccountRepository.findByUsername(loginRequest.getUsername());

		// Username does not exist.
		if (webAccount == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Username does not exist.");

		// Username exists, check if given password matches.
		if (!securityConfiguration.passwordMatches(loginRequest.getPassword(), webAccount.getPasswordSalt(), webAccount.getPassword()))
			throw new PSException(HttpStatus.UNAUTHORIZED, "Incorrect password.");

		// TODO: Username and password match, WebAccount was found.
		String loginToken = securityConfiguration.SHA1Encoder(System.currentTimeMillis() + loginRequest.getUsername());

		webAccount.setLoginToken(loginToken);
		webAccountRepository.save(webAccount);

		return loginToken;
	}

	/**
	 * Removes a login token from a {@link WebAccount}
	 *
	 * @param loginToken Login token to delete from a {@link WebAccount}
	 */
	@Transactional
	public void deleteLoginToken(String loginToken) {
		WebAccount webAccount = webAccountRepository.findByLoginToken(loginToken);
		webAccount.setLoginToken(null);

		webAccountRepository.save(webAccount);
	}

	/**
	 * Adds a social integration
	 *
	 * @param socialIntegrationRequest {@link SocialIntegrationRequest} Integration request provided from RESTful call
	 */
	@Transactional
	public void putSocialIntegration(SocialIntegrationRequest socialIntegrationRequest) throws PSException {
		if (socialIntegrationRequest.getLoginToken() == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token not provided");

		WebAccount webAccount = webAccountRepository.findByLoginToken(socialIntegrationRequest.getLoginToken());

		if (webAccount == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token does not belong to a user");

		SocialIntegration socialIntegration = webAccount.getSocialIntegration();

		if (socialIntegration == null) {
			socialIntegration = new SocialIntegration();
			socialIntegration.setWebAccountId(webAccount.getWebAccountId());
		}

		socialIntegration.setWebAccount(webAccount);
		webAccount.setSocialIntegration(socialIntegration);

		switch (socialIntegrationRequest.getIntegrationType()) {
			case DISCORD:
				socialIntegration.setDiscord(socialIntegrationRequest.getData());
				break;
			case TWITTER:
				socialIntegration.setTwitter(socialIntegrationRequest.getData());
				break;
			case INSTAGRAM:
				socialIntegration.setInstagram(socialIntegrationRequest.getData());
				break;
		}

		webAccountRepository.save(webAccount);
	}

	/**
	 * Attempts to add Patreon access and refresh tokens into a {@link WebAccount} by checking for a matching login token
	 *
	 * @param accessToken  Patreon access token - can be null
	 * @param refreshToken Patreon refresh token - can be null
	 * @param loginToken   {@link WebAccount} login token
	 */
	@Transactional
	public void putPatreonTokens(String accessToken, String refreshToken, String loginToken) throws PSException {
		if (loginToken == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token not provided");

		WebAccount webAccount = webAccountRepository.findByLoginToken(loginToken);

		if (webAccount == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token does not belong to a user");

		PatreonTokens patreonTokens = webAccount.getCreatorTokens();

		if (patreonTokens == null) {
			patreonTokens = new PatreonTokens();
			patreonTokens.setWebAccountId(webAccount.getWebAccountId());
		}

		patreonTokens.setWebAccount(webAccount);
		webAccount.setCreatorTokens(patreonTokens);

		patreonTokens.setAccessToken(accessToken);
		patreonTokens.setRefreshToken(refreshToken);

		webAccountRepository.save(webAccount);
//		oldWebAccountFunctions.putPatreonTokens(accessToken, refreshToken, loginToken);
	}

	/**
	 * Attempts to acquire Patreon access and refresh tokens by checking for a matching {@link WebAccountFunctions} with the
	 * given login token
	 *
	 * @param loginToken login token belonging to a {@link WebAccountFunctions}
	 * @return {@link PatreonTokens} containing the access and refresh tokens for the {@link WebAccountFunctions} that contains
	 * the given login token
	 */
	@Transactional
	public PatreonTokens getPatreonTokens(String loginToken) throws PSException {
		if (loginToken == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token not provided");

		WebAccount webAccount = webAccountRepository.findByLoginToken(loginToken);

		if (webAccount == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token does not belong to a user");

		PatreonTokens patreonTokens = webAccount.getCreatorTokens();

		if (patreonTokens == null) {
			patreonTokens = new PatreonTokens();
			patreonTokens.setWebAccountId(webAccount.getWebAccountId());
		}

		return patreonTokens;
	}
}
