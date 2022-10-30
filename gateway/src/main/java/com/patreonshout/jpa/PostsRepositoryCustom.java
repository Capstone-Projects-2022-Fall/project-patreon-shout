package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;

import java.util.List;

/**
 * Custom Spring Data Repository for custom SQL functionality not automatically provided in {@link org.springframework.data.jpa.repository.JpaRepository}
 */
public interface PostsRepositoryCustom {

    List<PostBean> getCreatorPosts(String creator_page_url);

    PostBean getPost(String url);

    List<PostBean> getAllPosts();

    void putPost(PostBean pb);

    void updatePost(PostBean pb);

    void removePost(String url);

    List<PostBean> getExistingPosts(List<PostBean> pbList);
}
