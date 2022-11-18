package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO that holds pagination info returned from the Patreon api
 */
@Data
public class PatreonPaginationV2 {

	/**
	 * total holds the total pages left in the paginated data returned from the Patreon api
	 */
	@JsonProperty("total")
	int total;
}
