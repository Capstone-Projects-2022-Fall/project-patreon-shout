package com.patreonshout.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.jpa.constants.IntegrationType;
import lombok.Getter;
import lombok.Setter;

/**
 * POJO used in requests for the Integration endpoint.
 */
@Setter
@Getter
public class IntegrationRequestBean {

	/**
	 * A {@link String} login token that belongs to a {@link WebAccount}
	 */
	@JsonProperty("login_token")
	String loginToken;

	/**
	 * An {@link IntegrationType} that will contain the type of social platform desired for this request
	 */
	@JsonProperty("integration_type")
	IntegrationType integrationType;

	/**
	 * A {@link String} that will contain either a webhook URL or a token
	 */
	@JsonProperty("data")
	String data;
}
