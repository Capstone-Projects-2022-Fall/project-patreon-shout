package com.patreonshout.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patreon_info")
public class PatreonInfoBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="patreon_info_id")
    protected int patreon_info_id;

    @Column(name="access_token")
    protected String access_token;

    @Column(name="scope")
    protected String scope;

    @Column(name="expires_in")
    protected int expires_in;


    public int getPatreon_info_id() {
        return patreon_info_id;
    }

    public void setPatreon_info_id(int patreon_info_id) {
        this.patreon_info_id = patreon_info_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

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
