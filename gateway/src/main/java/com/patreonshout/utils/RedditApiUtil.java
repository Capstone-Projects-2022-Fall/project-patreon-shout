package com.patreonshout.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class RedditApiUtil {

    public void sendPost(String accessToken, String body, String title) throws ParseException {

        // get the access_token and refresh_token of the user
        String response = WebClient.create("https://oauth.reddit.com/api/submit")
                .method(HttpMethod.POST)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.valueOf("application/x-www-form-urlencoded"));
                    httpHeaders.setBearerAuth(accessToken);
                })
                .body(BodyInserters.fromFormData("kind", "self").with("title", title).with("text", body).with("sr", "r/testingpatreonshout")) // TODO: get the user's saved subreddit location
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONParser parser = new JSONParser();
        JSONObject objResponse = (JSONObject) parser.parse(response);

        System.out.println(response);

    }
}
