package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * POJO that represents the "Data" object returned by Patreon API V2
 */
@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonDataV2 implements Serializable {

	/**
	 * An anonymous object that, when sent from Patreon API V2, can (seemingly) be any Patreon V2 object except
	 * {@link PatreonDataV2}
	 *
	 * This object <b>must</b> be converted to the desired object via {@link ObjectMapper}.
	 */
	@JsonProperty("attributes")
	Object attributes;

	/**
	 * id holds Patreon api id info
	 */
	@JsonProperty("id")
	int id;

	/**
	 * relationships represent the object relationships to other objects in the Patreon api
	 */
	@JsonProperty("relationships")
	PatreonRelationshipsV2 relationships;

	/**
	 * type holds Patreon api type info
	 */
	@JsonProperty("type")
	String type;
}
