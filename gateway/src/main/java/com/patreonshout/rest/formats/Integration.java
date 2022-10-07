package com.patreonshout.rest.formats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.beans.WebAccountBean;
import com.patreonshout.jpa.constants.IntegrationType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Integration {
	@JsonProperty("webaccount")
	WebAccountBean webAccountBean;

	@JsonProperty("integration_type")
	IntegrationType integrationType;

	@JsonProperty("data")
	String data;
}
