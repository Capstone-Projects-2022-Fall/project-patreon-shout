package com.patreonshout.jpa;

import com.patreonshout.beans.ListPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.PostBean} object
 */
@Transactional
public interface ListPostsRepository extends JpaRepository<ListPost, Integer> {

    @Modifying
    @Query(value = "DELETE FROM list_posts WHERE list_id = ?1 AND post_id = ?2",
            nativeQuery = true)
    void deleteByListAndPost(int list_id, int post_id);
}
