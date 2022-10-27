package com.patreonshout.beans;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "webaccounts")
public class WebAccount {

	/**
	 * <b>webaccount_id</b> is a {@link Integer} primary key for the webaccounts table in the database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webaccount_id", unique = true)
	protected int webAccountId;

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
	protected String passwordSalt;

	/**
	 * <b>login_token</b> is a {@link String} that holds the login token for the account
	 */
	@Column(name = "login_token", unique = true)
	protected String loginToken;

	@OneToOne(mappedBy = "webAccount", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	SocialIntegration socialIntegration;

	@OneToOne(mappedBy = "webAccount", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	PatreonTokens creatorTokens;

	@OneToOne(mappedBy = "webAccount", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	CreatorPages creatorPages;
}
