package com.patreonshout.beans;

import lombok.Data;

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
 * POJO that relates to the search_filter table in our database
 */
@Data
@Entity
@Table(name = "search_filters")
public class SearchFilter {

    /**
     * filter _id is the index/primary key for the search_filters table in our database
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="filter_id")
    private Long filterId;

    /**
     * filter is the search term that is set as the filter
     */
    @Column(name="filter")
    private String filter;

    /**
     * filter_name is the name of the filter shown on the filter list
     */
    @Column(name="filter_name")
    private String filterName;

    /**
     * webaccountId is the id of the user's web account
     */
    @Column(name="webaccount_id")
    private int webaccountId;
}
