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

		@JsonProperty("id")
		String id;

//      TODO: Commented out for now as there seem to be circular relationships and I don't know if we'll need it yet
//		@JsonProperty("relationships")
//		Relationships relationships;

		@JsonProperty("type")
		String type;

		@lombok.Data
		public static class Attributes {

			@JsonProperty("vanity")
			String vanity;
		}
	}
}
