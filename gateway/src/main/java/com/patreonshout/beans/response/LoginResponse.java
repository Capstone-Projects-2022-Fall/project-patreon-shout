package com.patreonshout.beans.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponse {

	/**
	 * User login token.
	 */
	private final String token;

}
