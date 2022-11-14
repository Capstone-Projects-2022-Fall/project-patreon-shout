package com.patreonshout.config.credentials;

import lombok.Getter;

/**
 * This class holds Patreon credential information for the Patreon client we use to create posts on a user's behalf
 */
@Getter
public class PatreonCredentials {

    /**
     * clientId is the id of our Patreon client
     */
    private final String clientID;

    /**
     * clientSecret is the secret of our Patreon client
     */
    private final String clientSecret;

    /**
     * redirectUri is the redirect uri a user is sent to after doing an OAuth
     */
    private final String redirectUri;

    /**
     * Sets the Twitter credentials
     *
     * @param clientID is the id of our Patreon client
     * @param clientSecret is the secret of our Patreon client
     * @param redirectUri is the redirect uri a user is sent to after doing an OAuth
     */
    public PatreonCredentials(String clientID, String clientSecret, String redirectUri) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
