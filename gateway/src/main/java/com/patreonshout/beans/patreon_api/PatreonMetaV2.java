package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to hold the pagination meta data returned from the Patreon api
 */
@Data
public class PatreonMetaV2 {

	/**
	 * holds the Patreon pagination info
	 */
	@JsonProperty("pagination")
	PatreonPaginationV2 pagination;
}
