package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO that shows a patron's membership details for a campaign
 */


@ToString
@Getter
@Setter
public class MembershipBean {

    /**
     * the user is not a pledging patron but has subscribed to updates about public posts
     */
    private boolean is_follower;

    /**
     * one of "active_patron", "declined_patron", "former_patron", or "null" if the member has never pledged
     */
    private String patron_status;

    /**
     * holds Patreon campaign id
     */
    private int campaignid;

}
