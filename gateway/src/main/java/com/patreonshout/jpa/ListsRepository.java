package com.patreonshout.jpa;

import com.patreonshout.beans.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.List} object
 */
public interface ListsRepository extends JpaRepository<List, Long>, ListsRepositoryCustom {

}
