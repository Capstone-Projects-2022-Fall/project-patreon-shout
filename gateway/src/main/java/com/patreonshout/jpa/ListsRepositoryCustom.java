package com.patreonshout.jpa;

import com.patreonshout.beans.ListBean;

/**
 * Custom Spring Data Repository for custom SQL functionality not automatically provided in {@link org.springframework.data.jpa.repository.JpaRepository}
 */
public interface ListsRepositoryCustom {
    /**
     * Returns a {@link com.patreonshout.beans.ListBean} object via specifiec list_id
     *
     * @param list_id the id of the list
     * @return the {@link com.patreonshout.beans.ListBean} holding the list data corresponding to the list_id provided
     */
    ListBean getListByList_id(int list_id);

    void deleteListByList_id(int list_id);
}
