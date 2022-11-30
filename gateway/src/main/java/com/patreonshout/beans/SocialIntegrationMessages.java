package com.patreonshout.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	protected String discordPublicMessage = "{content}";

	/**
	 * Custom Discord message for private Patreon posts
	 */
	@Column(name = "discord_private_message")
	protected String discordPrivateMessage = "This Patreon post is exclusive to my patrons!  Visit the link above to see the post.";

	/**
	 * Custom Twitter message for public Patreon posts
	 */
	@Column(name = "twitter_public_message")
	protected String twitterPublicMessage = "{content}\\n\n" +
			"{link}";

	/**
	 * Custom Twitter message for private Patreon posts
	 */
	@Column(name = "twitter_private_message")
	protected String twitterPrivateMessage = "This Patreon post is exclusive to my patrons!  View the post below.\\n\n" +
			"{link}";

	/**
	 * Custom Instagram message for public Patreon posts
	 */
	@Column(name = "instagram_public_message")
	protected String instagramPublicMessage = "{content}\\n\\n\n" +
			"{link}\\n\\n\n" +
			"#PatreonShout";

	/**
	 * Custom Instagram message for private Patreon posts
	 */
	@Column(name = "instagram_private_message")
	protected String instagramPrivateMessage = "This Patreon post is exclusive to my patrons!  Visit the link to see the post.\\n\\n\n" +
			"{link}";

	/**
	 * Custom Reddit message for public Patreon posts
	 */
	@Column(name = "reddit_public_message")
	protected String redditPublicMessage = "{content}";

	/**
	 * Custom Reddit message for private Patreon posts
	 */
	@Column(name = "reddit_private_message")
	protected String redditPrivateMessage = "This Patreon post is exclusive to my patrons!  Visit the link above to see the post.";

	/**
	 * {@link com.patreonshout.beans.WebAccount} object linked with this object
	 */
	@JsonIgnore
	@OneToOne
	@MapsId
	@JoinColumn(name = "webaccount_id")
	protected WebAccount webAccount;
}
