package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonInfoBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Communication between the patreon_info table in the database
 *
 * <p>
 *     Responsibilities:
 *     1) Add a PatreonInfoBean to the patreon_info database
 *     2) Return whether the bean was successfully added or not
 * </p>
 */
@Repository
public class PatreonInfoRepository {

    /**
     * em is the {@link EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Inserts a {@link PatreonInfoBean} into the patreon_info database
     *
     * @param pib {@link PatreonInfoBean} to be added to database
     * @return either "200" - success or "400" - failure
     */
    @Transactional
    public String putPatreonInfo(PatreonInfoBean pib) {
        String sql = "insert into patreon_info (access_token, expires_in, scope) values (:access_token, :expires_in, :scope)";

        Query q = em.createNativeQuery(sql, PatreonInfoBean.class);
        q.setParameter("access_token", pib.getAccess_token());
        q.setParameter("expires_in", pib.getExpires_in());
        q.setParameter("scope", pib.getScope());

        // 0 means failure in this situation "https://www.ibm.com/docs/en/db2-for-zos/11?topic=sql-jdbc-executeupdate-methods-against-db2-zos-server"
        return q.executeUpdate() != 0 ? "200" : "400";
    }
}
