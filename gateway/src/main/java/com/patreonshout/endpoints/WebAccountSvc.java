package com.patreonshout.endpoints;

import com.patreonshout.jpa.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class WebAccountSvc {

	@Autowired
	WebAccount webAccount;

	// This String object will contain an error message, or be empty if no error occurred
	@GetMapping("/register")
	public HttpStatus Register(
			@RequestParam(name = "user") String username,
			@RequestParam(name = "pass") String password
	) {
		// TODO: Ensure username and password are sanitized and fit specific requirements
		return webAccount.putAccount(username, password);
	}
}