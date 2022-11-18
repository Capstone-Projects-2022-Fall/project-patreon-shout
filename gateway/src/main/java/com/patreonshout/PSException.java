package com.patreonshout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception for any database or internal server errors
 */
@Getter
@AllArgsConstructor
public class PSException extends Exception {

	/**
	 * httpStatus returned to http request as the response
	 */
	private final HttpStatus httpStatus;

	/**
	 * message returned to http request as the response
	 */
	private final String message;
}
