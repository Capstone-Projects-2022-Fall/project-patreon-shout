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
	 * {@link WebAccount}
	 */
	@Transactional
	public void putAccount(RegisterRequest registerRequest) {
		String sql = "insert into webaccounts (username, password) values (:username, :password)";
		Query q = em.createNativeQuery(sql);

		q.setParameter("username", registerRequest.getUsername());
		q.setParameter("password", securityConfiguration.passwordEncoder().encode(registerRequest.getPassword()));

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

		// Password does not match the given user.
		if (!securityConfiguration.passwordEncoder().matches(loginRequest.getPassword(), waList.get(0).getPassword()))
			throw new PSException(HttpStatus.UNAUTHORIZED, "Incorrect password.");

		// TODO: Username and password match, WebAccount was found.
		return securityConfiguration.SHA1Encoder(System.currentTimeMillis() + loginRequest.getUsername());
	}

	@Transactional
	public void putIntegration(int webAccountId, IntegrationType type, String data) {
//		String sql = "INSERT INTO social_integrations (social_integration_id, :type) " +
//				"VALUES (:social_integration_id, :data) " +
//				"ON DUPLICATE KEY UPDATE :type = VALUES(:type)";
//		Query q = em.createNativeQuery(sql);
//
//		q.setParameter("social_integration_id", webAccountId);
//		q.setParameter("type", type.name().toLowerCase());
//		q.setParameter("data", data);

		String givenType = type.name().toLowerCase();
		Query q = em.createNativeQuery("INSERT INTO social_integrations (social_integration_id, " + givenType + ") "
				+ "VALUES (" + webAccountId + ", '" + data + "')"
				+ "ON DUPLICATE KEY UPDATE " + type + " = VALUES(" + type + ")");

		q.executeUpdate();
	}
}
