package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.PostBean} object
 */
public interface PostsRepository extends JpaRepository<PostBean, Long>, PostsRepositoryCustom{
}
