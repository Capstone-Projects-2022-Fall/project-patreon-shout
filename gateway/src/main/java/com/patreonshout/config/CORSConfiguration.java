package com.patreonshout.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO: Create description
 */
@Configuration
public class CORSConfiguration {

	/**
	 * TODO: Create description
	 */
	@Value("${patreonshout.server.cors-origin}")
	String origin;

	/**
	 * TODO Create description
	 *
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login").allowedOrigins(origin);
			}
		};
	}
}
