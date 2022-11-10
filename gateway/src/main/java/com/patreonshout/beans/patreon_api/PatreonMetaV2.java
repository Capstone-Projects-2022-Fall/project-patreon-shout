package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatreonMetaV2 {

	@JsonProperty("pagination")
	PatreonPaginationV2 pagination;
}
