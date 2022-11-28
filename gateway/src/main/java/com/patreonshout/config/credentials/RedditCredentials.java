package com.patreonshout.config.credentials;

import lombok.Getter;

/**
 * This class holds reddit credential information for the Reddit app we use to create posts on a user's behalf
 */
@Getter
public class RedditCredentials {

    /**
     * clientId is the id of our Reddit app
     */
    private final String clientID;

    /**
     * clientSecret is the secret of our Reddit app
     */
    private final String clientSecret;

    /**
     * redirectUri is the redirect uri a user is sent to after doing an OAuth
     */
    private final String redirectUri;

    /**
     * Sets the Reddit credentials
     *
     * @param clientID is the id of our Reddit app
     * @param clientSecret is the secret of our Reddit app
     * @param redirectUri is the redirect uri a user is sent to after doing an OAuth
     */
    public RedditCredentials(String clientID, String clientSecret, String redirectUri) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
