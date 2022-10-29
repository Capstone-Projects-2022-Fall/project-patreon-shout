package com.patreonshout.jpa;

import com.patreonshout.rest.BaseSvc;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of custom Spring Data Repository for custom SQL functionality not automatically provided in {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
@Transactional
public class SearchFiltersRepositoryImpl extends BaseSvc implements SearchFiltersCustom {

}
