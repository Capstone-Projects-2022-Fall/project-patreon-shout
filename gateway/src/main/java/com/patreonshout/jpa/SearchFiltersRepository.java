package com.patreonshout.jpa;

import com.patreonshout.beans.SearchFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.SearchFilter} object
 */
@Transactional
public interface SearchFiltersRepository extends JpaRepository<SearchFilter, Long>, SearchFiltersCustom {

    /**
     * Deletes a {@link com.patreonshout.beans.SearchFilter} in the database by it's filter_id
     *
     * @param filter_id the id of the list
     */
    @Modifying
    @Query(value = "DELETE FROM search_filters WHERE filter_id = ?1",
            nativeQuery = true)
    void deleteSearchFilterByFilterId(int filter_id);

    /**
     * Finds all of the search filters associated with a user
     *
     * @param webaccountId the id of the user's webaccount
     * @return a list of {@link com.patreonshout.beans.SearchFilter} objects
     */
    List<SearchFilter> findSearchFiltersByWebaccountId(int webaccountId);
}
