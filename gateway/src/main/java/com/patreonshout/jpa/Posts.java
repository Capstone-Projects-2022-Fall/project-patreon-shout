package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Posts Repository Wrapper
 *
 * <p>
 *     Responsibilities:
 *     1) Be a wrapper class for {@link com.patreonshout.jpa.PostsRepository}
 *     2) Add any logic needed before going into {@link com.patreonshout.jpa.PostsRepository}
 * </p>
 */
@Component
public class Posts {

    /**
     * postsRepository is the {@link com.patreonshout.jpa.PatreonInfoRepository} class that handles all logic regarding
     * database connections with the patreon_info table
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * getCreatorPosts() gets the posts from a particular creator
     *
     * @param creator is the creator who made the posts we want to get
     * @return a List of {@link com.patreonshout.beans.PostBean} objects containing Patreon post information of a given creator
     */
    public List<PostBean> getCreatorPosts(String creator) {
        return postsRepository.getCreatorPosts(creator);
    }

    /**
     * getPost() gets a specific post based on the post url
     *
     * @param url is the url of the Patreon post
     * @return a {@link com.patreonshout.beans.PostBean} object containing the Patreon post information of the given url
     */
    public PostBean getPost(String url){
        return postsRepository.getPost(url);
    }

    /**
     * getAllPosts() returns every post in the database
     *
     * @return a List of {@link com.patreonshout.beans.PostBean} containing every post in the database
     */
    public List<PostBean> getAllPosts() {
        return postsRepository.getAllPosts();
    }

    /**
     * putPost() adds a new Post to the database
     *
     * @param pb is the post to be added to the database
     */
    public void putPost(PostBean pb) {
        postsRepository.putPost(pb);
    }

    /**
     * updatePost() updates an existing post in the database based on the post_id
     *
     * @param pb is the post to be updated in the database
     */
    public void updatePost(PostBean pb) {
        postsRepository.updatePost(pb);
    }

    /**
     * removePost() removes a post in the database based on the post url
     *
     * @param url is the url of the Patreon post
     */
    public void removePost(String url) {
        postsRepository.removePost(url);
    }
}
