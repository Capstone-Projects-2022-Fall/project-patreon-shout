package com.patreonshout.rest;

import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.Tag;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.request.TagAddRequest;
import com.patreonshout.beans.request.TagDeleteRequest;
import com.patreonshout.beans.request.TagGetRequest;

import com.patreonshout.jpa.PostsRepository;
import com.patreonshout.jpa.TagsRepository;
import com.patreonshout.jpa.WebAccountFunctions;

import com.patreonshout.rest.interfaces.TagImpl;

import com.patreonshout.utils.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Tags RESTful Endpoint Interface
 */
@RestController
public class TagSvc extends BaseSvc implements TagImpl {

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the tags table in the database
     */
    @Autowired
    TagsRepository tagsRepository;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the posts table in the database
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * An autowired Spring component that endpoints utilize to send or receive data from the database
     */
    @Autowired
    WebAccountFunctions webAccountFunctions;


    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> AddTag(@RequestBody TagAddRequest tagAddRequest) {
        WebAccount userAccount = webAccountFunctions.findByLoginToken(tagAddRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        PostBean postBean = postsRepository.findPostBeanByUrl(tagAddRequest.getUrl());

        if (postBean == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid post url.");
        }

        Tag tag = new Tag();
        tag.setTag(tagAddRequest.getTag());
        tag.setPostBean(postBean);
        tag.setWebAccount(userAccount);

        tagsRepository.save(tag);

        return ResponseUtil.Generic(HttpStatus.CREATED, "Tag created.");
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> GetUserTagsOnSinglePost(@RequestBody TagGetRequest tagGetRequest) {

        WebAccount userAccount = webAccountFunctions.findByLoginToken(tagGetRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        PostBean postBean = postsRepository.findPostBeanByUrl(tagGetRequest.getUrl());

        if (postBean == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid post url.");
        }

        // build response so ResponseEntity can parse the returned objects correctly
        List<String> response = new ArrayList<>();

        for (Tag tag : tagsRepository.findAllByPostBean(postBean)) {
            response.add(tag.getTag());
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<?> DeleteUserTagOnSinglePost(@RequestBody TagDeleteRequest tagDeleteRequest) {

        WebAccount userAccount = webAccountFunctions.findByLoginToken(tagDeleteRequest.getLoginToken());

        if (userAccount == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid login token.");
        }

        PostBean postBean = postsRepository.findPostBeanByUrl(tagDeleteRequest.getUrl());

        if (postBean == null) {
            return ResponseUtil.Generic(HttpStatus.BAD_REQUEST, "Invalid post url.");
        }

        Tag tag = tagsRepository.findTagByPostBeanAndWebAccountAndTag(postBean, userAccount, tagDeleteRequest.getTag());

        tagsRepository.delete(tag);

        return ResponseUtil.Generic(HttpStatus.OK, "Tag Removed.");
    }
}
