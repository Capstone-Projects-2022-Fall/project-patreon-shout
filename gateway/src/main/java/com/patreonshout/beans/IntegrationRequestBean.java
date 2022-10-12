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
	 * webaccount is a {@link WebAccountBean} that will contain only the ID field filled as required for database entries
	 */
	@JsonProperty("webaccount")
	WebAccountBean webaccount;

	/**
	 * integrationType is an {@link IntegrationType} that will contain the type of social platform desired for this
	 * request
	 */
	@JsonProperty("integration_type")
	IntegrationType integrationType;

	/**
	 * data is a {@link String} that will contain either a webhook URL or a token
	 */
	@JsonProperty("data")
	String data;
}
