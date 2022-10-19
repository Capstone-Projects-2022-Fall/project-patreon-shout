package com.patreonshout.utils;

import com.patreonshout.beans.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * POJO containing functions to assist and generate endpoint JSON output
 */
public class ResponseUtil {

	/**
	 * Create a JSON response to output.
	 *
	 * @param httpStatus HTTP code specifying the error
	 * @param message    Reasoning for the provided HTTP code
	 * @return {@link ResponseEntity}
	 */
	public static ResponseEntity<?> Generic(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(
				new BaseResponse(httpStatus.value(), message),
				httpStatus
		);
	}
}
