package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Class to store the data received from Patreon sending us an http request
 */
@lombok.Data
public class PatreonCampaignV2 {

	@JsonProperty("data")
	Data[] data;

	@lombok.Data
	public static class Data {

		@JsonProperty("attributes")
		Attributes attributes;

		@lombok.Data
		public static class Attributes {

			@JsonProperty("vanity")
			String vanity;
		}
	}
}
