package com.patreonshout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class OAuthTest {

	@GetMapping("/webhook")
	public String WebhookReceiver() {
		return "Hello from /webhook!";
	}
}
