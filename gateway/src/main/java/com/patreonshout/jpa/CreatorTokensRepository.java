package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonTokens;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Communication between the creator_tokens table in the database
 *
 * <p>
 *     <b>Responsibilities</b>
 *     <ol>
 *         <li>Add a {@link PatreonTokens} to the creator_tokens database</li>
 *         <li>Return whether the bean was successfully added or not</li>
 *     </ol>
 * </p>
 */
@Repository
public class CreatorTokensRepository {

    /**
     * em is the {@link EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Inserts a {@link PatreonTokens} into the patreon_info database
     *
     * @param pib {@link PatreonTokens} to be added to database
     * @return either "200" - success or "400" - failure
     */
    @Transactional
    public String putPatreonInfo(PatreonTokens pib) {
        String sql = "insert into creator_tokens (webaccount_id, access_token, expires_in, scope) values (:webaccount_id, :access_token, :expires_in, :scope)";

        Query q = em.createNativeQuery(sql, PatreonTokens.class);
        q.setParameter("webaccount_id", pib.getWebAccountId());
        q.setParameter("access_token", pib.getAccessToken());
        q.setParameter("expires_in", pib.getExpiresIn());
        q.setParameter("scope", pib.getScope());

        // 0 means failure in this situation "https://www.ibm.com/docs/en/db2-for-zos/11?topic=sql-jdbc-executeupdate-methods-against-db2-zos-server"
        return q.executeUpdate() != 0 ? "200" : "400";
    }
}
