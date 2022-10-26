package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorTokensBean;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.config.SecurityConfiguration;
import com.patreonshout.jpa.constants.IntegrationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Functions that directly interact with the WebAccounts table in the database
 */
@Repository
public class OldWebAccountFunctions {

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
	 * Attempts to add Patreon access and refresh tokens into a {@link WebAccountFunctions} by checking for a matching login token
	 *
	 * @param accessToken  Patreon access token - can be null
	 * @param refreshToken Patreon refresh token - can be null
	 * @param loginToken   {@link WebAccount} login token
	 */
	@Transactional
	public void putPatreonTokens(String accessToken, String refreshToken, String loginToken) {
//		String sql = "UPDATE creator_tokens " +
//				"SET webaccount_id = COALESCE(:webaccount_id, @webaccount_id), " +
//				"access_token = COALESCE(:access_token, @access_token), " +
//				"refresh_token = COALESCE(:refresh_token, @refresh_token), " +
//				"expires_in = COALESCE(:expires_in, @expires_in), " +
//				"scope = COALESCE(:scope, @scope), " +
//				"WHERE login_token = :login_token";
//
//		Query q = em.createNativeQuery(sql);
//		q.setParameter("access_token", accessToken);
//		q.setParameter("refresh_token", refreshToken);
//		q.setParameter("login_token", loginToken);
//
//		q.executeUpdate();
	}

	/**
	 * Attempts to acquire Patreon access and refresh tokens by checking for a matching {@link WebAccountFunctions} with the
	 * given login token
	 *
	 * @param loginToken login token belonging to a {@link WebAccountFunctions}
	 * @return {@link WebAccount} containing the access and refresh tokens for the {@link WebAccountFunctions} that contains
	 * the given login token
	 */
	@Transactional
	public CreatorTokensBean getPatreonTokens(String loginToken) throws PSException {
		String sql = "SELECT * " +
				"FROM creator_tokens " +
				"INNER JOIN webaccounts " +
				"ON creator_tokens.webaccount_id = webaccounts.webaccount_id " +
				"WHERE webaccounts.login_token = :login_token";

		Query q = em.createNativeQuery(sql, CreatorTokensBean.class);
		q.setParameter("login_token", loginToken);

		List<CreatorTokensBean> resultList = q.getResultList();

		if (resultList.isEmpty())
			throw new PSException(HttpStatus.NOT_FOUND, "An exception occurred while finding the login token.");

		return resultList.get(0);
	}
}