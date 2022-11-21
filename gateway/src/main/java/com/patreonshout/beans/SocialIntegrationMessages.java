package com.patreonshout.beans;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * POJO that relates to the social_integration_messages table in our database
 */
@Data
@Entity
@Table(name = "social_integration_messages")
public class SocialIntegrationMessages implements Serializable {

	/**
	 * PK, contains an FK to webaccounts.webaccount_id
	 */
	@Id
	@Column(name="webaccount_id")
	protected int webAccountId;

	/**
	 * Custom Discord message for public Patreon posts
	 */
	@Column(name = "discord_public_message")
	protected String discordMessage;

	/**
	 * Custom Discord message for private Patreon posts
	 */
	@Column(name = "discord_private_message")
	protected String discordPrivateMessage;

	/**
	 * {@link com.patreonshout.beans.WebAccount} object linked with this object
	 */
	@OneToOne
	@MapsId
	@JoinColumn(name = "webaccount_id")
	protected WebAccount webAccount;
}
