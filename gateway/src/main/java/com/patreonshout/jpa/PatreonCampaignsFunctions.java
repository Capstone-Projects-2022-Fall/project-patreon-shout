package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorPage;
import com.patreonshout.beans.PatreonCampaign;
import com.patreonshout.beans.patreon_api.PatreonCampaignV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Component that contains functions for {@link CreatorPage} endpoints that allow interaction with the database
 */
@Component
public class PatreonCampaignsFunctions {

	/**
	 * An autowired Spring repository that handles all database CRUD operations with the creator pages
	 */
	@Autowired
	PatreonCampaignsRepository patreonCampaignsRepository;

	public PatreonCampaign getCampaignByWebaccountId(Long id) {
		return patreonCampaignsRepository.findByWebaccountId(id);
	}

	@Transactional
	public void getCampaign(Long id) throws PSException {
		Optional<PatreonCampaign> patreonCampaign = patreonCampaignsRepository.findById(id); //patreonCampaignsRepository.findByCampaignId(id);

	}

	public void putCampaign(PatreonCampaignV2.Data campaign) {
		PatreonCampaign patreonCampaign = new PatreonCampaign();

		patreonCampaign.setCampaignId(campaign.getId());
		patreonCampaign.setVanity(campaign.getAttributes().getVanity());
	}
}
