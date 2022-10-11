package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequest {

	@NotNull
	@JsonProperty("user")
	private String username;

	@NotNull
	@JsonProperty("pass")
	private String password;
}
