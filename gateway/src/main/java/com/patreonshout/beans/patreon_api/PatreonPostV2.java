package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatreonPostV2 {
	@JsonProperty("app_id")
	String appId;

	@JsonProperty("app_status")
	String appStatus;

	@JsonProperty("content")
	String content;

	@JsonProperty("embed_data")
	String embedData;

	@JsonProperty("embed_url")
	String embedUrl;

	@JsonProperty("is_paid")
	String isPaid;

	@JsonProperty("is_public")
	Boolean isPublic;

	@JsonProperty("published_at")
	String publishedAt;

	@JsonProperty("title")
	String title;

	@JsonProperty("url")
	String url;
}
