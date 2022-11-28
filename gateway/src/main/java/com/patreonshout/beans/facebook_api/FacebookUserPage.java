package com.patreonshout.beans.facebook_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to store the data received from Facebook sending us an HTTP request when a user OAuths
 */
@Data
public class FacebookUserPage {
	/**
	 * Facebook User Page ID
	 */
	@JsonProperty("id")
	String id;

	/**
	 * Instagram Business Account container
	 */
	@JsonProperty("instagram_business_account")
	InstagramBusinessAccount instagramBusinessAccount;

	/**
	 * POJO to store Instagram Business Account data
	 */
	@Data
	public static class InstagramBusinessAccount {
		@JsonProperty("id")
		String id;
	}
}
