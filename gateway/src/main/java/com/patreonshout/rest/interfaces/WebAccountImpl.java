package com.patreonshout.rest.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Interface for endpoints relating to the webaccounts table on the database
 */
@RequestMapping(value = "/webaccount")
@Tag(name = "Web Account Service",
		description = "Handles all Web Account related tasks for the database.")
public interface WebAccountImpl {

	/**
	 * Swagger endpoint documentation annotations and expected argument for endpoint
	 *
	 * @param username is the username for the {@link com.patreonshout.jpa.WebAccount}
	 * @param password is the password for the {@link com.patreonshout.jpa.WebAccount}
	 * @return {@link HttpStatus} 200 if the registration was successful
	 * {@link HttpStatus} 409 if the account already exists
	 */
	@GetMapping("/register")
	@Operation(summary = "Registers WebAccounts during PatreonShout sign up")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "WebAccount has been registered",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "409",
					description = "Username already exists",
					content = {@Content(mediaType = "application/json")})
	})
	HttpStatus Register(
			@RequestParam(name = "user") String username,
			@RequestParam(name = "pass") String password
	);
}
