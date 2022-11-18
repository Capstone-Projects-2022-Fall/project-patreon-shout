package com.patreonshout.jpa;

import com.patreonshout.beans.CreatorPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.CreatorPage} object
 */
@Repository
public interface CreatorPageRepository extends JpaRepository<CreatorPage, Long> {

}
