package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import javax.validation.constraints.NotNull;

/**
 *  Jackson Serialized object, used when a list update request is received
 */
@Getter
public class ListUpdateRequest {

    /**
     * listId is the id of the list to be updated
     */
    @NotNull
    @JsonProperty
    private int listId;

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
