package com.patreonshout.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * POJO that relates to the following_creators table in our database
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="following_creators")
public class FollowingCreators {

    /**
     * webAccountId holds the ID of the {@link WebAccount} this object belongs to
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webaccount_id")
    protected int webAccountId;

    /**
     * campaignIds holds a list of campaignIds of creators that the user follows
     */
    @Column(name="campaign_ids")
    protected String campaignIds;

    /**
     * The {@link com.patreonshout.beans.WebAccount} object linked with this object
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "webaccount_id")
    protected WebAccount webAccount;
}
