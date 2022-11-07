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
     * Returns a list of posts by the creator
     *
     * @param creatorPageUrl is the creator's unique name
     * @return a list of {@link com.patreonshout.beans.PostBean} objects
     */
    List<PostBean> findAllByCreatorPageUrl(String creatorPageUrl);

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
}
