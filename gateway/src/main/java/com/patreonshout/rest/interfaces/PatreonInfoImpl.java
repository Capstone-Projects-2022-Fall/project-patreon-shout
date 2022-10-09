package com.patreonshout.rest.interfaces;

import com.patreonshout.beans.PatreonInfoBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface for endpoints relating to the patreon_info table on the database
 */

@RequestMapping(value = "/patreoninfo")
@Tag(name = "Patreon Info service",
        description = "Handles all Patreon Info related tasks for the database.")
public interface PatreonInfoImpl {
    /**
     * Swagger endpoint documentation annotations and expected argument for endpoint
     *
     * @param request is the json request body
     * @return response code for http request issuer
     */
    @PutMapping("/put")
    @Operation(summary = "Saves Content Creator Patreon Information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Patreon Information was saved",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Patreon Information was not saved",
                    content = {@Content(mediaType = "application/json")})
    })
    String PutPatreonInfo(
            @RequestBody(
                    description = "Patreon Info To Add",
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    implementation = PatreonInfoBean.class)))
            String request);
}
