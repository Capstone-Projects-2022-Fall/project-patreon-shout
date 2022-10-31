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
public interface PostsRepository extends JpaRepository<PostBean, Long>, PostsRepositoryCustom{

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
}
