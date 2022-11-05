package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 *  Jackson Serialized object, used when a filter add request is received
 */
@Getter
public class TagDeleteRequest {

    /**
     * filter is the saved filter the user wants to filter posts by
     */
    @NotNull
    @JsonProperty(value="tag")
    private String tag;

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("login_token")
    private String loginToken;

    /**
     * url is the url of the post we want to delete the tag from
     */
    @NotNull
    @JsonProperty("url")
    private String url;
}
