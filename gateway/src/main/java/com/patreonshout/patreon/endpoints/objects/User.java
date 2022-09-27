package com.patreonshout.patreon.endpoints.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import javax.validation.constraints.NotNull;

// User V2, REF: https://docs.patreon.com/#user-v2
@ToString
public class User {
	@JsonProperty("data")
	@NotNull
	public Data data;

	@JsonProperty("links")
	@NotNull
	public Links links;

	@ToString
	public class Data {
		@JsonProperty("id")
		public float id;

		@JsonProperty("type")
		public String type;

		@JsonProperty("attributes")
		public Attributes attributes;

		@ToString
		public class Attributes {

			@JsonProperty("about")
			String about; // The user's about text, which appears on their profile. Can be null.

			@JsonProperty("can_see_nsfw")
			Boolean can_see_nsfw; // true if this user can view nsfw content. Can be null.

			@JsonProperty("created")
			String created; // Datetime of this user's account creation.

			@JsonProperty("email")
			String email; // The user's email address. Requires certain scopes to access. See the scopes section of this documentation.

			@JsonProperty("first_name")
			String first_name; // First name. Can be null.

			@JsonProperty("full_name")
			String full_name; // Combined first and last name.

			@JsonProperty("hide_pledges")
			Boolean hide_pledges; // true if the user has chosen to keep private which creators they pledge to. Can be null.

			@JsonProperty("image_url")
			String image_url; // The user's profile picture URL, scaled to width 400px.

			@JsonProperty("is_email_verified")
			Boolean is_email_verified; // true if the user has confirmed their email.

			@JsonProperty("last_name")
			String last_name; // Last name. Can be null.

			@JsonProperty("like_count")
			Integer like_count; // How many posts this user has liked.

			// TODO change to unique class if we require this in the future.
			@JsonProperty("social_connections")
			Object social_connections; // Mapping from user's connected app names to external user id on the respective app

			@JsonProperty("thumb_url")
			String thumb_url; // The user's profile picture URL, scaled to a square of size 100x100px.

			@JsonProperty("url")
			String url; // URL of this user's creator or patron profile.

			@JsonProperty("vanity")
			String vanity; // The public "username" of the user. patreon.com/ goes to this user's creator page. Non-creator users might not have a vanity. [Deprecated! use campaign.vanity] Can be null.
		}
	}

	@ToString
	public class Links {
		@JsonProperty("self")
		public String self;
	}
}
