package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ResetPasswordRequest {

	@NotNull
	@JsonProperty("login_token")
	String loginToken;

	@NotNull
	@JsonProperty("current_password")
	String currentPassword;

	@NotNull
	@JsonProperty("new_password")
	String newPassword;
}
