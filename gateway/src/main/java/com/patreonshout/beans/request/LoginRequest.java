package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  Jackson Serialized object, used when a login request is received
 */
@Getter
public class LoginRequest implements Serializable {

	/**
	 * The desired username for the given request
	 */
	@NotNull
	@JsonProperty("userName")
	private String username;

	/**
	 * The desired password for the given request
	 */
	@NotNull
	@JsonProperty("userPassword")
	private String password;
}
