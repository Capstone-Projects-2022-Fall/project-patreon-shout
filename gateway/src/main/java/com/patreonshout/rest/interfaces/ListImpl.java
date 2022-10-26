package com.patreonshout.rest.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface for endpoints relating to lists
 */
@RequestMapping(value = "/lists")
@Tag(name = "List Service",
        description = "Handles all Patreon Shout list related tasks for the database.")
public interface ListImpl {

    /**
     * Endpoint that will get a list of {@link com.patreonshout.beans.List} objects from the database given a login token
     *
     * @param loginToken is the login token provided to the user upon sign in
     * @return a json body of list objects for a user
     */
    @GetMapping("/user")
    @Operation(summary = "Gets the Lists of the user via login token")
    @ApiResponses(value = {
            @ApiResponse() // TODO: add api responses
    })
    ResponseEntity<?> GetUserLists(@RequestParam(required = true, name = "loginToken") String loginToken);



}
