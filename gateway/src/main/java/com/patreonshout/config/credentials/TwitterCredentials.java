package com.patreonshout.config.credentials;

import lombok.Getter;

@Getter
public class TwitterCredentials {
    private final String clientID;
    private final String clientSecret;
    private final String redirectUri;

    public TwitterCredentials(String clientID, String clientSecret, String redirectUri) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
