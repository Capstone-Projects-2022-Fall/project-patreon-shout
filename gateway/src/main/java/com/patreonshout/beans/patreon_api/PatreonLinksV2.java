package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to hold the links returned from the Patreon api
 */
@Data
public class PatreonLinksV2 {

	/**
	 * self holds previous links used for a specific user
	 */
	@JsonProperty("self")
	String self;
}
