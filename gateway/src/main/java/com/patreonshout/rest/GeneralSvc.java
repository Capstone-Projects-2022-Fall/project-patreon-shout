package com.patreonshout.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

public class GeneralSvc {

	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> catchSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		return createResponse(HttpStatus.CONFLICT, ex);
	}

	private ResponseEntity<?> createResponse(HttpStatus httpStatus, Exception ex) {
		return ResponseEntity
				.status(httpStatus)
				.body(Map.of(
						"status", httpStatus.value(),
						"reason", ex.getMessage()
				));
	}
}
