package com.patreonshout.jpa;

import com.patreonshout.beans.ListBean;
import com.patreonshout.beans.WebAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.ListBean} object
 */
public interface ListsRepository extends JpaRepository<ListBean, Integer> {

    /**
     * Returns a {@link com.patreonshout.beans.ListBean} object via specific list_id
     *
     * @param list_id the id of the list
     * @return the {@link com.patreonshout.beans.ListBean} holding the list data corresponding to the list_id provided
     */
    ListBean getListByList_id(int list_id);

    /**
     * Deletes a {@link com.patreonshout.beans.ListBean} object via specific list_id
     *
     * @param list_id is the id of the list we want to delete
     */
    void deleteListByList_id(int list_id);

    /**
     * Gets a list by the user and the list title
     *
     * @param webAccount is the webAccount associated with the list we want to find
     * @param title is the title of the list we want to find
     * @return the list with the corresponding account and title
     */
    ListBean findListBeanByWebAccountAndTitle(WebAccount webAccount, String title);
}
