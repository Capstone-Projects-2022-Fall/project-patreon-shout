package com.patreonshout.jpa;

import com.patreonshout.beans.ListBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.ListBean} object
 */
@Transactional
public interface ListsRepository extends JpaRepository<ListBean, Integer> {

    /**
     * Returns a {@link com.patreonshout.beans.ListBean} object via specific list_id
     *
     * @param list_id the id of the list
     * @return the {@link com.patreonshout.beans.ListBean} holding the list data corresponding to the list_id provided
     */
    ListBean getListByListId(int list_id);

    /**
     * Deletes a {@link com.patreonshout.beans.ListBean} object via specific list_id
     *
     * @param list_id is the id of the list we want to delete
     */
    void deleteListByListId(int list_id);

    /**
     * Gets a list by the user and the list title. WARNING--should only be used when trying to get a user's "Favorites" list
     *
     * @param webAccountId is the id of the user's web account
     * @param title is the title of the list we want to find
     * @return the list with the corresponding account and title
     */
    ListBean findListBeanByWebAccountIdAndTitle(int webAccountId, String title);

    /**
     * Gets a list of {@link com.patreonshout.beans.ListBean} objects by its webaccount_id
     *
     * @param webAccountId the webaccount id we want to get the lists of
     * @return a list of {@link com.patreonshout.beans.ListBean} objects
     */
    List<ListBean> findListBeansByWebAccountId(int webAccountId);

}
