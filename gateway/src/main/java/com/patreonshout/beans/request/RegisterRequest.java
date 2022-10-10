package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Jackson Serialized object, used when a registration request is received
 */
@Getter
public class RegisterRequest implements Serializable {
	/**
	 * The desired username for the given request
	 */
	@NotNull
	@JsonProperty("user")
	String username;

	/**
	 * The desired password for the given request
	 */
	@NotNull
	@JsonProperty("pass")
	String password;
}
