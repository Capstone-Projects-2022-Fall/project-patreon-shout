package com.patreonshout.rest.interfaces;


import com.patreonshout.PSException;
import com.patreonshout.beans.request.TagAddRequest;
import com.patreonshout.beans.request.TagDeleteRequest;
import com.patreonshout.beans.request.TagGetRequest;
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

    @PostMapping
    ResponseEntity<?> AddTag(@RequestBody TagAddRequest tagAddRequest);

    @GetMapping
    ResponseEntity<?> GetUserTagsOnSinglePost(@RequestBody TagGetRequest tagGetRequest);

    @DeleteMapping
    ResponseEntity<?> DeleteUserTagOnSinglePost(@RequestBody TagDeleteRequest tagDeleteRequest);

}
