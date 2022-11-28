package com.patreonshout.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

/**
 * Used to send posts to reddit and refresh the user's access token
 */
public class RedditApiUtil {

    /**
     * Refreshes a user's access token
     *
     * @param refreshToken is the token used to generate a new access token for the user
     * @param clientId is our reddit client's id
     * @param clientSecret is our reddit client's secret
     * @return the new access token
     */
    public String refreshAccessToken(String refreshToken, String clientId, String clientSecret) throws ParseException {

        String basicAuth = clientId + ":" + clientSecret;
        String finalBasicAuth = Base64.getEncoder().encodeToString(basicAuth.getBytes());

        String response = WebClient.create("https://www.reddit.com/api/v1/access_token")
                .method(HttpMethod.POST)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
                    httpHeaders.setBasicAuth(finalBasicAuth);
                })
                .body(BodyInserters.fromFormData("grant_type", "refresh_token").with("refresh_token", refreshToken))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONParser parser = new JSONParser();
        JSONObject objResponse = (JSONObject) parser.parse(response);

        return (String) objResponse.get("access_token");
    }

    /**
     * Sends a reddit post
     *
     * @param accessToken the authorization token to send a post on a user's behalf
     * @param body the body text of the post
     * @param title the title of the post
     * @param subreddit the subreddit to output the post to
     */
    public void sendPost(String accessToken, String body, String title, String subreddit) throws ParseException {

        if (subreddit.startsWith("r/")) {
            if (subreddit.length() == 2) {
                System.out.println("invalid subreddit value was '/r'");
                return;
            }
            subreddit = subreddit.substring(2);
        }

        if (subreddit.length() == 0) {
            System.out.println("invalid subreddit value was ''");
            return;
        }

        // get the access_token and refresh_token of the user
        String response = WebClient.create("https://oauth.reddit.com/api/submit")
                .method(HttpMethod.POST)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
                    httpHeaders.setBearerAuth(accessToken);
                })
                .body(BodyInserters.fromFormData("kind", "self").with("title", title).with("text", body).with("sr", "r/" + subreddit))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
