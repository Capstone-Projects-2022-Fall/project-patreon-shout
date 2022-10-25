package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO that relates to the creator_tokens table in our database
 */
@Entity
@Table(name="creator_tokens")
@Setter
@Getter
@ToString
public class CreatorTokensBean {

    /**
     * The index/primary key for the creator_tokens table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="creator_token_id")
    protected int creator_token_id;

    /**
     * Holds the ID of the {@link WebAccountBean} this object belongs to
     */
    @Column(name = "webaccount_id")
    protected String webaccount_id;

    /**
     * Holds the Patreon content creator's access token
     */
    @Column(name="access_token")
    protected String access_token;

    /**
     * Holds the Patreon content creator's refresh token
     */
    @Column(name="refresh_token")
    protected String refresh_token;

    /**
     * Holds the scope we are provided for the Patreon API per content creator
     */
    @Column(name="scope")
    protected String scope;

    /**
     * Holds the expiration date for when we need to refresh the access_token
     */
    @Column(name="expires_in")
    protected int expires_in;
}
