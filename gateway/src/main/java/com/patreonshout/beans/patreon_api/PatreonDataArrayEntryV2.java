package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO that holds data received from the patreon api
 */
@Data
public class PatreonDataArrayEntryV2 {

	/**
	 * An anonymous object that, when sent from Patreon API V2, can (seemingly) be any Patreon V2 object except
	 * {@link PatreonDataV2}
	 *
	 * This object <b>must</b> be converted to the desired object via {@link com.fasterxml.jackson.databind.ObjectMapper}.
	 */
	@JsonProperty("attributes")
	Object attributes;

	/**
	 * id holds Patreon api id info
	 */
	@JsonProperty("id")
	int id;

	/**
	 * type holds Patreon api type info
	 */
	@JsonProperty("type")
	String type;
}
