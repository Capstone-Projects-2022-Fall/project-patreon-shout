package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PatreonInfo Repository Wrapper
 *
 * <p>
 *     Responsibilities:
 *     1) Be a wrapper class for {@link CreatorTokensRepository}
 *     2) Add any logic needed before going into {@link CreatorTokensRepository}
 * </p>
 */
@Component
public class CreatorTokens {

    /**
     * patreonInfoRepository is the {@link CreatorTokensRepository} class that handles all logic regarding
     * database connections with the patreon_info table
     */
    @Autowired
    CreatorTokensRepository creatorTokensRepository;

    /**
     * Calls {@link CreatorTokensRepository}'s putPatreonInfo() and passes it the PatreonInfoBean that
     * is to be added to the database
     *
     * @param pib is the {@link PatreonTokens} being added to the database
     * @return either "200" - success or "400" - failure
     */
    public String putPatreonInfo(PatreonTokens pib) {
        return creatorTokensRepository.putPatreonInfo(pib);
    }
}
