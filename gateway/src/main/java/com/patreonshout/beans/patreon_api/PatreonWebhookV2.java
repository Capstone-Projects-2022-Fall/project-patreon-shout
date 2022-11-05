package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class PatreonWebhookV2 {

	@JsonProperty("data")
	Data[] data;

	@JsonProperty("meta")
	Meta meta;

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

	@lombok.Data
	public static class Meta {
		@lombok.Data
		public static class Pagination {
			@JsonProperty("total")
			int total;
		}
	}

	@lombok.Data
	public static class Links {
		@JsonProperty("self")
		String self;
	}
}
