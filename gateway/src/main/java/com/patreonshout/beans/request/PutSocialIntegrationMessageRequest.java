package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO used in requests for the SocialIntegrationMessages endpoint.
 */
@Setter
@Getter
public class PutSocialIntegrationMessageRequest {

	/**
	 * A {@link String} login token that belongs to a {@link WebAccount}
	 */
	@JsonProperty("login_token")
	String loginToken;

	/**
	 * An {@link SocialIntegrationName} that will contain the type of social platform desired for this request
	 */
	@JsonProperty("integration_name")
	SocialIntegrationName socialIntegrationName;

	/**
	 * A {@link String} that contains the desired <b>public</b> Patreon post format
	 */
	@JsonProperty("public_message")
	String publicMessage;

	/**
	 * A {@link String} that contains the desired <b>private</b> Patreon post format
	 */
	@JsonProperty("private_message")
	String privateMessage;
}
