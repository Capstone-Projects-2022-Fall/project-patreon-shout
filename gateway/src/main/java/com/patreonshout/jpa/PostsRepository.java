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
public class PostsRepository  extends BaseSvc {

    /**
     * em is the {@link EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * getCreatorPosts() gets the posts from a particular creator
     *
     * @param creator is the creator who made the posts we want to get
     * @return a List of {@link com.patreonshout.beans.PostBean} objects containing Patreon post information of a given creator
     */
    @Transactional
    public List<PostBean> getCreatorPosts(String creator) {
        String sql = "select * from posts where creator = :creator";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("creator", creator);

        return q.getResultList();
    }

    /**
     * getPost() gets a specific post based on the post url
     *
     * @param url is the url of the Patreon post
     * @return a {@link com.patreonshout.beans.PostBean} object containing the Patreon post information of the given url
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
     * getAllPosts() returns every post in the database
     *
     * @return a List of {@link com.patreonshout.beans.PostBean} containing every post in the database
     */
    @Transactional
    public List<PostBean> getAllPosts() {
        String sql = "select * from posts";

        Query q = em.createNativeQuery(sql, PostBean.class);

        return q.getResultList();
    }

    /**
     * putPost() adds a new Post to the database
     *
     * @param pb is the post to be added to the database
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
     * updatePost() updates an existing post in the database based on the post_id
     *
     * @param pb is the post to be updated in the database
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
     * removePost() removes a post in the database based on the post url
     *
     * @param url is the url of the Patreon post
     */
    @Transactional
    public void removePost(String url) {
        String sql = "delete from posts where url = :url";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("url", url);

        q.executeUpdate();
    }
}
