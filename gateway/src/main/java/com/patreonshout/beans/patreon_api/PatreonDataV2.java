package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * POJO that represents the "Data" object returned by Patreon API V2
 */
@Data
public class PatreonDataV2 implements Serializable {

	/**
	 * An anonymous object that, when sent from Patreon API V2, can (seemingly) be any Patreon V2 object except
	 * {@link PatreonDataV2}
	 *
	 * This object <b>must</b> be converted to the desired object via {@link ObjectMapper}.
	 */
	@JsonProperty("attributes")
	Object attributes;

	@JsonProperty("id")
	int id;

//	@JsonProperty("relationships")
//	PatreonRelationshipsV2 relationships;

	@JsonProperty("type")
	String type;
}
