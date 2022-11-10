package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Class to store the data received from Patreon sending us an http request
 */
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonCampaignV2 {

	@JsonProperty("vanity")
	String vanity;

	@JsonProperty("data")
	PatreonDataV2 data;
}
