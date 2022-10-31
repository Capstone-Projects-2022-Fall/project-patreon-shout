package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 *  Jackson Serialized object, used when a list creation request is received
 */
@Getter
public class PostGetMultipleRequest implements Serializable {

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("loginToken")
    private String loginToken;

    /**
     * creators is the list of creators we want to get posts from
     */
    @NotNull
    @JsonProperty("creators")
    private List<String> creators;

    /**
     * page is the page number we want of the paginated request
     */
    @NotNull
    @JsonProperty("page")
    private int page;
}
