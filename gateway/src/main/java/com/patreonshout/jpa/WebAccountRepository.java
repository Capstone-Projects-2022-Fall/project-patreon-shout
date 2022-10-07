package com.patreonshout.jpa;

import com.patreonshout.jpa.constants.IntegrationType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	 * Adds a {@link com.patreonshout.jpa.WebAccount} to the database
	 *
	 * @param username Username for the {@link com.patreonshout.jpa.WebAccount}
	 * @param password Password for the {@link com.patreonshout.jpa.WebAccount}
	 * @return {@link HttpStatus} object containing either 200 (No error) or
	 * 409 (Username already exists in webaccounts table)
	 */
	@Transactional
	public HttpStatus putAccount(String username, String password) {
		String sql = "insert into webaccounts (username, password) values (:username, :password)";
		Query q = em.createNativeQuery(sql);

		q.setParameter("username", username);
		q.setParameter("password", password);

		try {
			q.executeUpdate();
		} catch (DataIntegrityViolationException ex) {
			return HttpStatus.CONFLICT; // Username already exists in webaccounts table
		}

		return HttpStatus.OK; // No error
	}

	@Transactional
	public HttpStatus putIntegration(int webAccountId, IntegrationType type, String data) {
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

		return HttpStatus.OK;
	}
}
