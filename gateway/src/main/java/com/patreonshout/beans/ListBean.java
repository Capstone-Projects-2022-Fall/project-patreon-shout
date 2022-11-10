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
     * webAccount is the id corresponding our list to the user's {@link com.patreonshout.beans.WebAccount}
     */
    @Column(name="webaccount_id")
    protected int webAccountId;
}
