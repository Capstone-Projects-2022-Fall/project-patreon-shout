package com.patreonshout.endpoints;

import com.patreonshout.jpa.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class Test {

	@Autowired
	WebAccount webAccount;

	@GetMapping("/register")
	public int Register(
			@RequestParam(name = "user") String username,
			@RequestParam(name = "pass") String password
	) {
		// TODO: Ensure username and password are sanitized and fit specific requirements
		return webAccount.putAccount(username, password);
	}
}