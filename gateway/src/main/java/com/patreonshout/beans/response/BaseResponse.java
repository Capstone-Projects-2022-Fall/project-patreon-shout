package com.patreonshout.beans.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * POJO that returns a status and message for an http request
 */
@Setter
@Getter
@RequiredArgsConstructor
public class BaseResponse {

	/**
	 * status is the status of the http request
	 */
	private final int status;

	/**
	 * message is the message response of the http request
	 */
	private final String message;

}
