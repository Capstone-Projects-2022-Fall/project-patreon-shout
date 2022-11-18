package com.patreonshout.beans.patreon_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO that holds webhook information needed for debugging when using the Patreon api to send post information to our endpoint on post publish
 */
@lombok.Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatreonWebhookV2 {

	/**
	 * lastAttemptedAt is the last date that the webhook was attempted or used
	 */
	@JsonProperty("last_attempted_at")
	String lastAttemptedAt;

	/**
	 * numConsecutiveTimesFailed is the number of times the webhook has failed consecutively, when in an error state
	 */
	@JsonProperty("num_consecutive_times_failed")
	String numConsecutiveTimesFailed;

	/**
	 * paused is true of the webhook is paused as a result of repeated failed attempts to post to the set uri, false to attempt to re-enable a previously failing webhook
	 */
	@JsonProperty("paused")
	Boolean paused;

	/**
	 * secret is the secret used to sign your webhook message body so you can validate authenticity upon receipt
	 */
	@JsonProperty("secret")
	String secret;

	/**
	 * triggers is a list of events that will triger the webhook
	 */
	@JsonProperty("triggers")
	String[] triggers;

	/**
	 * uri is the fullly qualified uri where webhook will be sent
	 */
	@JsonProperty("uri")
	String uri;
}
