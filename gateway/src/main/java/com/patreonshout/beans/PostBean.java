package com.patreonshout.beans;

import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * POJO that relates to the posts table in our database
 */
@Entity
@Table(name="posts")
@Type("post")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class PostBean extends BaseResource {

    /**
     * post_id is the index/primary key for the posts table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    protected int postId;

    /**
     * Name of the content creator who made the Patreon post
     */
    @Column(name="campaign_id")
    protected int campaignId;

    /**
     * Platform app id. Can be null
     */
    @Column(name="app_id")
    protected String appId;

    /**
     * Processing status of the post. Can be null
     */
    @Column(name="app_status")
    protected String appStatus;

    /**
     * The content of the Patreon post. Can be null
     */
    @Column(name="content")
    protected String content;

    /**
     * An object containing embed data if media is embedded in the post, or none if there is no embed
     */
    @Column(name="embed_data")
    protected String embedData;

    /**
     * Embed media url. Can be null
     */
    @Column(name="embed_url")
    protected String embedUrl;

    /**
     * True if the post incurs a bill as part of a pay-per-post campaign. TODO: Can be null
     */
    @Column(name="is_paid")
    protected Boolean isPaid;

    /**
     * True if the post is viewable by anyone. TODO: Can be null
     */
    @Column(name="is_public")
    protected Boolean isPublic;

    /**
     * Datetime that the creator most recently published (made publicly visible) the post. Can be null
     */
    @Column(name="publishdate") // TODO: This should be published_at
    protected String publishDate; // TODO: This should be publishedAt and in UTC ISO format

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
     * tags is the list of {@link com.patreonshout.beans.Tag} objects linked with this PostBean object
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "postBean")
    List<Tag> tags;

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
