package com.patreonshout.jpa;

import com.patreonshout.beans.ListBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.ListBean} object
 */
public interface ListsRepository extends JpaRepository<ListBean, Long>, ListsRepositoryCustom {
    List<ListBean> findByListId(int listId);
}
