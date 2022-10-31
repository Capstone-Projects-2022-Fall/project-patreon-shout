package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.*;
import com.patreonshout.beans.request.SocialIntegrationRequest;
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

	@Transactional
	public WebAccount getAccount(String loginToken) throws PSException {
		if (loginToken == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token not provided");

		WebAccount webAccount = webAccountRepository.findByLoginToken(loginToken);

		if (webAccount == null)
			throw new PSException(HttpStatus.NOT_FOUND, "Login token does not belong to a user");

		return webAccount;
	}

	/**
	 * Adds a {@link WebAccountFunctions} to the database
	 *
	 * @param registerRequest {@link RegisterRequest} object that contains the desired login details for a new
	 *                        {@link HttpStatus} 409 if the account already exists
	 */
	@Transactional
	public void putAccount(RegisterRequest registerRequest) throws PSException {
		ensurePasswordValidity(registerRequest.getPassword());

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

	/**
	 * Changes the password for a {@link WebAccount}.<br/>
	 * <br/>
	 * <b>Requirements</b>
	 * <ul>
	 *     <li>loginToken matches an account</li>
	 *     <li>currentPassword does not match newPassword</li>
	 *     <li>currentPassword matches the current password on the {@link WebAccount}</li>
	 *     <li>newPassword does not match any of the {@link OldPasswords} for the {@link WebAccount}</li>
	 * </ul>
	 *
	 * @param loginToken      login token belonging to a {@link WebAccount}
	 * @param currentPassword current password for the {@link WebAccount}
	 * @param newPassword     desired new password for the {@link WebAccount}
	 * @throws PSException {@link HttpStatus#UNAUTHORIZED} if the given current password is incorrect for the given
	 *                     account, or it does not meet our password requirements.
	 *                     {@link HttpStatus#CONFLICT} if the new password matches the current password, or any of the
	 *                     previous three passwords for the account.
	 */
	public void putNewPassword(String loginToken, String currentPassword, String newPassword) throws PSException {
		// ! Given current password is null
		if (currentPassword == null)
			throw new PSException(HttpStatus.BAD_REQUEST, "Current password must not be null");

		// ! Given new password is null
		if (newPassword == null)
			throw new PSException(HttpStatus.BAD_REQUEST, "New password must not be null");

		// ! Given current password matches the desired new password
		if (currentPassword.equals(newPassword))
			throw new PSException(HttpStatus.UNAUTHORIZED, "Current password can not match the new password");

		// ! Given new password does not meet our password requirements
		ensurePasswordValidity(newPassword);

		WebAccount webAccount = getAccount(loginToken);

		// ! Given current password does not match the web account's actual current password
		if (!securityConfiguration.passwordMatches(currentPassword, webAccount.getPasswordSalt(), webAccount.getPassword()))
			throw new PSException(HttpStatus.UNAUTHORIZED, "Current password is incorrect for the given account");

		OldPasswords oldPasswords = webAccount.getOldPasswords();

		// First time changing password
		if (oldPasswords == null)
			oldPasswords = new OldPasswords();
		else // ! Desired new password matches one of the previous three passwords
			for (String oldPass : new String[]{oldPasswords.getOldPasswordOne(), oldPasswords.getOldPasswordTwo(), oldPasswords.getOldPasswordThree()})
				if (oldPass != null && securityConfiguration.passwordMatches(newPassword, webAccount.getPasswordSalt(), oldPass))
					throw new PSException(HttpStatus.CONFLICT, "New password can not be one of the last three previously used passwords");

		// Shift passwords one column to the right
		oldPasswords.setOldPasswordThree(oldPasswords.getOldPasswordTwo());
		oldPasswords.setOldPasswordTwo(oldPasswords.getOldPasswordOne());
		oldPasswords.setOldPasswordOne(webAccount.getPassword());

		// Set and save data
		webAccount.setPassword(securityConfiguration.encodePassword(newPassword, webAccount.getPasswordSalt()));
		oldPasswords.setWebAccount(webAccount);
		webAccount.setOldPasswords(oldPasswords);

		webAccountRepository.save(webAccount);
	}

	/**
	 * Checks if the given raw password meets our password requirements
	 *
	 * @param rawPassword password to check
	 * @throws PSException {@link HttpStatus#UNAUTHORIZED} if the password does not meet our password requirements
	 */
	private void ensurePasswordValidity(String rawPassword) throws PSException {
		// ! Given new password does not meet our password requirements
		if (!securityConfiguration.passwordIsValid(rawPassword))
			throw new PSException(HttpStatus.UNAUTHORIZED, "New password does not meet password requirements");
	}

	/**
	 * Gets a {@link com.patreonshout.beans.WebAccount} object from a specified login token
	 *
	 * @param loginToken is the user's session login token used to validate the user
	 * @return {@link com.patreonshout.beans.WebAccount} object holding data corresponding to the provided login token
	 */
	public WebAccount findByLoginToken(String loginToken) {
		return webAccountRepository.findByLoginToken(loginToken);
	}
}
