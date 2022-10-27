package com.patreonshout.beans;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * POJO that relates to the social_integrations table in our database
 */
@Data
@Entity
@Table(name = "social_integrations")
public class SocialIntegration implements Serializable {

	/**
	 * social_integration_id is the index/primary key for the social_integrations table in our database
	 */
	@Id
	@Column(name="webaccount_id")
	protected int webAccountId;

	/**
	 * discord_data holds the Patreon content creator Discord Webhook URL needed for integration
	 */
	@Column(name = "discord")
	protected String discord;

	/**
	 * twitter_data holds the Twitter token needed for integration
	 */
	@Column(name = "twitter")
	protected String twitter;

	/**
	 * instagram_data holds the Instagram token needed for integration
	 */
	@Column(name = "instagram")
	protected String instagram;

	/**
	 * webAccount is the {@link com.patreonshout.beans.WebAccount} object linked with this object
	 */
	@OneToOne
	@MapsId
	@JoinColumn(name = "webaccount_id")
	protected WebAccount webAccount;
}
