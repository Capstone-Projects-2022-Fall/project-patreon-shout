package com.patreonshout.rest;

import com.patreonshout.PSException;
import com.patreonshout.beans.response.BaseResponse;
import com.patreonshout.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Parental service that any {@link RestController} objects must extend. Contains controller-wide Exception Handler.
 */
public class BaseSvc {

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
				return ResponseUtil.Generic(HttpStatus.CONFLICT, "Duplicate primary key.");
			case 1452:
				return ResponseUtil.Generic(HttpStatus.CONFLICT, "Foreign key constraint failed.");
			default:
				ex.printStackTrace();
				return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "SQLIntegrityConstraintViolationException unknown SQL code");
		}
	}

	@ExceptionHandler(value = PSException.class)
	public ResponseEntity<?> catchCustomException(PSException ex) {
		return ResponseUtil.Generic(ex.getHttpStatus(), ex.getMessage());
	}
}
