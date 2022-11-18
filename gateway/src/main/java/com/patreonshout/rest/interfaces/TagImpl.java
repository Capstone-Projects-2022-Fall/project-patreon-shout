package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.request.TagAddRequest;
import com.patreonshout.beans.request.TagDeleteRequest;
import com.patreonshout.beans.request.TagGetRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface for endpoints relating to lists
 */
@RequestMapping(value = "/tags")
@Tag(name = "Tag Service",
        description = "Handles all Patreon Shout post tag related tasks for the database.")
public interface TagImpl {

    /**
     * Endpoint that will add a new {@link com.patreonshout.beans.Tag} object to the database
     *
     * @param tagAddRequest is the {@link com.patreonshout.beans.request.TagAddRequest} object that contains {@link com.patreonshout.beans.Tag} request parameters
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#CONFLICT} or {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR} or {@link org.springframework.http.HttpStatus#BAD_GATEWAY} otherwise
     */
    @PostMapping
    @Operation(summary = "Adds a new tag to the user's tag for a specific post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Tag created.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Duplicate primary key.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid login token.",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> AddTag(@RequestBody TagAddRequest tagAddRequest) throws PSException;

    /**
     * Endpoint that will get the user given tags of a post from the database
     *
     * @param tagGetRequest is the {@link com.patreonshout.beans.request.TagGetRequest} object that contains {@link com.patreonshout.beans.Tag} request parameters
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR} or {@link org.springframework.http.HttpStatus#BAD_GATEWAY} otherwise
     */
    @GetMapping
    @Operation(summary = "Gets the tags that a user has for a specific post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "user lists returned",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> GetUserTagsOnSinglePost(@RequestBody TagGetRequest tagGetRequest) throws PSException;

    /**
     * Endpoint that will delete the user given tag of a post from the database
     *
     * @param tagDeleteRequest is the {@link com.patreonshout.beans.request.TagDeleteRequest} object that contains {@link com.patreonshout.beans.Tag} request parameters
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#INTERNAL_SERVER_ERROR} or {@link org.springframework.http.HttpStatus#BAD_GATEWAY} otherwise
     */
    @DeleteMapping
    @Operation(summary = "Deletes a tag that a user has for a specific post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tag Removed",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> DeleteUserTagOnSinglePost(@RequestBody TagDeleteRequest tagDeleteRequest) throws PSException;

}
