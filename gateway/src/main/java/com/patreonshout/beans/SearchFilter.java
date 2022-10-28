package com.patreonshout.beans;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO that relates to the search_filter table in our database
 */
@Data
@Entity
@Table(name = "search_filter")
public class SearchFilter {

    @Id
    private Long criteria_id;

}
