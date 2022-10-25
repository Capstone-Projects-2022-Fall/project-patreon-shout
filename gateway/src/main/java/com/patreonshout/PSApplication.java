package com.patreonshout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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