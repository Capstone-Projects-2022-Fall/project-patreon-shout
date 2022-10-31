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
public class PostsRepositoryImpl extends BaseSvc implements PostsRepositoryCustom {

    /**
     * em is the {@link EntityManager} that handles all the transactions with our database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * getCreatorPosts() gets the posts from a particular creator
     *
     * @param creator_page_url is the creator who made the posts we want to get
     * @return a List of {@link com.patreonshout.beans.PostBean} objects containing Patreon post information of a given creator
     */
    @Transactional
    public List<PostBean> getCreatorPosts(String creator_page_url) {
        String sql = "select * from posts where creator_page_url = :creator_page_url";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("creator_page_url", creator_page_url);

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
        String sql = "insert into posts (publishdate, title, url, content, is_public, creator_page_url) values (:publishdate, :title, :url, :content, :is_public, :creator_page_url)";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("publishdate", pb.getPublishdate());
        q.setParameter("title", pb.getTitle());
        q.setParameter("url", pb.getUrl());
        q.setParameter("content", pb.getContent());
        q.setParameter("is_public", pb.is_public());
        q.setParameter("creator_page_url", pb.getCreator_page_url());

        q.executeUpdate();
    }

    /**
     * updatePost() updates an existing post in the database based on the post_id
     *
     * @param pb is the post to be updated in the database
     */
    @Transactional
    public void updatePost(PostBean pb) {
        String sql = "update posts set publishdate = :publishdate, title = :title, url = :url, content = :content, is_public = :is_public, creator_page_url = :creator_page_url where post_id = :post_id";

        Query q = em.createNativeQuery(sql, PostBean.class);
        q.setParameter("publishdate", pb.getPublishdate());
        q.setParameter("title", pb.getTitle());
        q.setParameter("url", pb.getUrl());
        q.setParameter("content", pb.getContent());
        q.setParameter("is_public", pb.is_public());
        q.setParameter("creator_page_url", pb.getCreator_page_url());
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
