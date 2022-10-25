package com.patreonshout.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * POJO that relates to the webaccounts table in the database
 */
@Entity
@Table(name = "webaccounts")
@NoArgsConstructor
@Data
public class NewWebAccount {
	/**
	 * <b>webaccount_id</b> is a {@link Integer} primary key for the webaccounts table in the database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webaccount_id", unique = true)
	int webaccount_id;

	/**
	 * <b>username</b> is a unique {@link String} that holds the  username for the account
	 */
	@Column(name = "username", unique = true)
	protected String username;

	/**
	 * <b>password</b> is a {@link String} that holds the password for the account
	 */
	@Column(name = "password")
	protected String password;

	/**
	 * <b>password_salt</b> is a {@link String} that holds the password salt for the account
	 */
	@Column(name = "NaCl", unique = true)
	protected String password_salt;

	/**
	 * <b>login_token</b> is a {@link String} that holds the login token for the account
	 */
	@Column(name = "login_token", unique = true)
	String login_token;
}
