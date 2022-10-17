package com.patreonshout.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security Configuration
 */
@Configuration
public class SecurityConfiguration {

	/**
	 * Allows encoding passwords with {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
	 *
	 * @return {@link PasswordEncoderFactories} object that defaults to BCrypt encoding
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public String SHA1Encoder(String text) {
		return DigestUtils.shaHex(text);
	}
}