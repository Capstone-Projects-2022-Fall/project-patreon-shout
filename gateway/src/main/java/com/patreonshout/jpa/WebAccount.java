package com.patreonshout.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebAccount {

	@Autowired
	WebAccountRepository webAccountRepository;

	public int putAccount(String username, String password) {
		return webAccountRepository.putAccount(username, password);
	}
}
