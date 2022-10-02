package com.patreonshout.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class WebAccount {

	@Autowired
	WebAccountRepository webAccountRepository;

	public String putAccount(String username, String password) {
		try {
			webAccountRepository.putAccount(username, password);
		} catch (DataIntegrityViolationException ex) { // Username already exists in webaccounts table
			return "Username taken.";
		}

		return ""; // No error
	}
}
