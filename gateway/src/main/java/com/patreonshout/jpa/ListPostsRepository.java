package com.patreonshout.jpa;

import com.patreonshout.beans.ListPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.PostBean} object
 */
@Transactional
public interface ListPostsRepository extends JpaRepository<ListPost, Integer> {

    /**
     * Deletes a {@link com.patreonshout.beans.ListPost} in the database by it's list_id and post_id
     *
     * @param list_id the id of the list
     * @param post_id the id of the post
     */
    @Modifying
    @Query(value = "DELETE FROM list_posts WHERE list_id = ?1 AND post_id = ?2",
            nativeQuery = true)
    void deleteByListAndPost(int list_id, int post_id);

    /**
     * Finds all the posts in a list by it's {@link com.patreonshout.beans.ListBean}
     *
     * @param listId the id of the list we want to find the posts of
     * @return a list of {@link com.patreonshout.beans.ListPost}
     */
    List<ListPost> findAllByListId(int listId);
}
