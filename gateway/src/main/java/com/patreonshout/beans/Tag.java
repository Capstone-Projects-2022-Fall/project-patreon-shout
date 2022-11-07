package com.patreonshout.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO that relates to the tags table in our database
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tags")
public class Tag {

    /**
     * tagId is a {@link Integer} primary key for the tags table in the database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", unique = true)
    protected int tagId;

    /**
     * tag is the post tag the user associates with the post
     */
    @Column(name = "tag")
    protected String tag;

    /**
     * webAccount is the {@link com.patreonshout.beans.WebAccount} object linked with this object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "webaccount_id", nullable = false)
    protected WebAccount webAccount;

    /**
     * postBean is the {@link com.patreonshout.beans.PostBean} object linked with this object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    protected PostBean postBean;
}
