package com.patreonshout.jpa;

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
	 * Adds a {@link WebAccount} to the database
	 *
	 * @param username Username for the {@link WebAccount}
	 * @param password Password for the {@link WebAccount}
	 */
	@Transactional
	public void putAccount(String username, String password) {
		String sql = "insert into webaccounts (username, password) values (:username, :password)";
		Query q = em.createNativeQuery(sql);

		q.setParameter("username", username);
		q.setParameter("password", password);

		q.executeUpdate();
	}
}
