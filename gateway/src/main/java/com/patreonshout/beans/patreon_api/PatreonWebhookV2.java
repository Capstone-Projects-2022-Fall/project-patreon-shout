package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonWebhookV2 {
	@JsonProperty("last_attempted_at")
	String lastAttemptedAt;

	@JsonProperty("num_consecutive_times_failed")
	String numConsecutiveTimesFailed;

	@JsonProperty("paused")
	Boolean paused;

	@JsonProperty("secret")
	String secret;

	@JsonProperty("triggers")
	String[] triggers;

	@JsonProperty("uri")
	String uri;
}
