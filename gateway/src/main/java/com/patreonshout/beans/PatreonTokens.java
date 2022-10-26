package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * POJO that relates to the creator_tokens table in our database
 */
@Entity
@Table(name= "patreon_tokens")
@Setter
@Getter
@ToString
public class PatreonTokens {

    /**
     * Holds the ID of the {@link WebAccount} this object belongs to
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webaccount_id")
    protected int webAccountId;

    /**
     * Holds the Patreon content creator's access token
     */
    @Column(name="access_token")
    protected String accessToken;

    /**
     * Holds the Patreon content creator's refresh token
     */
    @Column(name="refresh_token")
    protected String refreshToken;

    /**
     * Holds the scope we are provided for the Patreon API per content creator
     */
    @Column(name="scope")
    protected String scope;

    /**
     * Holds the expiration date for when we need to refresh the access_token
     */
    @Column(name="expires_in")
    protected int expiresIn;

    /**
     * webAccount is the {@link com.patreonshout.beans.WebAccount} object linked with this object
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "webaccount_id")
    protected WebAccount webAccount;
}
