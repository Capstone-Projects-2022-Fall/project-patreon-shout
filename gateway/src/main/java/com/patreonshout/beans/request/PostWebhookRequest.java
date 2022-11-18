package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import lombok.Getter;

/**
 *  Jackson Serialized object, used when a social integration is trying to be setup to cross post(not currently used)
 */
@Getter
public class PostWebhookRequest {

	/**
	 * loginToken is the user's session login token
	 */
	@JsonProperty("login_token")
	private String loginToken;

	/**
	 * socialIntegrationName is the type of social integration we are trying to setup cross posting for
	 */
	@JsonProperty("integration")
	SocialIntegrationName socialIntegrationName;
}