package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 *  Jackson Serialized object, used when a favorites list add request is received
 */
@Getter
public class FavoriteListRequest {

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("login_token")
    private String loginToken;


    /**
     * url is the unique url of the post we want to add to the favorites list
     */
    @NotNull
    @JsonProperty("url")
    private String url;
}
