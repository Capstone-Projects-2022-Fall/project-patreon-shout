package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;
import com.patreonshout.beans.Tag;
import com.patreonshout.beans.WebAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.Tag} object
 */
public interface TagsRepository extends JpaRepository<Tag, Long> {

    /**
     * Gets a list of the {@link com.patreonshout.beans.Tag} objects that a user has put onto one post
     *
     * @param postBean is the post the tags are assigned to
     * @return a list of the {@link com.patreonshout.beans.Tag} objects that a user has put onto one post
     */
    List<Tag> findAllByPostBean(PostBean postBean);

    /**
     * Find a particular {@link com.patreonshout.beans.Tag}
     *
     * @param postBean is the post the tags are assigned to
     * @param webAccount is the account the tags are assigned to
     * @param tag is the particular tag we want to find the {@link com.patreonshout.beans.Tag} object of
     * @return the {@link com.patreonshout.beans.Tag} with the corresponding tag, post, and account provided
     */
    Tag findTagByPostBeanAndWebAccountAndTag(PostBean postBean, WebAccount webAccount, String tag);

}
