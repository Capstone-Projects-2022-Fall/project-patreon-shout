package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  Jackson Serialized object, used when a list update request is received
 */
@Getter
public class ListUpdateRequest extends ListCreationRequest implements Serializable {

    /**
     * list_id is the id of the list to be updated
     */
    @NotNull
    @JsonProperty("list_id")
    private int list_id;
}
