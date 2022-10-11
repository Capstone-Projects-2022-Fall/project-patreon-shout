package com.patreonshout.rest.interfaces;

import com.patreonshout.beans.IntegrationRequestBean;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.jpa.WebAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Interface for endpoints relating to webaccount activities
 */
@RequestMapping(value = "/webaccount")
@Tag(name = "Web Account Service",
		description = "Handles all Web Account related tasks for the database.")
public interface WebAccountImpl {

	/**
	 * Endpoint that will create a new {@link WebAccount} into the database.
	 *
	 * @param registerRequest {@link RegisterRequest} object that contains the desired login details for a new
	 * @return {@link HttpStatus#OK} if the registration was successful, {@link HttpStatus#CONFLICT} if the account
	 * already exists
	 */
	@PostMapping("/register")
	@Operation(summary = "Registers WebAccounts during PatreonShout sign up")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "WebAccount has been registered",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "409",
					description = "Username already exists",
					content = {@Content(mediaType = "application/json")})
	})
	HttpStatus Register(@RequestBody RegisterRequest registerRequest);

	@PostMapping("/login")
	@Operation(summary = "Retrieves a login token given valid WebAccount details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Token retrieved",
					content = {@Content(mediaType = "application/json")})
	})
	HttpStatus Login(@RequestBody LoginRequest loginRequest);

	/**
	 * Endpoint that allows registering, updating or deleting integrations for social platforms.
	 *
	 * @param integrationRequestBean {@link IntegrationRequestBean} object that contains {@link WebAccount} and request details.
	 * @return {@link HttpStatus#OK} if successful, {@link HttpStatus#CONFLICT} if the provided {@link IntegrationRequestBean}
	 * does not contain a valid {@link com.patreonshout.beans.WebAccountBean} ID number.
	 */
	@PutMapping("/integration")
	@Operation(summary = "Registers WebAccounts during PatreonShout sign up")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Integration has been saved",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "409",
					description = "WebAccount ID does not exist",
					content = {@Content(mediaType = "application/json")})
	})
	@ResponseStatus(code = HttpStatus.OK, reason = "Data saved successfully")
	HttpStatus Integration(@RequestBody IntegrationRequestBean integrationRequestBean);
}
