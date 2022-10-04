package com.patreonshout.rest;

import com.patreonshout.interfaces.WebAccountImpl;
import com.patreonshout.jpa.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebAccountSvc implements WebAccountImpl {

	@Autowired
	WebAccount webAccount;

	// This String object will contain an error message, or be empty if no error occurred
	public String Register(
			@RequestParam(name = "user") String username,
			@RequestParam(name = "pass") String password
	) {
		// TODO: Ensure username and password are sanitized and fit specific requirements
		return webAccount.putAccount(username, password);
	}
}