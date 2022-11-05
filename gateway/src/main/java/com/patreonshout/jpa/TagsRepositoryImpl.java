package com.patreonshout.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of custom Spring Data Repository for custom SQL functionality not automatically provided in {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
public class TagsRepositoryImpl {

    /**
     * em is the {@link javax.persistence.EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;
}
