package com.patreonshout.config;

import com.patreon.PatreonOAuth;
import com.patreonshout.config.credentials.InstagramCredentials;
import com.patreonshout.config.credentials.PatreonCredentials;
import com.patreonshout.config.credentials.TwitterCredentials;
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
@Import({JPAConfiguration.class, CORSConfiguration.class})
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
	 * twitterClientId is the client id of our Twitter app
	 */
	@Value("${twitter.client.id}") String twitterClientId;

	/**
	 * twitterClientSecret is the client secret of our Twitter app
	 */
	@Value("${twitter.client.secret}") String twitterClientSecret;

	/**
	 * twitterRedirectUri is the redirect ui of our Twitter app
	 */
	@Value("${twitter.client.redirecturi}") String twitterRedirectUri;

	/**
	 * twitterClientId is the client id of our Twitter app
	 */
	@Value("${instagram.client.id}") String instagramClientId;

	/**
	 * twitterClientSecret is the client secret of our Twitter app
	 */
	@Value("${instagram.client.secret}") String instagramClientSecret;

	/**
	 * twitterRedirectUri is the redirect ui of our Twitter app
	 */
	@Value("${instagram.client.redirecturi}") String instagramRedirectUri;


	/**
	 * Creates a new {@link com.patreon.PatreonOAuth} object using the clientId, clientSecret, and redirectUri
	 *
	 * @return a new PatreonOAuth object
	 */
	@Bean
	public PatreonOAuth oauthClient() {
		return new PatreonOAuth(clientId, clientSecret, redirectUri + "/receivers/patreon/oauth");
	}

	/**
	 * Creates a new {@link com.patreonshout.config.credentials.TwitterCredentials} object holding our twitter application credentials
	 *
	 * @return a new TwitterCredentials object
	 */
	@Bean
	public TwitterCredentials twitterClient() {
		return new TwitterCredentials(twitterClientId, twitterClientSecret, twitterRedirectUri);
	}

	/**
	 * Creates a new {@link com.patreonshout.config.credentials.InstagramCredentials} object holding our Instagram application credentials
	 *
	 * @return {@link com.patreonshout.config.credentials.InstagramCredentials} object
	 */
	@Bean
	public InstagramCredentials instagramClient() {
		return new InstagramCredentials(instagramClientId, instagramClientSecret, instagramRedirectUri);
	}

	/**
	 * Creates a new {@link com.patreonshout.config.credentials.PatreonCredentials} object holding our Patreon client credentials
	 *
	 * @return a new PatreonCredentials object
	 */
	@Bean
	public PatreonCredentials patreonCredentials() {
		return new PatreonCredentials(clientId, clientSecret, redirectUri);
	}
}