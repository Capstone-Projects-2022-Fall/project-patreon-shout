package com.patreonshout.beans.request.receivers.patreon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.beans.patreon_api.PatreonDataV2;
import com.patreonshout.beans.patreon_api.PatreonLinksV2;
import lombok.Data;

/**
 * POJO that holds the webhook request information sent from the Patreon api
 */
@Data
public class WebhookRequest {

	/**
	 * data represents the data object returned from the Patreon api
	 */
	@JsonProperty("data")
	PatreonDataV2 data;

	/**
	 * links represent the links object returned from the Patreon api
	 */
	@JsonProperty("links")
	PatreonLinksV2 links;
}
