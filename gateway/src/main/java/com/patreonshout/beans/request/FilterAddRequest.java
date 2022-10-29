package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  Jackson Serialized object, used when a filter add request is received
 */
@Getter
public class FilterAddRequest extends FilterCommonRequest implements Serializable {

    /**
     * filter is the saved filter the user wants to filter posts by
     */
    @NotNull
    @JsonProperty(value="filter")
    private String filter;

    /**
     * filter_name is the name set to the filter provided by the user
     */
    @NotNull
    @JsonProperty(value="filter_name")
    private String filter_name;
}
