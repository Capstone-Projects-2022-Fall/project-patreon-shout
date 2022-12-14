package com.patreonshout.patreon.endpoints;

import com.patreonshout.patreon.endpoints.objects.User;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

/**
 * Helps connect to the Patreon OAuth endpoint
 */
@Component
public class EndpointWrapper {

	/**
	 * url to the Patreon OAuth endpoint
	 */
	private static String baseUrl = "https://www.patreon.com/api/oauth2/v2/";

	/**
	 * Sends a Patreon OAuth request
	 *
	 * @param accessToken is the content creator access token
	 * @return the json output from the OAuth request
	 * @throws IOException when the Patreon OAuth request doesn't go as planned
	 */
	public static User fetchUser(String accessToken) throws IOException {
		return WebClient
				.create(baseUrl)
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("identity")
						.queryParam("fields[user]", "about,created,email,first_name,full_name,image_url,last_name,social_connections,thumb_url,url,vanity")
						.build())
				.headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
				.retrieve()
				.bodyToMono(User.class)
				.share()
				.block();
	}
}
