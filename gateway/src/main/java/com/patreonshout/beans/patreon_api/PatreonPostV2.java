package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * POJO that holds post information returned from the Patreon api
 */
@Data
public class PatreonPostV2 {
	/**
	 * appId holds the platform app id
	 */
	@JsonProperty("app_id")
	String appId;

	/**
	 * appStatus holds the processing status of a post
	 */
	@JsonProperty("app_status")
	String appStatus;

	/**
	 * content holds the content of a post
	 */
	@JsonProperty("content")
	String content;

	/**
	 * embedData holds an object containing embed data if media is embedded in a post(Patreon api never returns any embed data)
	 */
	@JsonProperty("embed_data")
	String embedData;

	/**
	 * embedUrl holds the embed media url in a post(Patreon api never returns any embed data)
	 */
	@JsonProperty("embed_url")
	String embedUrl;

	/**
	 * isPaid is true of the post incurs a bill as part of a per-per-post campaign
	 */
	@JsonProperty("is_paid")
	Boolean isPaid;

	/**
	 * isPublic is true of the post is viewable by anyone, false if only for patrons to view
	 */
	@JsonProperty("is_public")
	Boolean isPublic;

	/**
	 * publishedAt is the datetime that the creator most recently published the post
	 */
	@JsonProperty("published_at")
	String publishedAt;

	/**
	 * title is the title of the post
	 */
	@JsonProperty("title")
	String title;

	/**
	 * url is the url of the post
	 */
	@JsonProperty("url")
	String url;
}
