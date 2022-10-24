package com.patreonshout.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO that relates to the posts table in our database
 */
@JsonIgnoreProperties({ "id", "links", "post_id" })
@Entity
@Table(name="posts")
@Type("post")
public class PostBean extends BaseResource {

    /**
     * post_id is the index/primary key for the posts table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    protected int post_id;

    /**
     * creator is the name of the content creator who made the Patreon post
     */
    @Column(name="creator_page_url")
    protected String creator_page_url;

    /**
     * publishdate is the date at which a Patreonpost was published
     */
    @Column(name="publishdate")
    @JsonProperty("published_at")
    protected String publishdate;

    /**
     * title is the title of the Patreon post
     */
    @Column(name="title")
    @JsonProperty("title")
    protected String title;

    /**
     * url is the url of the Patreon post
     */
    @Column(name="url")
    @JsonProperty("url")
    protected String url;

    /**
     * content is the content of the Patreon post
     */
    @Column(name="content")
    @JsonProperty("content")
    protected String content;

    /**
     * isprivate denotes whether the Patreon post is private or public
     */
    @Column(name="is_public")
    @JsonProperty("is_public")
    protected boolean is_public;

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
     * is_public getter
     * @return is_public
     */
    public boolean isIs_public() {
        return is_public;
    }

    /**
     * is_public setter
     * @param is_public
     */
    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }

    /**
     * creator getter
     * @return creator
     */
    public String getCreator() {
        return creator_page_url;
    }

    /**
     * creator setter
     * @param creator_page_url
     */
    public void setCreator(String creator_page_url) {
        this.creator_page_url = creator_page_url;
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
                ", is_public=" + is_public +
                ", creator_page_url='" + creator_page_url + '\'' +
                '}';
    }
}
