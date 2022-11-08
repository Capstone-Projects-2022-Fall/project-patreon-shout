package com.patreonshout.beans;

import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * POJO that relates to the posts table in our database
 */
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
    protected int postId;

    @Column(name="app_id")
    protected String appId;

    @Column(name="app_status")
    protected String appStatus;

    @Column(name="embed_data")
    protected String embedData;

    @Column(name="embed_url")
    protected String embedUrl;

    @Column(name="is_paid")
    protected String isPaid;

    /**
     * creator is the name of the content creator who made the Patreon post
     */
    @Column(name="creator_page_url")
    protected String creatorPageUrl;

    /**
     * publishdate is the date at which a Patreonpost was published
     */
    @Column(name="publishdate")
    protected String publishDate;

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
    @Column(name="is_public")
    protected boolean isPublic;

    /**
     * tags is the list of {@link com.patreonshout.beans.Tag} objects linked with this PostBean object
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "postBean")
    List<Tag> tags;

    /**
     * listPosts is the List of {@link com.patreonshout.beans.ListPost} objects linked with this ListBean object
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    List<ListPost> listPosts;

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
