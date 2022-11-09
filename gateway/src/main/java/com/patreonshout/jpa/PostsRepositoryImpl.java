package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;
import com.patreonshout.rest.BaseSvc;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of custom Spring Data Repository for custom SQL functionality not automatically provided in {@link org.springframework.data.jpa.repository.JpaRepository}
 */
@Repository
public class PostsRepositoryImpl extends BaseSvc {

    /**
     * em is the {@link EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;


    /**
     * getExistingPosts() checks what posts in the {@link com.patreonshout.beans.PostBean} object list are already in the database
     *
     * @param pbList is the list of {@link com.patreonshout.beans.PostBean} objects that we want to check the database for
     * @return a list of {@link com.patreonshout.beans.PostBean} objects that were shown to be in the database
     */
    @Transactional
    public List<PostBean> getExistingPosts(List<PostBean> pbList) {
        String urlList = getUrlList(pbList);

        String sql = "SELECT * FROM posts WHERE url IN " + urlList;

        Query q = em.createNativeQuery(sql, PostBean.class);

        return q.getResultList();
    }

    /**
     * Gets the existing posts from a particular content creator
     *
     * @param pbList is the list of {@link com.patreonshout.beans.PostBean} objects that we want to check the database for
     * @return a list of urls from the pbList provided
     */
    private String getUrlList(List<PostBean> pbList) {

        if (pbList.isEmpty()) {
            return "()";
        }

        StringBuilder urlList = new StringBuilder("(");

        // fence post problem
        urlList.append("'").append(pbList.get(0).getUrl()).append("'");
        for (int i = 1; i < pbList.size(); i++) {
            urlList.append(", '").append(pbList.get(i).getUrl()).append("'");
        }
        urlList.append(")");

        return urlList.toString();
    }
}
