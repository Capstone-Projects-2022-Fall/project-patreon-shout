package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.PostBean} object
 */
public interface PostsRepository extends JpaRepository<PostBean, Integer> {

    /**
     * Returns the posts from multiple creators in paginated json body
     *
     * @param campaignList is the list of creators we want to get posts from
     * @param pageable is the {@link org.springframework.data.domain.Pageable} object we use to paginate our data
     * @return is the {@link org.springframework.data.domain.Page} object with our json body data holding posts from specified creators
     */
    @Query(value = "SELECT * FROM posts WHERE campaign_id in ?1",
    countQuery = "SELECT count(*) FROM posts WHERE campaign_id in ?1",
    nativeQuery = true)
    Page<PostBean> getMultipleCreatorPosts(List<String> campaignList, Pageable pageable);

    /**
     * Returns the posts from multiple creators
     *
     * @param campaignList is the list of creators we want to get posts from
     * @return is the list of {@link com.patreonshout.beans.PostBean} objects holding posts from specified creators
     */
    @Query(value = "SELECT * FROM posts WHERE campaign_id in ?1 ORDER BY published_at DESC",
            countQuery = "SELECT count(*) FROM posts WHERE campaign_id in ?1",
            nativeQuery = true)
    List<PostBean> getMultipleCreatorPosts(List<String> campaignList);

    /**
     * Returns a list of posts by the creator
     *
     * @param campaignId is the creator's unique name
     * @return a list of {@link com.patreonshout.beans.PostBean} objects
     */
    List<PostBean> findAllByCampaignId(int campaignId);

    /**
     * returns a post by the post's link
     *
     * @param url is the url of the post we want to find
     * @return a {@link com.patreonshout.beans.PostBean} object
     */
    PostBean findPostBeanByUrl(String url);

    /**
     * getExistingPosts() checks what posts in the {@link com.patreonshout.beans.PostBean} object list are already in the database
     *
     * @param pbList is the list of {@link com.patreonshout.beans.PostBean} objects that we want to check the database for
     * @return a list of {@link com.patreonshout.beans.PostBean} objects that were shown to be in the database
     */
    List<PostBean> getExistingPosts(List<PostBean> pbList);

    /**
     * find the posts in a list by list_id
     *
     * @param list_id is the id of a list
     * @return a list of {@link com.patreonshout.beans.PostBean}
     */
    @Query(value = "SELECT * FROM list_posts RIGHT JOIN posts ON list_posts.post_id = posts.post_id where list_id = ?1",
            nativeQuery = true)
    List<Object[]> findPostsInListByListId(int list_id, String url);
}
