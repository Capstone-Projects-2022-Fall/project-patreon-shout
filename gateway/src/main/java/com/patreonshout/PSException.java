package com.patreonshout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class PSException extends Exception {
	private final HttpStatus httpStatus;
	private final String message;
}
