package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO that relates to the posts table in our database
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="lists")
public class ListBean {

    /**
     * list_id is the index/primary key for the lists table in our database
     */
    @Id
    @Column(name="listId")
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
}
