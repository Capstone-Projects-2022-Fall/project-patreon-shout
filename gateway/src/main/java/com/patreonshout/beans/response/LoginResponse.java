package com.patreonshout.beans.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * POJO that holds the login token needed for logging in, is used during the login http request
 */
@Getter
@RequiredArgsConstructor
public class LoginResponse {

	/**
	 * User login token.
	 */
	private final String token;

}
