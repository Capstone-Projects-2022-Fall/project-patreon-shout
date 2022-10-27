package com.patreonshout.jpa;

import com.patreonshout.beans.WebAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for WebAccountFunctions to use pre-made JpaRepository database communications
 */
@Repository
public interface WebAccountRepository extends JpaRepository<WebAccount, Long> {
	/**
	 * finds a {@link com.patreonshout.beans.WebAccount} object from a specified username
	 *
	 * @param username is a user's username
	 * @return {@link com.patreonshout.beans.WebAccount} object holding data corresponding to the provided username
	 */
	WebAccount findByUsername(String username);

	/**
	 * Gets a {@link com.patreonshout.beans.WebAccount} object from a specified login token
	 *
	 * @param loginToken is the user's session login token used to validate the user
	 * @return {@link com.patreonshout.beans.WebAccount} object holding data corresponding to the provided login token
	 */
	WebAccount findByLoginToken(String loginToken);
}
