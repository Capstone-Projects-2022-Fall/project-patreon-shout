package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonInfoBean;
import com.patreonshout.beans.PostBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PatreonInfoRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String putPatreonInfo(PatreonInfoBean pib) {
        String sql = "insert into patreon_info (access_token, expires_in, scope) values (:access_token, :expires_in, :scope)";

        Query q = em.createNativeQuery(sql, PatreonInfoBean.class);
        q.setParameter("access_token", pib.getAccess_token());
        q.setParameter("expires_in", pib.getExpires_in());
        q.setParameter("scope", pib.getScope());

        // 0 means failure in this situation "https://www.ibm.com/docs/en/db2-for-zos/11?topic=sql-jdbc-executeupdate-methods-against-db2-zos-server"
        return q.executeUpdate() != 0 ? "success" : "failure";
    }

}
