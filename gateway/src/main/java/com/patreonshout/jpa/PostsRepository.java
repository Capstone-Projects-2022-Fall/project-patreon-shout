package com.patreonshout.jpa;

import com.patreonshout.beans.PostBean;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Communication between the patreon_info table in the database
 *
 * <p>
 *     Responsibilities:
 *     1) Get a specific PostBean from the database
 *     2) Get all PostBeans in the database
 *     3) Add a PostBean to the database
 *     4) Update a PostBean in the database
 *     5) Remove a PostBean in the database
 *     6) Return whether the bean was successfully added or not
 * </p>
 */
@Repository
public class PostsRepository {

    /**
     * em is the {@link EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param url
     * @return
     */
    @Transactional
    public PostBean getPost(String url){
        String sql = "select * from posts where url = :url";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("url", url);

        List<PostBean> pbList = q.getResultList();

        if (pbList.isEmpty()) {
            return new PostBean();
        }

        return pbList.get(0);
    }

    /**
     *
     * @return
     */
    @Transactional
    public List<PostBean> getAllPosts() {
        String sql = "select * from posts";

        Query q = em.createNativeQuery(sql, PostBean.class);

        return q.getResultList();
    }

    /**
     *
     * @param pb
     */
    @Transactional
    public void putPost(PostBean pb) {
        String sql = "insert into posts (publishdate, title, url, content, isprivate, creator) values (:publishdate, :title, :url, :content, :isprivate, :creator)";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("publishdate", pb.getPublishdate());
        q.setParameter("title", pb.getTitle());
        q.setParameter("url", pb.getUrl());
        q.setParameter("content", pb.getContent());
        q.setParameter("isprivate", pb.isIsprivate());
        q.setParameter("creator", pb.getCreator());

        q.executeUpdate();
    }

    /**
     *
     * @param pb
     */
    @Transactional
    public void updatePost(PostBean pb) {
        String sql = "update posts set publishdate = :publishdate, title = :title, url = :url, content = :content, isprivate = :isprivate, creator = :creator where post_id = :post_id";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("publishdate", pb.getPublishdate());
        q.setParameter("title", pb.getTitle());
        q.setParameter("url", pb.getUrl());
        q.setParameter("content", pb.getContent());
        q.setParameter("isprivate", pb.isIsprivate());
        q.setParameter("creator", pb.getCreator());
        q.setParameter("post_id", pb.getPost_id());

        q.executeUpdate();
    }

    /**
     *
     * @param url
     */
    @Transactional
    public void removePost(String url) {
        String sql = "delete from posts where url = :url";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("url", url);

        q.executeUpdate();
    }
}
