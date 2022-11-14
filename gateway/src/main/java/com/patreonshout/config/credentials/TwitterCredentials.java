package com.patreonshout.config.credentials;

import lombok.Getter;

/**
 * This class holds twitter credential information for the Twitter app we use to create posts on a user's behalf
 */
@Getter
public class TwitterCredentials {

    /**
     * clientId is the id of our Twitter app
     */
    private final String clientID;

    /**
     * clientSecret is the secret of our Twitter app
     */
    private final String clientSecret;

    /**
     * redirectUri is the redirect uri a user is sent to after doing an OAuth
     */
    private final String redirectUri;

    /**
     * Sets the Twitter credentials
     *
     * @param clientID is the id of our Twitter app
     * @param clientSecret is the secret of our Twitter app
     * @param redirectUri is the redirect uri a user is sent to after doing an OAuth
     */
    public TwitterCredentials(String clientID, String clientSecret, String redirectUri) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
