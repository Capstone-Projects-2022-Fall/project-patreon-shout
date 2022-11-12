package com.patreonshout.utils;

import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;

public class TwitterApiUtil {

    private final TwitterApi client;

    public TwitterApiUtil(String clientId, String clientSecret, String userAccessToken, String userRefreshToken) {
        this.client = new TwitterApi(new TwitterCredentialsOAuth2(clientId, clientSecret, userAccessToken, userRefreshToken, true));
    }



}
