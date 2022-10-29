package com.patreonshout.rest.interfaces;

import com.patreonshout.beans.request.FilterAddRequest;
import com.patreonshout.beans.request.FilterDeleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface for endpoints relating to search filters
 */

@RequestMapping(value = "/filters")
@Tag(name = "Search Filter Service",
        description = "Handles all Patreon Shout search filter related tasks for the database.")
public interface SearchFilterImpl {

    /**
     * Endpoint that will delete a {@link com.patreonshout.beans.SearchFilter} object from the database for a user
     *
     * @param filterDeleteRequest is the {@link com.patreonshout.beans.request.FilterDeleteRequest} object that contains json body information about the list we are going to delete from the database
     * @return {@link org.springframework.http.HttpStatus#OK} if successful, {@link org.springframework.http.HttpStatus#BAD_REQUEST} otherwise
     */
    @DeleteMapping
    @Operation(summary = "Deletes one of the user's filters from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List removed.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> DeleteFilter(@RequestBody FilterDeleteRequest filterDeleteRequest);

    /**
     * Endpoint that will get every {@link com.patreonshout.beans.SearchFilter} from the database for a user
     *
     * @param loginToken is the login token provided to the user upon sign in
     * @return a json body of search filter objects for a user
     */
    @GetMapping
    @Operation(summary = "Gets all of the user's filters from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "user lists returned",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> GetUserFilters(@RequestParam String loginToken);

    /**
     * Endpoint that will add a new {@link com.patreonshout.beans.SearchFilter} to the database for a user
     *
     * @param filterAddRequest is the {@link com.patreonshout.beans.request.FilterAddRequest} object that contains json body information about the list we are going to add to the database
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#CONFLICT} otherwise
     */
    @PutMapping
    @Operation(summary = "Adds a user filter to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "List created.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Foreign key constraint failed.",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> AddFilter(@RequestBody FilterAddRequest filterAddRequest);


}
