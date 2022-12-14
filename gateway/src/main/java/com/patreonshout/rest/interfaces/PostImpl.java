package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.request.PostGetMultipleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface for endpoints relating to Patreon posts
 */
@RequestMapping(value = "/posts")
@Tag(name = "Post Service",
        description = "Handles all Patreon post related tasks for the database.")
public interface PostImpl {

    /**
     * Endpoint that will get a list of {@link com.patreonshout.beans.PostBean} objects from the database given a specified creator
     *
     * @param campaignId is the creator of the posts we want to get
     * @return a json body of post objects from a given creator
     */
    @GetMapping("/creator")
    @Operation(summary = "Gets the saved Patreon posts of a specified creator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "Creator posts returned",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> GetCreatorPosts(@RequestParam(required = true, name = "campaign") int campaignId);

    /**
     * Endpoint that will get a list of {@link com.patreonshout.beans.PostBean} objects from the database given list of creators
     *
     * @param postGetMultipleRequest is the {@link com.patreonshout.beans.request.PostGetMultipleRequest} object holding json data about our request
     * @return a json body of post objects from the given list of creators
     */
    @GetMapping("/creators")
    @Operation(summary = "Gets the saved Patreon posts of a specified creator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "Creator posts returned",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid login token.",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> GetMultipleCreatorPosts(@RequestBody PostGetMultipleRequest postGetMultipleRequest) throws PSException;

    /**
     * Endpoint that will return the campaign info given a campaign_id
     *
     * @param campaignId the campaign id that we want to get the info of
     * @return a json body of posts
     */
    @GetMapping("/campaign")
    @Operation(summary = "Gets the saved Patreon posts of a specified creator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "Creator campaign returned.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No campaign found.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid login token.",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> GetCampaignInfoFromId(@RequestParam(required = true, name = "campaignId") int campaignId, @RequestParam(required = true, name = "loginToken") String loginToken) throws PSException;

    /**
     * Endpoint that will return the post information of each creator that the user follows
     *
     * @param loginToken the user's login token
     * @return a json body of post information
     */
    @GetMapping("/following")
    @Operation(summary = "Gets the saved Patreon posts of creators that the user follows")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "Creator posts returned",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid login token.",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> GetFollowingPosts(@RequestParam(name = "loginToken") String loginToken) throws PSException;
}
