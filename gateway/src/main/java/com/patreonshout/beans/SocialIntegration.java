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
	 * twitterAccessToken holds the Twitter access_token needed for integration
	 */
	@Column(name = "twitter_access_token")
	protected String twitterAccessToken;

	/**
	 * twitterRefreshToken holds the Twitter refresh token needed for integration
	 */
	@Column(name = "twitter_refresh_token")
	protected String twitterRefreshToken;

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

	/**
	 * Informs if this SocialIntegration object has any Social Integrations
	 *
	 * @return {@link Boolean#TRUE} if any social integrations are not null, false otherwise
	 */
	public Boolean isEmpty() {
		return discord == null && twitterAccessToken == null && twitterRefreshToken == null && instagram == null;
	}
}
