package com.patreonshout.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot configuration for CORS
 */
@Configuration
public class CORSConfiguration {

	/**
	 * CORS http request origin
	 */
	@Value("${patreonshout.server.cors-origin}")
	String origin;

	/**
	 * Configures CORS for the application
	 *
	 * @return {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurer} that configures CORS
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NotNull CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(origin);
			}
		};
	}
}
