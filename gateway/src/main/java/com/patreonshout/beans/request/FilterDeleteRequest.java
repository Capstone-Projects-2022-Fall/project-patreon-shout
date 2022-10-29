package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Jackson Serialized object, used for common json properties between search filter endpoint requests
 */
@Getter
public class FilterDeleteRequest extends FilterCommonRequest implements Serializable {

    /**
     * filter_id is the id of the filter to be deleted
     */
    @NotNull
    @JsonProperty("filter_id")
    private int filter_id;
}
