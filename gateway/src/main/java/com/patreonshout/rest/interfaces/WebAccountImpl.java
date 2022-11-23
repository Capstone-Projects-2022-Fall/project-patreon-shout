package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.SocialIntegration;
import com.patreonshout.beans.SocialIntegrationMessages;
import com.patreonshout.beans.request.*;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.jpa.WebAccountFunctions;
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
	 * Endpoint that will create a new {@link WebAccountFunctions} into the database.
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
	HttpStatus Register(@RequestBody RegisterRequest registerRequest) throws PSException;

	/**
	 * Endpoint that will create a new {@link WebAccountFunctions} into the database
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
	 * Endpoint that will delete a login token from a {@link WebAccountFunctions} in the database
	 *
	 * @param loginToken Login token to delete from the database
	 * @return {@link HttpStatus#OK} if successful
	 */
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
	 * @param putSocialIntegrationRequest {@link PutSocialIntegrationRequest} object that contains {@link WebAccountFunctions} and request details.
	 * @return {@link HttpStatus#OK} if successful, {@link HttpStatus#CONFLICT} if the provided {@link PutSocialIntegrationRequest}
	 * does not contain a valid {@link WebAccount} ID number.
	 */
	@PutMapping("/socialintegration")
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
	HttpStatus PutSocialIntegration(@RequestBody PutSocialIntegrationRequest putSocialIntegrationRequest) throws PSException;

	/**
	 * Endpoint that returns the social integration tokens and webhook URLs for social platforms for a given login token
	 *
	 * @param loginToken Login token belonging to a {@link WebAccount}
	 * @return {@link SocialIntegration} Social integration tokens and webhook URLs
	 */
	@GetMapping("/socialintegration")
	@Operation(summary = "Acquires social integrations")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Integrations returned",
					content = {@Content(mediaType = "application/json")})
	})
	ResponseEntity<?> GetSocialIntegration(@RequestParam(name = "login_token") String loginToken) throws PSException;

	/**
	 * Endpoint that allows updating Patreon post redirect messages for social platforms.
	 *
	 * @param putSocialIntegrationMessageRequest {@link PutSocialIntegrationMessageRequest} object that contains {@link WebAccountFunctions} and request details.
	 * @return {@link HttpStatus#OK} if successful, {@link HttpStatus#CONFLICT} if the provided {@link PutSocialIntegrationRequest}
	 * does not contain a valid {@link WebAccount} ID number.
	 */
	@PutMapping("/socialintegrationmessages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Integration messages have been saved",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "409",
					description = "WebAccount ID does not exist",
					content = {@Content(mediaType = "application/json")})
	})
	@ResponseStatus(code = HttpStatus.OK, reason = "Data saved successfully")
	HttpStatus PutSocialIntegrationMessages(@RequestBody PutSocialIntegrationMessageRequest putSocialIntegrationMessageRequest) throws PSException;

	/**
	 * Returns the custom Patreon post redirect messages for all social platforms
	 *
	 * @param loginToken Login token belonging to a {@link WebAccount}
	 * @return {@link SocialIntegrationMessages} Social integration public and private messages
	 */
	@GetMapping("/socialintegrationmessages")
	@Operation(summary = "Acquires custom Patreon post redirect messages")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Custom Patreon post redirect messages",
					content = {@Content(mediaType = "application/json")})
	})
	ResponseEntity<?> GetSocialIntegrationMessages(@RequestParam(name = "login_token") String loginToken) throws PSException;

	/**
	 * Endpoint that allows retrieval of Patreon access and refresh tokens for a {@link WebAccount} containing the given
	 * login token
	 *
	 * @param loginToken Login token belonging to a {@link WebAccount}
	 * @return {@link WebAccount} Patreon access and refresh token
	 */
	@GetMapping("/patreontokens")
	@Operation(summary = "Retrieves Patreon access and refresh tokens for a WebAccount containing the given login token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Tokens retrieved",
					content = {@Content(mediaType = "application/json")})
	})
	ResponseEntity<?> GetPatreonTokens(@RequestParam(name = "login_token") String loginToken) throws PSException;

	/**
	 * Endpoint that allows {@link WebAccount} password resetting.
	 *
	 * @param resetPasswordRequest {@link ResetPasswordRequest} object generated from a user calling this endpoint
	 * @return {@link HttpStatus#OK} if successful, {@link HttpStatus#UNAUTHORIZED} if the given oldPassword did not
	 * match any current passwords belonging to the {@link WebAccount} found with the given loginToken
	 */
	@PutMapping("/resetpassword")
	@Operation(summary = "Retrieves Patreon access and refresh tokens for a WebAccount containing the given login token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Tokens retrieved",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "401",
					description = "Tokens retrieved",
					content = {@Content(mediaType = "application/json")})
	})
	ResponseEntity<?> ResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws PSException;
}
