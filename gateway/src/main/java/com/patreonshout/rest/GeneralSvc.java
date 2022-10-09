package com.patreonshout.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

/**
 * Parental service that any {@link RestController} objects must extend. Contains controller-wide Exception Handler.
 */
public class GeneralSvc {

	/**
	 * Handles every {@link SQLIntegrityConstraintViolationException} exception thrown by controllers.
	 *
	 * @param ex Auto-provided {@link SQLIntegrityConstraintViolationException} object.
	 * @return Jackson JSON compatible {@link ResponseEntity} containing an HTTP error code and message.
	 */
	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> catchSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		switch (ex.getErrorCode()) {
			case 1062: // Duplicate entry for key
				return createResponse(HttpStatus.CONFLICT, "Duplicate primary key.");
			case 1452:
				return createResponse(HttpStatus.CONFLICT, "Foreign key constraint failed.");
			default:
				ex.printStackTrace();
				return createResponse(HttpStatus.BAD_REQUEST, "SQLIntegrityConstraintViolationException unknown SQL code");
		}
	}

	/**
	 * Create a JSON response to output.
	 *
	 * @param httpStatus HTTP code specifying the error
	 * @param message    Reasoning for the provided HTTP code
	 * @return {@link ResponseEntity}
	 */
	private ResponseEntity<?> createResponse(HttpStatus httpStatus, String message) {
		return ResponseEntity
				.status(httpStatus)
				.body(Map.of(
						"status", httpStatus.value(),
						"message", message
				));
	}
}
