package com.patreonshout.patreon.endpoints.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Java Object containing the data we get from OAuth requests to Patreon that we use to turn into a JSON string
 */

@ToString // User V2, REF: https://docs.patreon.com/#user-v2
public class User {
	/**
	 * the Data object that is defined in this class and holds the data returned from the OAuth
	 */
	@JsonProperty("data")
	@NotNull
	public Data data;
	/**
	 * the Links object that is defined in this class and holds the link to itself from the OAuth
	 */
	@JsonProperty("links")
	@NotNull
	public Links links;

	/**
	 * Java Object containing all the data we get from Patreon's OAuth
	 */
	@ToString
	public class Data {
		/**
		 * the Patreon id
		 */
		@JsonProperty("id")
		public float id;

		/**
		 * the Patreon type
		 */
		@JsonProperty("type")
		public String type;

		/**
		 * the Patreon attributesd
		 */
		@JsonProperty("attributes")
		public Attributes attributes;

		/**
		 * provides a toString for all the Patreon attributes
		 */
		@ToString
		public class Attributes {

			/**
			 * the user's about text, which appears on their profile, can be null
			 */
			@JsonProperty("about")
			String about; // The user's about text, which appears on their profile. Can be null.

			/**
			 * true if this user can view nsfw content, can be null
			 */
			@JsonProperty("can_see_nsfw")
			Boolean can_see_nsfw; // true if this user can view nsfw content. Can be null.

			/**
			 * datetime of this user's account creation
			 */
			@JsonProperty("created")
			String created; // Datetime of this user's account creation.

			/**
			 * the user's email address, requires certain scopes to access, see the scopes section of this documentation
			 */
			@JsonProperty("email")
			String email; // The user's email address. Requires certain scopes to access. See the scopes section of this documentation.

			/**
			 * first name, can be null
			 */
			@JsonProperty("first_name")
			String first_name; // First name. Can be null.

			/**
			 * combined first and last name
			 */
			@JsonProperty("full_name")
			String full_name; // Combined first and last name.

			/**
			 * true if the user has chosen to keep private which creators they pledge to, can be null
			 */
			@JsonProperty("hide_pledges")
			Boolean hide_pledges; // true if the user has chosen to keep private which creators they pledge to. Can be null.

			/**
			 * the user's profile picture URL, scaled to width 400px
			 */
			@JsonProperty("image_url")
			String image_url; // The user's profile picture URL, scaled to width 400px.

			/**
			 * true if the user has confirmed their email
			 */
			@JsonProperty("is_email_verified")
			Boolean is_email_verified; // true if the user has confirmed their email.

			/**
			 * last name, can be null
			 */
			@JsonProperty("last_name")
			String last_name; // Last name. Can be null.

			/**
			 * how many posts this user has liked
			 */
			@JsonProperty("like_count")
			Integer like_count; // How many posts this user has liked.

			/**
			 * mapping from user's connected app names to external user id on the respective app
			 */
			@JsonProperty("social_connections") // TODO change to unique class if we require this in the future.
			Object social_connections; // Mapping from user's connected app names to external user id on the respective app

			/**
			 * the user's profile picture url, scaled to a square of size 100x100px
			 */
			@JsonProperty("thumb_url")
			String thumb_url; // The user's profile picture URL, scaled to a square of size 100x100px.

			/**
			 * url of this user's creator or patron profile
			 */
			@JsonProperty("url")
			String url; // URL of this user's creator or patron profile.

			/**
			 * the public "username" of the user, patreon.com/ goes to this user's creator page, non-creator users might not have a vanity, [Deprecated! use campaign.vanity] can be null.
			 */
			@JsonProperty("vanity")
			String vanity; // The public "username" of the user. patreon.com/ goes to this user's creator page. Non-creator users might not have a vanity. [Deprecated! use campaign.vanity] Can be null.
		}
	}

	/**
	 * Provides the application with a toString() of {@link com.patreonshout.patreon.endpoints.objects.User.Links}
	 */
	@ToString
	public class Links {
		/**
		 *
		 */
		@JsonProperty("self")
		public String self;
	}
}
