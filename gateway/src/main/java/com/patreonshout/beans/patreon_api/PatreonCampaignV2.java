package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * POJO to store the data received from Patreon sending us an http request
 */
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonCampaignV2 {

	/**
	 * vanity is the content creator's url name
	 */
	@JsonProperty("vanity")
	String vanity;

	/**
	 * data represents the "Data" object returned by Patreon API V2
	 */
	@JsonProperty("data")
	PatreonDataV2 data;
}
