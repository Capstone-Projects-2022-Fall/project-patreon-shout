package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  Jackson Serialized object, used when a filter add request is received
 */
@Getter
public class TagGetRequest implements Serializable {

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("login_token")
    private String loginToken;

    /**
     * url is the url of the post we want to get the tags of
     */
    @NotNull
    @JsonProperty("url")
    private String url;
}
