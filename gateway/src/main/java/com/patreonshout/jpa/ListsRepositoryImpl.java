package com.patreonshout.jpa;

import com.patreonshout.beans.ListBean;
import com.patreonshout.rest.BaseSvc;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementation of custom Spring Data Repository for custom SQL functionality not automatically provided in {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
@Transactional
public class ListsRepositoryImpl extends BaseSvc implements ListsRepositoryCustom{

    /**
     * em is the {@link javax.persistence.EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public ListBean getListByList_id(int list_id) {
        String sql = "select * from lists where list_id = :list_id";

        Query q = em.createNativeQuery(sql, ListBean.class);
        q.setParameter("list_id", list_id);

        return (ListBean) q.getResultList().get(0);
    }

}