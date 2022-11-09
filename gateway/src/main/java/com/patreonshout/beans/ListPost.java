package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
 * POJO that relates to the list_posts table in our database
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="list_posts")
public class ListPost {

    /**
     * listPostsId is the index/primary key for the list_posts table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="list_posts_id")
    protected int listPostsId;

    /**
     * listId is the id of the list associated with the list_posts
     */
    @Column(name = "list_id")
    protected int listId;

    /**
     * postId is the id of the post associated with the list_posts
     */
    @Column(name = "post_id")
    protected int postId;
}
