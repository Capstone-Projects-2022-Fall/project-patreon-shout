package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.jpa.constants.SocialIntegrationName;
import lombok.*;

/**
 * POJO used in requests for the Integration endpoint.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutInstagramPostDetailsRequest {

	/**
	 * A {@link String} login token that belongs to a {@link WebAccount}
	 */
	@JsonProperty("login_token")
	String loginToken;

	/**
	 * HTTP Image URL
	 */
	@JsonProperty("instagram_image_url")
	String imageUrl;

	/**
	 * Gaussian Blur amount
	 */
	@JsonProperty("instagram_blur_amount")
	String blurAmount;

	/**
	 * HEX color value for the image message's color
	 */
	@JsonProperty("instagram_message_color")
	String messageColor;
}
