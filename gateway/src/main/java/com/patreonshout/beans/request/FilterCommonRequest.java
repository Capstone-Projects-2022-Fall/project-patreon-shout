package com.patreonshout.beans.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Jackson Serialized object, used for common json properties between search filter endpoint requests
 */
@Getter
public class FilterCommonRequest implements Serializable {

    /**
     * loginToken is the user's login session token
     */
    @NotNull
    @JsonProperty("loginToken")
    private String loginToken;
}
