package com.patreonshout.beans.facebook_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to store the data received from Facebook sending us an HTTP request when a user OAuths
 */
@Data
public class FacebookAccessToken {

	/**
	 * Facebook access token
	 */
	@JsonProperty("access_token")
	String accessToken;

	/**
	 * Access token type, typically "BEARER"
	 */
	@JsonProperty("token_type")
	String tokenType;

	/**
	 * Expiration time in seconds
	 */
	@JsonProperty("expires_in")
	Long expiresIn;
}
