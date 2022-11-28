package com.patreonshout.beans.facebook_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO to store the data received from Facebook sending us an HTTP request when a user OAuths
 */
@Data
public class FacebookUserPages {
	/**
	 * Container for all User Pages retrieved
	 */
	@JsonProperty("data")
	DataObject[] dataObjects;

	/**
	 * POJO to store the Facebook user Page data
	 */
	@Data
	public static class DataObject {
		/**
		 * Facebook User Page category
		 */
		@JsonProperty("category")
		String category;

		/**
		 * Facebook User Page name
		 */
		@JsonProperty("name")
		String name;

		/**
		 * Facebook User Page ID
		 */
		@JsonProperty("id")
		String id;
	}
}
