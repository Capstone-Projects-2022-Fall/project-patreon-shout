package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatreonLinksV2 {

	@JsonProperty("self")
	String self;
}
