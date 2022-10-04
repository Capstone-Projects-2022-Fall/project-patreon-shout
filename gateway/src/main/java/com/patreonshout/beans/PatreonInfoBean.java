package com.patreonshout.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO that relates to the patreon_info table in our database
 */
@Entity
@Table(name="patreon_info")
public class PatreonInfoBean {

    /**
     * patreon_info_id is the index/primary key for the patreon_info table in our database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patreon_info_id")
    protected int patreon_info_id;

    /**
     * access_token holds the Patreon content creator access token needed to parse a content creator's posts
     */
    @Column(name="access_token")
    protected String access_token;

    /**
     * scope holds the scope we are provided for the Patreon API per content creator
     */
    @Column(name="scope")
    protected String scope;

    /**
     * expires_in holds the expiration date for when we need to refresh the access_token
     */
    @Column(name="expires_in")
    protected int expires_in;

    /**
     * patreon_info_id getter
     * @return patreon_info_id
     */
    public int getPatreon_info_id() {
        return patreon_info_id;
    }

    /**
     * patreon_info_id setter
     * @param patreon_info_id
     */
    public void setPatreon_info_id(int patreon_info_id) {
        this.patreon_info_id = patreon_info_id;
    }

    /**
     * access_token getter
     * @return access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * access_token setter
     * @param access_token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * scope getter
     * @return scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * scope setter
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * expires_in getter
     * @return expires_in
     */
    public int getExpires_in() {
        return expires_in;
    }

    /**
     * expires_in setter
     * @param expires_in
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * toString method to have ease of access to data
     * @return all data fields inside of a formatted string
     */
    @Override
    public String toString() {
        return "PatreonInfoBean{" +
                "patreon_info_id=" + patreon_info_id +
                ", access_token='" + access_token + '\'' +
                ", scope='" + scope + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
