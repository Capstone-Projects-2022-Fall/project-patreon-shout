package com.patreonshout.rest.interfaces;

import com.patreonshout.PSException;
import com.patreonshout.beans.request.FavoriteListRequest;
import com.patreonshout.beans.request.ListCreationRequest;
import com.patreonshout.beans.request.ListDeleteRequest;
import com.patreonshout.beans.request.ListPostUpdateRequest;
import com.patreonshout.beans.request.ListUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Endpoint that will get a list of {@link com.patreonshout.beans.ListBean} objects from the database given a login token
     *
     * @param loginToken is the login token provided to the user upon sign in
     * @return a json body of list objects for a user
     */
    @GetMapping("/user")
    @Operation(summary = "Gets the Lists of the user via login token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "user lists returned",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> GetUserLists(@RequestParam(required = true, name = "loginToken") String loginToken);

    /**
     * Endpoint that will get a list of {@link com.patreonshout.beans.ListBean} objects from the database that have a specific post in them
     *
     * @param loginToken is the login token provided to the user upon sign in
     * @param url is the url of the post we want to get the lists of
     * @return a json body of list objects for a user
     */
    @GetMapping("/post")
    @Operation(summary = "Gets the Lists of the user that the provided post is apart of")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "user lists returned",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> GetUserListsWithPost(@RequestParam(name = "loginToken") String loginToken, @RequestParam(name = "url") String url);

    /**
     * Endpoint that will add a new {@link com.patreonshout.beans.ListBean} object to the database
     *
     * @param listCreationRequest is the {@link com.patreonshout.beans.request.ListCreationRequest} object that contains {@link com.patreonshout.beans.ListBean} request parameters
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#CONFLICT} otherwise
     */
    @PostMapping("/list")
    @Operation(summary = "Adds a new list to the user's lists")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",
                description = "List created.",
                content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "409",
                description = "Foreign key constraint failed.",
                content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "400",
                description = "the login token provided doesn't match up with the owner of the requested list's login token",
                content = {@Content(mediaType = "application/json")}
        )
    })
    ResponseEntity<?> AddUserList(@RequestBody ListCreationRequest listCreationRequest);

    /**
     * Endpoint that will update a {@link com.patreonshout.beans.ListBean} object in the database
     *
     * @param listUpdateRequest is the {@link com.patreonshout.beans.request.ListUpdateRequest} object that contains {@link com.patreonshout.beans.ListBean} request parameters
     * @return {@link org.springframework.http.HttpStatus#OK} if successful, {@link org.springframework.http.HttpStatus#BAD_REQUEST} otherwise
     */
    @PutMapping("/list")
    @Operation(summary = "Updates one of the user's lists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "List updated.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> UpdateUserList(@RequestBody ListUpdateRequest listUpdateRequest);

    /**
     * Endpoint that will delete a {@link com.patreonshout.beans.ListBean} object in the database
     *
     * @param listDeleteRequest is the {@link com.patreonshout.beans.request.ListDeleteRequest} object that contains {@link com.patreonshout.beans.ListBean} requests parameters
     * @return {@link org.springframework.http.HttpStatus#OK} if successful, {@link org.springframework.http.HttpStatus#BAD_REQUEST} otherwise
     */
    @DeleteMapping("/list")
    @Operation(summary = "Deletes one of the user's lists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List removed.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> DeleteUserList(@RequestBody ListDeleteRequest listDeleteRequest);

    /**
     * Updates the user's post lists
     *
     * @param listPostUpdateRequest is the {@link com.patreonshout.beans.request.ListPostUpdateRequest} object that contains {@link com.patreonshout.beans.ListPost} requests parameters
     * @return {@link org.springframework.http.HttpStatus#OK} if successful, {@link org.springframework.http.HttpStatus#BAD_REQUEST} otherwise
     */
    @PutMapping("/post")
    @Operation(summary = "Updates the lists of the specified post in the json request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Post lists updated.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> UpdateUserPostLists(@RequestBody ListPostUpdateRequest listPostUpdateRequest);

    /**
     * Gets the post from a specific list for a specific user
     *
     * @param loginToken is the login token provided to the user upon sign in
     * @param list_id is the id of the list we want to get the posts of
     * @return a list of {@link com.patreonshout.beans.PostBean} objects from the specified list_id
     */
    @GetMapping("list")
    @Operation(summary = "Gets the posts from a user's list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "user posts returned",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> GetPostsFromList(@RequestParam(name = "loginToken") String loginToken, @RequestParam(name = "list_id") int list_id) throws PSException;

    /**
     * Adds a post to the favorites list
     *
     * @param favoriteListAddRequest is the {@link com.patreonshout.beans.request.FavoriteListRequest} object that contains {@link com.patreonshout.beans.ListPost} requests parameters
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#CONFLICT} OR {@link org.springframework.http.HttpStatus#BAD_REQUEST} otherwise
     */
    @PostMapping("/favorite")
    @Operation(summary = "Adds a post to the user's favorites list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Favorites lists updated.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409",
                    description = "Foreign key constraint failed.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> AddPostToFavoritesList(@RequestBody FavoriteListRequest favoriteListAddRequest) throws PSException;

    /**
     * Deletes a post from the favorites list
     *
     * @param favoriteListDeleteRequest is the {@link com.patreonshout.beans.request.FavoriteListRequest} object that contains {@link com.patreonshout.beans.ListPost} requests parameters
     * @return {@link org.springframework.http.HttpStatus#CREATED} if successful, {@link org.springframework.http.HttpStatus#BAD_REQUEST} otherwise
     */
    @DeleteMapping("/favorite")
    @Operation(summary = "Adds a post to the user's favorites list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Favorites lists updated.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "the login token provided doesn't match up with the owner of the requested list's login token",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    ResponseEntity<?> DeletePostFromFavoritesList(@RequestBody FavoriteListRequest favoriteListDeleteRequest) throws PSException;
}
