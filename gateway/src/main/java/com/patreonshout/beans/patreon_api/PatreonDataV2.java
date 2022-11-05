package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

@Data
public class PatreonDataV2 implements Serializable {

	/**
	 * An anonymous object that
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
