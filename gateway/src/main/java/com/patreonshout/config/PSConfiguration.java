package com.patreonshout.config;

import com.patreon.PatreonOAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Patreon Shout Configuration
 */
@Configuration
@EnableScheduling
@Import({JPAConfiguration.class})
public class PSConfiguration {

	/**
	 * clientId is the Patreon id of our client we use during OAuth for users of Patreon Shout
	 */
	@Value("${patreonshout.client.id}") String clientId;
	/**
	 * clientSecret is the Patreon secret of our client we use during OAuth for users of Patreon Shout
	 */
	@Value("${patreonshout.client.secret}") String clientSecret;
	/**
	 * redirectUri is the Uri we give to Patreon that lets them redirect information to our endpoints
	 */
	@Value("${patreonshout.client.redirecturi}") String redirectUri;

	/**
	 * Creates a new {@link com.patreon.PatreonOAuth} object using the clientId, clientSecret, and redirectUri
	 *
	 * @return a new PatreonOAuth object
	 */
	@Bean
	public PatreonOAuth oauthClient() {
		return new PatreonOAuth(clientId, clientSecret, redirectUri);
	}
}