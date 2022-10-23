package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.WebAccountBean;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.config.SecurityConfiguration;
import com.patreonshout.jpa.constants.IntegrationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Functions that directly interact with the WebAccounts table in the database
 */
@Repository
public class WebAccountRepository {

	/**
	 * em is the {@link EntityManager} that handles all the transactions with our database
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * securityConfiguration is the {@link SecurityConfiguration} that handles encrypting password with
	 * {@link BCryptPasswordEncoder} hashing
	 */
	@Autowired
	SecurityConfiguration securityConfiguration;

	/**
	 * Adds a {@link WebAccount} to the database
	 *
	 * @param registerRequest {@link RegisterRequest} object that contains the desired login details for a new
	 *                        {@link WebAccount}
	 */
	@Transactional
	public void putAccount(RegisterRequest registerRequest) {
		String sql = "insert into webaccounts (username, password, NaCl) values (:username, :password, :password_salt)";
		Query q = em.createNativeQuery(sql);

		String salt = securityConfiguration.createSalt();

		q.setParameter("username", registerRequest.getUsername());
		q.setParameter("password", securityConfiguration.encodePassword(registerRequest.getPassword(), salt));
		q.setParameter("password_salt", salt);

		q.executeUpdate();
	}

	/**
	 * Ensures a {@link WebAccount} with a matching username and password exists in the database
	 *
	 * @param loginRequest {@link LoginRequest} object that contains the desired login details to check
	 */
	@Transactional
	public String readAccount(LoginRequest loginRequest) throws PSException {
		String sql = "SELECT * FROM webaccounts WHERE username = :username";

		Query q = em.createNativeQuery(sql, WebAccountBean.class);
		q.setParameter("username", loginRequest.getUsername());

		List<WebAccountBean> waList = q.getResultList();

		// Username does not exist.
		if (waList.isEmpty())
			throw new PSException(HttpStatus.NOT_FOUND, "Username does not exist.");

		WebAccountBean accountBean = waList.get(0);

		// Password does not match the given user.
		if (!securityConfiguration.passwordMatches(loginRequest.getPassword(), accountBean.getPassword_salt(), accountBean.getPassword()))
			throw new PSException(HttpStatus.UNAUTHORIZED, "Incorrect password.");

		// TODO: Username and password match, WebAccount was found.
		String loginToken = securityConfiguration.SHA1Encoder(System.currentTimeMillis() + loginRequest.getUsername());
		putLoginToken(loginToken, loginRequest.getUsername());

		return loginToken;
	}

	/**
	 * Adds a login token to a {@link WebAccount} matching the given username in the database
	 *
	 * @param loginToken Login token to add to the desired {@link WebAccount}
	 * @param username Username of the desired {@link WebAccount}
	 */
	@Transactional
	public void putLoginToken(String loginToken, String username) {
		String sql = "UPDATE webaccounts SET login_token = :login_token WHERE username = :username";

		Query q = em.createNativeQuery(sql);
		q.setParameter("login_token", loginToken);
		q.setParameter("username", username);

		q.executeUpdate();
	}

	/**
	 * Removes a login token from a {@link WebAccount}
	 *
	 * @param loginToken Login token to delete from the database
	 */
	@Transactional
	public void deleteLoginToken(String loginToken) {
		String sql = "UPDATE webaccounts SET login_token = NULL WHERE login_token = :login_token";

		Query q = em.createNativeQuery(sql);
		q.setParameter("login_token", loginToken);

		q.executeUpdate();
	}

	/**
	 * Adds a social integration
	 *
	 * @param type Integration type
	 * @param data Webhook URL or access token
	 */
	@Transactional
	public void putIntegration(int webAccountId, IntegrationType type, String data) {
		String givenType = type.name().toLowerCase();
		Query q = em.createNativeQuery("INSERT INTO social_integrations (social_integration_id, " + givenType + ") "
				+ "VALUES (" + webAccountId + ", '" + data + "')"
				+ "ON DUPLICATE KEY UPDATE " + type + " = VALUES(" + type + ")");

		q.executeUpdate();
	}


	/**
	 * Attempts to add Patreon access and refresh tokens into a {@link WebAccount} by checking for a matching login token
	 *
	 * @param accessToken  Patreon access token - can be null
	 * @param refreshToken Patreon refresh token - can be null
	 * @param loginToken   {@link WebAccountBean} login token
	 */
	@Transactional
	public void putPatreonTokens(String accessToken, String refreshToken, String loginToken) {
		String sql = "UPDATE webaccounts SET access_token = COALESCE(:access_token, @access_token), refresh_token = COALESCE(:refresh_token, @refresh_token) WHERE login_token = :login_token";

		Query q = em.createNativeQuery(sql);

		q.setParameter("access_token", accessToken);
		q.setParameter("refresh_token", refreshToken);
		q.setParameter("login_token", loginToken);

		q.executeUpdate();
	}

	/**
	 * Attempts to acquire Patreon access and refresh tokens by checking for a matching {@link WebAccount} with the
	 * given login token
	 *
	 * @param loginToken login token belonging to a {@link WebAccount}
	 * @return {@link WebAccountBean} containing the access and refresh tokens for the {@link WebAccount} that contains
	 * the given login token
	 */
	@Transactional
	public WebAccountBean getPatreonTokens(String loginToken) throws PSException {
		String sql = "SELECT * FROM webaccounts WHERE login_token = :login_token";

		Query q = em.createNativeQuery(sql, WebAccountBean.class);

		q.setParameter("login_token", loginToken);

		List<WebAccountBean> waList = q.getResultList();

		if (waList.isEmpty())
			throw new PSException(HttpStatus.NOT_FOUND, "An exception occurred while finding the login token.");

		return waList.get(0);
	}
}
