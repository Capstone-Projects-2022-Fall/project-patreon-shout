package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Jackson Serialized object, used when a list post update request is received
 */
@Getter
public class ListPostUpdateRequest {

    /**
     * url of the post to be updated from the specified lists
     */
    @NotNull
    @JsonProperty("url")
    private String url;

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("login_token")
    private String loginToken;

    /**
     * listUpdates are the lists that will update(add/remove) the post specified through it's url
     */
    @NotNull
    @JsonProperty("list_updates")
    List<ListUpdate> listUpdates;

    /**
     * Jackson Serialized object, used when a list post update request is received, holds the update information for each list
     */
    @Getter
    public static class ListUpdate {

        /**
         * list_id is the id of the list to be updated
         */
        @NotNull
        @JsonProperty("list_id")
        private int listId;

        @NotNull
        @JsonProperty("update")
        private boolean update;
    }
}


