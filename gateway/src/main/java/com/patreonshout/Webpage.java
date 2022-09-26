package com.patreonshout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class Webpage {
	@GetMapping("")
	public String index() {
		return "Index page for webhook tester.";
	}
}
