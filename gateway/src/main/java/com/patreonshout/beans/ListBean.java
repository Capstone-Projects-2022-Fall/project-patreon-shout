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
import java.io.Serializable;

/**
 * POJO that relates to the posts table in our database
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="lists")
public class ListBean implements Serializable {

    /**
     * list_id is the index/primary key for the posts table in our database
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
     * added_creators is the JSON Array of creators that are added to the list, ex) ["alex", "ayser", "chris", "jonah"]
     */
    @Column(name="added_creators")
    protected String added_creators;

    /**
     * webAccount is the {@link com.patreonshout.beans.WebAccount} object linked with this object
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "webaccount_id", nullable = false)
    protected WebAccount webAccount;
}
