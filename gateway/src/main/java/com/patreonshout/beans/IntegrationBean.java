package com.patreonshout.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.jpa.constants.IntegrationType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IntegrationBean {
	@JsonProperty("webaccount")
	WebAccountBean webaccount;

	@JsonProperty("integration_type")
	IntegrationType integrationType;

	@JsonProperty("data")
	String data;
}
