package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  Jackson Serialized object, used when a list creation request is received
 */
@Getter
public class ListCreationRequest implements Serializable{

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("loginToken")
    private String loginToken;

    /**
     * title is the new list title
     */
    @NotNull
    @JsonProperty("title")
    private String title;

    /**
     * description is the new list description
     */
    @NotNull
    @JsonProperty("description")
    private String description;

    /**
     * added_creators is the new list creators
     */
    @NotNull
    @JsonProperty("added_creators")
    private String added_creators;

}
