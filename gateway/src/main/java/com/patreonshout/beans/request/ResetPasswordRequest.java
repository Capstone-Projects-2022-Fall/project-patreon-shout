package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patreonshout.beans.WebAccount;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Jackson serialized POJO, used when a login request is received
 */
@Getter
public class ResetPasswordRequest {

	/**
	 * A {@link String} login token that belongs to a {@link WebAccount}
	 */
	@NotNull
	@JsonProperty("login_token")
	String loginToken;

	/**
	 * A {@link String} that should be the {@link WebAccount}'s current password
	 */
	@NotNull
	@JsonProperty("current_password")
	String currentPassword;

	/**
	 * A {@link String} that is the {@link WebAccount}'s desired new password
	 */
	@NotNull
	@JsonProperty("new_password")
	 String newPassword;
}
