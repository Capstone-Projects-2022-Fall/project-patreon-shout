package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO that holds the campaign data we want from the Patreon api
 */
@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonRelationshipsV2 {

	@JsonProperty("campaign")
	PatreonCampaignV2 campaign;
}
