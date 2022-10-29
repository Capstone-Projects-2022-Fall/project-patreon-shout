package com.patreonshout.jpa;

import com.patreonshout.beans.SearchFilter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.SearchFilter} object
 */
public interface SearchFiltersRepository extends JpaRepository<SearchFilter, Long>, SearchFiltersCustom {
}
