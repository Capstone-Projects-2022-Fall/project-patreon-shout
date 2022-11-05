package com.patreonshout.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * SpringBoot configuration for CORS
 */
@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {

	/**
	 * Configures CORS for the application
	 *
	 * @param registry is the {@link org.springframework.web.servlet.config.annotation.CorsRegistry} we want to configure
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE");
	}
}
