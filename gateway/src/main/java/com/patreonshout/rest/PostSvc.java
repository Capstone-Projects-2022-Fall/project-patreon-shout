package com.patreonshout.rest;

import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.PostGetMultipleRequest;
import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.WebAccountFunctions;
import com.patreonshout.rest.interfaces.PostImpl;
import com.patreonshout.utils.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Post RESTful Endpoint Interface
 */
@RestController
public class PostSvc extends BaseSvc implements PostImpl {

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the posts table in the database
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    private WebAccountFunctions webAccountFunctions;

    /**
     * {@inheritDoc}
     */
    @CrossOrigin(origins = origin)
    public ResponseEntity<?> GetCreatorPosts(@RequestParam(name = "creator") String creator) { // TODO: SOON TO BE DEPRECATED
        List<PostBean> response = postsRepository.getCreatorPosts(creator);

        for (PostBean pb : response) {
            if (!pb.is_public()) {
                pb.setContent("This post is private");
            }
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @CrossOrigin(origins = origin)
    public ResponseEntity<?> GetMultipleCreatorPosts(@RequestBody PostGetMultipleRequest postGetMultipleRequest) {
        WebAccount userAccount = webAccountFunctions.findByLoginToken(postGetMultipleRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        Page<PostBean> page = postsRepository.getMultipleCreatorPosts(postGetMultipleRequest.getCreators(), PageRequest.of(postGetMultipleRequest.getPage(), 5).withSort(Sort.Direction.ASC, "publishdate"));

        for (PostBean pb : page.getContent()) {
            if (!pb.is_public()) {
                pb.setContent("This post is private");
            }
        }

        return new ResponseEntity<>(page, HttpStatus.FOUND);
    }
}
