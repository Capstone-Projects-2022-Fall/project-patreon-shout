package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatreonObjectArrayV2 {

	@JsonProperty("data")
	PatreonDataV2[] data;

	@JsonProperty("meta")
	PatreonMetaV2 meta;
}
