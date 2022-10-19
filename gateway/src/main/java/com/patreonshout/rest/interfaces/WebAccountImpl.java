package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.IntegrationRequestBean;
import com.patreonshout.beans.WebAccountBean;
import com.patreonshout.beans.request.LoginRequest;
import com.patreonshout.beans.request.RegisterRequest;
import com.patreonshout.jpa.WebAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/**
	 * Endpoint that will create a new {@link WebAccount} into the database
	 *
	 * @param loginRequest is the json request
	 * @return a token signifying valid login or no token signifying invalid login
	 */
	@PostMapping("/login")
	@Operation(summary = "Retrieves a login token given valid WebAccount details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Token retrieved",
					content = {@Content(mediaType = "application/json")})
	})
	ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest) throws PSException;

	/**
	 * Endpoint that will delete a login token from a {@link WebAccount} in the database
	 *
	 * @param loginToken Login token to delete from the database
	 * @return {@link HttpStatus#OK} if successful
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/logout")
	@Operation(summary = "Deletes a login token from a WebAccount if it exists")
	@ApiResponses(
			@ApiResponse(responseCode = "200",
					description = "Token deleted, if it existed",
					content = {@Content(mediaType = "application/json")})
	)
	ResponseEntity<?> Logout(@RequestParam(name = "login_token") String loginToken);

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

	/**
	 * Endpoint that allows retrieval of Patreon access and refresh tokens for a {@link WebAccount} containing the given
	 * login token
	 *
	 * @param loginToken Login token belonging to a {@link WebAccount}
	 * @return {@link WebAccountBean} containing a valid {@link WebAccount} ID and its respective login, refresh and
	 * access tokens
	 */
	@GetMapping("/patreontokens")
	@Operation(summary = "Retrieves Patreon access and refresh tokens for a WebAccount containing the given login token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Tokens retrieved",
					content = {@Content(mediaType = "application/json")})
	})
	ResponseEntity<?> GetPatreonTokens(@RequestParam(name = "login_token") String loginToken) throws PSException;
}
