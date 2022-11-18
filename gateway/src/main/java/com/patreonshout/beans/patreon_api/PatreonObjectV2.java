package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to encapsulate the {@link com.patreonshout.beans.patreon_api.PatreonDataV2} object returned from the Patreon api
 */
@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonObjectV2 {

	/**
	 * data holds a single {@link com.patreonshout.beans.patreon_api.PatreonDataV2} object requested from the Patreon api
	 */
	@JsonProperty("data")
	PatreonDataV2 data;
}
