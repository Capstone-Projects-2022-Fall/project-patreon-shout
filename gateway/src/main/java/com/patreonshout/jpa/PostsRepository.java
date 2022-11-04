package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.PostBean} object
 */
public interface PostsRepository extends JpaRepository<PostBean, Long> {

    /**
     * Returns the posts from multiple creators in paginated json body
     *
     * @param creatorList is the list of creators we want to get posts from
     * @param pageable is the {@link org.springframework.data.domain.Pageable} object we use to paginate our data
     * @return is the {@link org.springframework.data.domain.Page} object with our json body data holding posts from specified creators
     */
    @Query(value = "SELECT * FROM posts WHERE creator_page_url in ?1",
    countQuery = "SELECT count(*) FROM posts WHERE creator_page_url in ?1",
    nativeQuery = true)
    Page<PostBean> getMultipleCreatorPosts(List<String> creatorList, Pageable pageable);

    /**
     * getCreatorPosts() gets the posts from a particular creator
     *
     * @param creator_page_url is the creator who made the posts we want to get
     * @return a List of {@link com.patreonshout.beans.PostBean} objects containing Patreon post information of a given creator
     */
    List<PostBean> getCreatorPosts(String creator_page_url);

    /**
     * getPost() gets a specific post based on the post url
     *
     * @param url is the url of the Patreon post
     * @return a {@link com.patreonshout.beans.PostBean} object containing the Patreon post information of the given url
     */
    PostBean getPost(String url);

    /**
     * getAllPosts() returns every post in the database
     *
     * @return a List of {@link com.patreonshout.beans.PostBean} containing every post in the database
     */
    List<PostBean> getAllPosts();

    /**
     * putPost() adds a new Post to the database
     *
     * @param pb is the post to be added to the database
     */
    void putPost(PostBean pb);

    /**
     * updatePost() updates an existing post in the database based on the post_id
     *
     * @param pb is the post to be updated in the database
     */
    void updatePost(PostBean pb);

    /**
     * removePost() removes a post in the database based on the post url
     *
     * @param url is the url of the Patreon post
     */
    void removePost(String url);

    /**
     * getExistingPosts() checks what posts in the {@link com.patreonshout.beans.PostBean} object list are already in the database
     *
     * @param pbList is the list of {@link com.patreonshout.beans.PostBean} objects that we want to check the database for
     * @return a list of {@link com.patreonshout.beans.PostBean} objects that were shown to be in the database
     */
    List<PostBean> getExistingPosts(List<PostBean> pbList);
}
