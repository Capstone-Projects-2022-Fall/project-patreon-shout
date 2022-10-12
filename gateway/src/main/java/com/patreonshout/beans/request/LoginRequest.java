package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 *  Jackson Serialized object, used when a login request is received
 */

@Getter
public class LoginRequest {

	/**
	 * The desired username for the given request
	 */
	@NotNull
	@JsonProperty("user")
	private String username;

	/**
	 * The desired password for the given request
	 */
	@NotNull
	@JsonProperty("pass")
	private String password;
}
