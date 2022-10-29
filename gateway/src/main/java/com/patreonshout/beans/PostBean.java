package com.patreonshout.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Setter
@Getter
@ToString
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
     * Used to check if a {@link com.patreonshout.beans.PostBean} is equal to another PostBean object
     *
     * @param o is the object we check the current object against
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof PostBean)) {
            return false;
        }

        return this.url.equals(((PostBean) o).getUrl());
    }
}
