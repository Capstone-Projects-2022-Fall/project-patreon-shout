package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO that holds the data and meta information from the Patreon api
 */
@Data
public class PatreonObjectArrayV2 {

	/**
	 * data holds the information requested from the Patreon api
	 */
	@JsonProperty("data")
	PatreonDataV2[] data;

	/**
	 * meta holds the meta information requested from the patreon api
	 */
	@JsonProperty("meta")
	PatreonMetaV2 meta;
}
