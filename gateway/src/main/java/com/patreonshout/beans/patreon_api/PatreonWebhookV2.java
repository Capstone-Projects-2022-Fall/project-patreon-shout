package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class PatreonWebhookV2 {

	@JsonProperty("data")
	Data[] data;

	@JsonProperty("meta")
	PatreonMetaV2 meta;

	@lombok.Data
	public static class Data {

		@JsonProperty("attributes")
		Attributes attributes;

		public static class Attributes {
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

		@JsonProperty("id")
		int id;

		@JsonProperty("type")
		String type;
	}
}
