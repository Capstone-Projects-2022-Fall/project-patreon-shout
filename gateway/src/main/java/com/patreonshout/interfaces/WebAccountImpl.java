package com.patreonshout.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/webaccount")
@Tag(name = "Web Account Service",
		description = "Handles all Web Account related tasks for the database.")
public interface WebAccountImpl {
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
