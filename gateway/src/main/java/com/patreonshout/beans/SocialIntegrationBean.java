package com.patreonshout.beans;

import javax.persistence.*;

/**
 * POJO that relates to the social_integrations table in our database
 */
@Entity
@Table(name = "social_integrations")
public class SocialIntegrationBean {

	/**
	 * social_integration_id is the index/primary key for the social_integrations table in our database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="social_integration_id")
	protected int social_integration_id;

	/**
	 * discord_data holds the Patreon content creator Discord Webhook URL needed for integration
	 */
	@Column(name = "discord_data")
	protected String discord_data;

	/**
	 * twitter_data holds the Twitter token needed for integration
	 */
	@Column(name = "twitter_data")
	protected String twitter_data;

	/**
	 * instagram_data holds the Instagram token needed for integration
	 */
	@Column(name = "instagram_data")
	protected String instagram_data;
}
