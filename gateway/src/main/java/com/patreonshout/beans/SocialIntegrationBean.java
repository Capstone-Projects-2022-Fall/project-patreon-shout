package com.patreonshout.beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * POJO that relates to the social_integrations table in our database
 */
@Entity
@Table(name = "social_integrations")
public class SocialIntegrationBean implements Serializable {

	/**
	 * social_integration_id is the index/primary key for the social_integrations table in our database
	 */
	@Id
	@Column(name="social_integration_id")
	protected int social_integration_id;

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
}
