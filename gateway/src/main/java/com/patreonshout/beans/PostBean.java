package com.patreonshout.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO that relates to the posts table in our database
 */
@Entity
@Table(name="posts")
public class PostBean {

    /**
     * post_id is the index/primary key for the posts table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    protected int post_id;

    /**
     * publishdate is the date at which a Patreonpost was published
     */
    @Column(name="publishdate")
    protected String publishdate;

    /**
     * title is the title of the Patreon post
     */
    @Column(name="title")
    protected String title;

    /**
     * url is the url of the Patreon post
     */
    @Column(name="url")
    protected String url;

    /**
     * content is the content of the Patreon post
     */
    @Column(name="content")
    protected String content;

    /**
     * isprivate denotes whether the Patreon post is private or public
     */
    @Column(name="isprivate")
    protected boolean isprivate;

    /**
     * creator is the name of the content creator who made the Patreon post
     */
    @Column(name="creator")
    protected String creator;

    /**
     * post_id getter
     * @return post_id
     */
    public int getPost_id() {
        return post_id;
    }

    /**
     * post_id setter
     * @param post_id
     */
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    /**
     * publishdate getter
     * @return publishdate
     */
    public String getPublishdate() {
        return publishdate;
    }

    /**
     * publishdate setter
     * @param publishdate
     */
    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * title getter
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * title setter
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * url getter
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * url setter
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * content getter
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * content setter
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * isprivate getter
     * @return isprivate
     */
    public boolean isIsprivate() {
        return isprivate;
    }

    /**
     * isprivate setter
     * @param isprivate
     */
    public void setIsprivate(boolean isprivate) {
        this.isprivate = isprivate;
    }

    /**
     * creator getter
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * creator setter
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * toString method to have ease of access to data
     * @return all data fields inside of a formatted string
     */
    @Override
    public String toString() {
        return "PostBean{" +
                "post_id=" + post_id +
                ", publishdate='" + publishdate + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", isprivate=" + isprivate +
                ", creator='" + creator + '\'' +
                '}';
    }
}
