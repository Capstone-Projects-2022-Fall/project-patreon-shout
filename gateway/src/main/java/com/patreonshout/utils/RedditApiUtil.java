package com.patreonshout.utils;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class RedditApiUtil {

    public String refreshAccessToken() {
        // TODO: send request to get new access token
        return null;
    }

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
