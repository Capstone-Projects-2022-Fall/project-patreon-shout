package com.patreonshout.rest;

import com.patreonshout.beans.PostBean;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.rest.interfaces.PostImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Post RESTful Endpoint Interface
 */
@RestController
public class PostSvc extends BaseSvc implements PostImpl {

    /**
     * posts is the wrapper class for {@link com.patreonshout.jpa.PostsRepositoryImpl}
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetCreatorPosts(@RequestParam(name = "creator") String creator) {
        List<PostBean> response = postsRepository.getCreatorPosts(creator);

        for (PostBean pb : response) {
            if (!pb.is_public()) {
                pb.setContent("This post is private");
            }
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
}
