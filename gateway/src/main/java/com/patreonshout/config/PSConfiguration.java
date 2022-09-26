package com.patreonshout.config;

import com.patreon.PatreonOAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Import({JPAConfiguration.class})
public class PSConfiguration {

	@Value("${patreonshout.client.id}") String clientId;
	@Value("${patreonshout.client.secret}") String clientSecret;
	@Value("${patreonshout.client.redirecturi}") String redirectUri;

	@Bean
	public PatreonOAuth oauthClient() {
		return new PatreonOAuth(clientId, clientSecret, redirectUri);
	}
}