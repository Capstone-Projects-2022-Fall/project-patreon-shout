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

    List<Tag> findAllByPostBean(PostBean postBean);

    Tag findTagByPostBeanAndWebAccountAndTag(PostBean postBean, WebAccount webAccount, String tag);

}
