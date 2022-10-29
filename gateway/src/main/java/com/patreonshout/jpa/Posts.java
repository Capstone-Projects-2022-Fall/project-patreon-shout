package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Posts Repository Wrapper <br/><br/>
 * <p>
 *     <b>Responsibilities</b>
 *     <ol>
 *         <li>Be a wrapper class for {@link com.patreonshout.jpa.PostsRepository}</li>
 *         <li>Add any logic needed before going into {@link com.patreonshout.jpa.PostsRepository}</li>
 *     </ol>
 * </p>
 */
@Component
public class Posts {

    /**
     * postsRepository is the {@link CreatorTokensRepository} class that handles all logic regarding
     * database connections with the patreon_info table
     */
    @Autowired
    PostsRepository postsRepository;

    /**
     * Gets the posts from a particular creator page
     *
     * @param creator_page_url the creator's url/handle
     * @return a List of {@link com.patreonshout.beans.PostBean} objects containing Patreon post information of a given creator_page_url
     */
    public List<PostBean> getCreatorPosts(String creator_page_url) {
        return postsRepository.getCreatorPosts(creator_page_url);
    }

    /**
     * Gets a specific post based on the post url
     *
     * @param url is the url of the Patreon post
     * @return a {@link com.patreonshout.beans.PostBean} object containing the Patreon post information of the given url
     */
    public PostBean getPost(String url){
        return postsRepository.getPost(url);
    }

    /**
     * Gets every post in the database
     *
     * @return a List of {@link com.patreonshout.beans.PostBean} containing every post in the database
     */
    public List<PostBean> getAllPosts() {
        return postsRepository.getAllPosts();
    }

    /**
     * Adds a new Post to the database
     *
     * @param pb is the post to be added to the database
     */
    public void putPost(PostBean pb) {
        postsRepository.putPost(pb);
    }

    /**
     * Updates an existing post in the database based on the post_id
     *
     * @param pb is the post to be updated in the database
     */
    public void updatePost(PostBean pb) {
        postsRepository.updatePost(pb);
    }

    /**
     * Removes a post in the database based on the post url
     *
     * @param url is the url of the Patreon post
     */
    public void removePost(String url) {
        postsRepository.removePost(url);
    }

    /**
     * Gets the existing posts from a particular content creator
     *
     * @param pbList is the list of {@link com.patreonshout.beans.PostBean} objects that we want to check the database for
     * @return a list of {@link com.patreonshout.beans.PostBean} objects from pbList that were shown to be in the database
     */
    public List<PostBean> getExistingPosts(List<PostBean> pbList) {
        StringBuilder urlList = new StringBuilder("(");

        // fence post problem
        urlList.append("'").append(pbList.get(0).getUrl()).append("'");
        for (int i = 1; i < pbList.size(); i++) {
            urlList.append(", '").append(pbList.get(i).getUrl()).append("'");
        }
        urlList.append(")");

        return postsRepository.getExistingPosts(urlList.toString());
    }
}
