package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatreonDataArrayEntryV2 {

	@JsonProperty("attributes")
	Object attributes;

	@JsonProperty("id")
	int id;

	@JsonProperty("type")
	String type;
}
