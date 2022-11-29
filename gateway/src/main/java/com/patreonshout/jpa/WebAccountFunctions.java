package com.patreonshout.jpa;

import club.minnced.discord.webhook.exception.HttpException;
import club.minnced.discord.webhook.receive.ReadonlyMessage;
import com.patreonshout.PSException;
import com.patreonshout.beans.*;
import com.patreonshout.beans.request.*;
import com.patreonshout.config.SecurityConfiguration;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import com.patreonshout.utils.DiscordWebhookUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

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
	 * Puts the user's list of following creators into the database
	 *
	 * @param campaignIds the list of following creators
	 * @param webAccount the user's {@link com.patreonshout.beans.WebAccount}
	 */
	@Transactional
	public void putFollowingCreators(List<Integer> campaignIds, WebAccount webAccount) {

		// this is kinda scuffed implementation but i wanna get it done fast
//		FollowingCreators followingCreators = new FollowingCreators();
//		followingCreators.setCampaignIds(campaignIds.toString().replaceAll("\\s", ""));


		// we want to overwrite the previous following creators to get the most updated list of following creators
//		followingCreators.setWebAccount(webAccount);
//		webAccount.setFollowingCreators(followingCreators);
		webAccount.getFollowingCreators().setCampaignIds(campaignIds.toString().replaceAll("\\s", ""));
		webAccountRepository.save(webAccount);
	}

	/**
	 * Gets a {@link WebAccount} object from a specified login token
	 *
	 * @param loginToken is the user's session login token used to validate the user
	 * @return {@link WebAccount} object holding data corresponding to the provided login token
	 */
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
	 * Gets a {@link WebAccount} object from an id
	 *
	 * @param id is the webaccount's id
	 * @return {@link WebAccount} object holding data corresponding to the provided login token
	 */
	@Transactional
	public WebAccount getAccount(long id) throws PSException {
		if (id == 0)
			throw new PSException(HttpStatus.NOT_FOUND, "WebAccount ID not provided");

		return webAccountRepository.findByWebAccountId((int) id);
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

		// Create SocialIntegration
		SocialIntegration socialIntegration = new SocialIntegration();
		socialIntegration.setWebAccount(webAccount);
		webAccount.setSocialIntegration(socialIntegration);

		// Create OldPassword
		OldPasswords oldPasswords = new OldPasswords();
		oldPasswords.setWebAccount(webAccount);
		webAccount.setOldPasswords(oldPasswords);

		// Create PatreonTokens
		PatreonTokens patreonTokens = new PatreonTokens();
		patreonTokens.setWebAccount(webAccount);
		webAccount.setCreatorTokens(patreonTokens);

		// Create SocialIntegrationMessages
		SocialIntegrationMessages socialIntegrationMessages = new SocialIntegrationMessages();
		socialIntegrationMessages.setWebAccount(webAccount);
		webAccount.setSocialIntegrationMessages(socialIntegrationMessages);

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
	 */
	@Transactional
	public void putSocialIntegration(String loginToken, SocialIntegrationName socialIntegrationName, String data) throws PSException {
		PutSocialIntegrationRequest createdRequest = PutSocialIntegrationRequest.builder()
				.loginToken(loginToken)
				.socialIntegrationName(socialIntegrationName)
				.data(data)
				.build();

		this.putSocialIntegration(createdRequest);
	}

	/**
	 * Adds a social integration
	 *
	 * @param putSocialIntegrationRequest {@link PutSocialIntegrationRequest} Integration request provided from RESTful call
	 */
	@Transactional
	public void putSocialIntegration(PutSocialIntegrationRequest putSocialIntegrationRequest) throws PSException {
		WebAccount webAccount = this.getAccount(putSocialIntegrationRequest.getLoginToken());
		SocialIntegration socialIntegration = webAccount.getSocialIntegration();

		// * Ensure integration type was given...
		// ! This will throw an exception for us if any errors were found
		validateSocialIntegration(putSocialIntegrationRequest);

		// * Save data into database
		saveIntegration(putSocialIntegrationRequest, socialIntegration);

		// * Save all changes to the Web Account
		webAccountRepository.save(webAccount);
	}
	/**
	 * Adds social integration information for Instagram
	 */
	@Transactional
	public void putInstagramPostDetails(PutInstagramPostDetailsRequest putInstagramPostDetailsRequest) throws PSException {
		WebAccount webAccount = this.getAccount(putInstagramPostDetailsRequest.getLoginToken());
		SocialIntegration socialIntegration = webAccount.getSocialIntegration();

		socialIntegration.setInstagramImageUrl(putInstagramPostDetailsRequest.getImageUrl());
		socialIntegration.setInstagramBlurAmount(putInstagramPostDetailsRequest.getBlurAmount());
		socialIntegration.setInstagramMessageColor(putInstagramPostDetailsRequest.getMessageColor());

		webAccountRepository.save(webAccount);
	}

	/**
	 * Gets the Instagram post details belonging to a specific {@link WebAccount}
	 *
	 * @param loginToken {@link String} login token belonging to a {@link WebAccount}
	 * @return {@link Map} containing Instagram post details
	 */
	@Transactional
	public Map<String, String> getInstagramPostDetails(String loginToken) throws PSException {
		WebAccount webAccount = this.getAccount(loginToken);
		SocialIntegration socialIntegration = webAccount.getSocialIntegration();
		Map<String, String> response = new HashMap<>();

		response.put("instagram_image_url", socialIntegration.getInstagramImageUrl());
		response.put("instagram_blur_amount", socialIntegration.getInstagramBlurAmount());
		response.put("instagram_message_color", socialIntegration.getInstagramMessageColor());

		return response;
	}


	/**
	 * Checks the validity of a social integration's type and given data
	 *
	 * @param putSocialIntegrationRequest {@link PutSocialIntegrationRequest} object containing the contents of this
	 *                                    request
	 * @throws PSException {@link HttpStatus#BAD_REQUEST} if the integration type given was null, or the data did not
	 *                     pass the validity checks
	 */
	private void validateSocialIntegration(PutSocialIntegrationRequest putSocialIntegrationRequest) throws PSException {
		// * Ensure integration type was given
		if (putSocialIntegrationRequest.getSocialIntegrationName() == null)
			throw new PSException(HttpStatus.BAD_REQUEST, "Unknown social integration name");

		// * If the data is null/empty, the user wants to remove this from the database.  Do not validate the data
		if (putSocialIntegrationRequest.getData() == null || putSocialIntegrationRequest.getData().length() == 0)
			return;

		// * Check validity of token/webhook URL
		switch (putSocialIntegrationRequest.getSocialIntegrationName()) {
			case DISCORD:
				DiscordWebhookUtil testMsg;

				// * Create a new Discord Webhook to ensure the given Webhook URL is in a valid format
				try {
					testMsg = new DiscordWebhookUtil(putSocialIntegrationRequest.getData(), false);
				} catch (Exception ex) {
					throw new PSException(HttpStatus.BAD_REQUEST, "The webhook URL is invalid");
				}

				// * Attempt to send an empty message to ensure the given Webhook URL is functional
				try {
					CompletableFuture<ReadonlyMessage> testSend = testMsg.send();
					testSend.join();
				} catch (CompletionException ex) { // ! Error code 400 == GOOD, error code != 400 means it failed to send
					if (ex.getCause() instanceof HttpException && ((HttpException) ex.getCause()).getCode() != 400)
						throw new PSException(HttpStatus.BAD_REQUEST, "The webhook URL is invalid");
				} catch (Exception ex) { // ! If we somehow hit this, something went terribly wrong
					ex.printStackTrace();
					throw new PSException(HttpStatus.BAD_REQUEST, "An unknown exception has occurred while validating Discord webhook URL");
				}
				break;
			case TWITTER:
				break;
			case INSTAGRAM:
				break;
			case REDDIT:
				break;
		}
	}

	/**
	 * Saves social integration data from a request into a given {@link SocialIntegration} object
	 *
	 * @param putSocialIntegrationRequest {@link PutSocialIntegrationRequest} object generated from Spring
	 * @param socialIntegration           {@link SocialIntegration} belonging to a current {@link WebAccount}
	 */
	public void saveIntegration(PutSocialIntegrationRequest putSocialIntegrationRequest, SocialIntegration socialIntegration) throws PSException {
		String data = putSocialIntegrationRequest.getData();

		// Required for deleting a social integration, so we don't save empty strings
		if (data != null && data.isEmpty())
			data = null;

		switch (putSocialIntegrationRequest.getSocialIntegrationName()) {
			case DISCORD:
				socialIntegration.setDiscord(data);
				break;
			case TWITTER: {
				// i didn't want to refactor everything so when you send social integration request to TWITTER, you do access_token:refresh_token in "data"
				String[] tokens = data.split(":");
				socialIntegration.setTwitterAccessToken(tokens[0]);
				socialIntegration.setTwitterRefreshToken(tokens[1]);
				break;
			}
			case INSTAGRAM: {
				if (data == null) {
					socialIntegration.setInstagramAccessToken(null);
					socialIntegration.setInstagramIgUserId(null);
				} else {
					// access_token:ig-user-id
					String[] tokens = data.split(":");
					if (tokens.length != 2)
						throw new PSException(HttpStatus.BAD_REQUEST, "We were not able to properly save the Facebook access token and ig-user-id");

					socialIntegration.setInstagramAccessToken(tokens[0]);
					socialIntegration.setInstagramIgUserId(tokens[1]);
				}

				break;
			}
			case REDDIT: {
				if (data == null) {
					socialIntegration.setRedditAccessToken(null);
					socialIntegration.setRedditRefreshToken(null);
					socialIntegration.setRedditSubredditLocation(null);
				} else {
					// data == "access_token:refresh_token:subreddit_location"
					String[] tokens = data.split(":");
					if (tokens.length != 3) {
						throw new PSException(HttpStatus.BAD_REQUEST, "We were not able to properly save the Reddit access and refresh tokens");
					}

//					if (tokens[0].equals("") && tokens[1].equals("") && !tokens[2].equals("")) { // when someone sends the request ::subreddit_location it will only set the subreddit_location without changing the access token or refresh token
//						socialIntegration.setRedditSubredditLocation(tokens[2]);
//					}
					else {
						if (!tokens[0].equals("")){
							socialIntegration.setRedditAccessToken(tokens[0]);
						}

						if (!tokens[1].equals("")){
							socialIntegration.setRedditRefreshToken(tokens[1]);
						}

						if (!tokens[2].equals("")){
							socialIntegration.setRedditSubredditLocation(tokens[2]);
						}
					}
				}

				break;
			}
			default:
				System.out.println("UNKNOWN CASE: " + putSocialIntegrationRequest.getSocialIntegrationName());
				break;
		}
	}

	/**
	 * Gets a {@link WebAccount} from the given login token, then returns its {@link SocialIntegration}
	 *
	 * @param loginToken {@link WebAccount} login token
	 * @return {@link SocialIntegration} belonging to a login token's {@link WebAccount}
	 */
	@Transactional
	public SocialIntegration getSocialIntegration(String loginToken) throws PSException {
		WebAccount webAccount = this.getAccount(loginToken);

		return webAccount.getSocialIntegration();
	}

	/**
	 * Saves social integration messages from a request into a {@link WebAccount}'s {@link SocialIntegrationMessages} object
	 *
	 * @param putSocialIntegrationMessageRequest {@link PutSocialIntegrationMessageRequest} object generated from Spring
	 */
	@Transactional
	public void putSocialIntegrationMessages(PutSocialIntegrationMessageRequest putSocialIntegrationMessageRequest) throws PSException {
		WebAccount webAccount = this.getAccount(putSocialIntegrationMessageRequest.getLoginToken());
		SocialIntegrationMessages socialIntegrationMessages = webAccount.getSocialIntegrationMessages();

		// * Check validity of token/webhook URL
		switch (putSocialIntegrationMessageRequest.getSocialIntegrationName()) {
			case DISCORD:
				socialIntegrationMessages.setDiscordPublicMessage(putSocialIntegrationMessageRequest.getPublicMessage());
				socialIntegrationMessages.setDiscordPrivateMessage(putSocialIntegrationMessageRequest.getPrivateMessage());
				break;
			case TWITTER:
				socialIntegrationMessages.setTwitterPublicMessage(putSocialIntegrationMessageRequest.getPublicMessage());
				socialIntegrationMessages.setTwitterPrivateMessage(putSocialIntegrationMessageRequest.getPrivateMessage());
				break;
			case INSTAGRAM:
				socialIntegrationMessages.setInstagramPublicMessage(putSocialIntegrationMessageRequest.getPublicMessage());
				socialIntegrationMessages.setInstagramPrivateMessage(putSocialIntegrationMessageRequest.getPrivateMessage());
				break;
			case REDDIT:
				socialIntegrationMessages.setRedditPublicMessage(putSocialIntegrationMessageRequest.getPublicMessage());
				socialIntegrationMessages.setRedditPrivateMessage(putSocialIntegrationMessageRequest.getPrivateMessage());
				break;
		}

		// * Save all changes to the WebAccount
		webAccountRepository.save(webAccount);
	}

	/**
	 * Acquires a {@link SocialIntegrationMessages} from the given login token's {@link WebAccount}
	 *
	 * @param loginToken {@link WebAccount}'s login token
	 * @return {@link SocialIntegrationMessages} belonging to a login token's {@link WebAccount}
	 */
	@Transactional
	public SocialIntegrationMessages getSocialIntegrationMessages(String loginToken) throws PSException {
		return this.getAccount(loginToken).getSocialIntegrationMessages();
	}

	/**
	 * Attempts to add Patreon access and refresh tokens into a {@link WebAccount} by checking for a matching login token
	 *
	 * @param loginToken   {@link WebAccount} login token
	 * @param accessToken  Patreon access token - can be null
	 * @param refreshToken Patreon refresh token - can be null
	 */
	@Transactional
	public void putPatreonTokens(String loginToken, String accessToken, String refreshToken) throws PSException {
		WebAccount webAccount = this.getAccount(loginToken);
		this.putPatreonTokens(webAccount, accessToken, refreshToken);
	}

	@Transactional
	public void putPatreonTokens(WebAccount webAccount, String accessToken, String refreshToken) {
		PatreonTokens patreonTokens = webAccount.getCreatorTokens();

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
		WebAccount webAccount = this.getAccount(loginToken);

		return webAccount.getCreatorTokens();
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

		// ! Desired new password matches one of the previous three passwords
		if (!oldPasswords.isEmpty())
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
	 * Gets a {@link com.patreonshout.beans.WebAccount} object from a specified web account id
	 *
	 * @param webAccountId is the id of the web account
	 * @return {@link com.patreonshout.beans.WebAccount} object holding data corresponding to the provided web account id
	 */
	public WebAccount findByWebAccountId(int webAccountId) {
		return webAccountRepository.findByWebAccountId(webAccountId);
	}
}
