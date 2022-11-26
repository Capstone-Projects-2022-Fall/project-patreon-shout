package com.patreonshout.beans.facebook_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to store the data received from Facebook sending us an HTTP request when a user OAuths
 */
@Data
public class FacebookUserPages {
	@JsonProperty("data")
	DataObject[] dataObjects;

	@Data
	public static class DataObject {
		@JsonProperty("category")
		String category;

		@JsonProperty("name")
		String name;

		@JsonProperty("id")
		String id;
	}
}
