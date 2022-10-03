package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PatreonInfo Repository Wrapper
 *
 * <p>
 *     Responsibilities:
 *     1) Be a wrapper class for {@link com.patreonshout.jpa.PatreonInfoRepository}
 *     2) Add any logic needed before going into {@link com.patreonshout.jpa.PatreonInfoRepository}
 * </p>
 */
@Component
public class PatreonInfo {

    /**
     * patreonInfoRepository is the {@link com.patreonshout.jpa.PatreonInfoRepository} class that handles all logic regarding
     * database connections with the patreon_info table
     */
    @Autowired
    PatreonInfoRepository patreonInfoRepository;

    /**
     * Calls {@link com.patreonshout.jpa.PatreonInfoRepository}'s putPatreonInfo() and passes it the PatreonInfoBean that
     * is to be added to the database
     *
     * @param pib is the {@link com.patreonshout.beans.PatreonInfoBean} being added to the database
     * @return either "200" - success or "400" - failure
     */
    public String putPatreonInfo(PatreonInfoBean pib) {
        return patreonInfoRepository.putPatreonInfo(pib);
    }
}
