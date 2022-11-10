package com.patreonshout.beans.request.receivers.patreon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.beans.patreon_api.PatreonDataV2;
import com.patreonshout.beans.patreon_api.PatreonLinksV2;
import lombok.Data;

@Data
public class WebhookRequest {

	@JsonProperty("data")
	PatreonDataV2 data;

	@JsonProperty("links")
	PatreonLinksV2 links;
}
