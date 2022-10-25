package com.patreonshout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Where the Spring Boot application is run
 */
@SpringBootApplication
public class PSApplication {

	/**
	 * Starts the Spring Boot application
	 * @param args are the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PSApplication.class, args);
	}
}