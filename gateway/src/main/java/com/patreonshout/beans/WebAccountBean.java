package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * POJO that relates to the webaccounts table in the database
 */
@Setter
@Getter
@Entity
@Table(name = "webaccounts")
public class WebAccountBean {

	/**
	 * <b>webaccount_id</b> is a {@link Integer} primary key for the webaccounts table in the database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webaccount_id")
	int webaccount_id;

	/**
	 * <b>username</b> is a unique {@link String} that holds the  username for the account
	 */
	@Column(name = "username", unique = true)
	String username;

	/**
	 * <b>password</b> is a {@link String} that holds the password for the account
	 */
	@Column(name = "password")
	String password;

	/**
	 * <b>login_token</b> is a {@link String} that holds the login token for the account
	 */
	@Column(name = "login_token", unique = true)
	String login_token;

	/**
	 * <b>access_token</b> is a {@link String} that holds the Patreon access token for the account
	 */
	@Column(name = "access_token", unique = true)
	String access_token;

	/**
	 * <b>refresh_token</b> is a{@link String} that holds the Patreon refresh token for the account
	 */
	@Column(name = "refresh_token", unique = true)
	String refresh_token;
}
