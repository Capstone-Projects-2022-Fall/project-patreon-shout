package com.patreonshout.beans;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * POJO that relates to the lists table in our database
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="lists")
public class ListBean implements Serializable {

    /**
     * list_id is the index/primary key for the list_posts table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="list_id")
    protected int listId;

    /**
     * title is the title of the list
     */
    @Column(name="title")
    protected String title;

    /**
     * description is the description of the list
     */
    @Column(name="description")
    protected String description;

    /**
     * webAccount is the {@link com.patreonshout.beans.WebAccount} object linked with this object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "webaccount_id", nullable = false)
    protected WebAccount webAccount;

    /**
     * listPosts is the List of {@link com.patreonshout.beans.ListPost} objects linked with this ListBean object
     */
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "list")
    List<ListPost> listPosts;
}
