package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import lombok.Getter;

@Getter
public class PostWebhookRequest {
	@JsonProperty("login_token")
	private String loginToken;

	@JsonProperty("integration")
	SocialIntegrationName socialIntegrationName;
}