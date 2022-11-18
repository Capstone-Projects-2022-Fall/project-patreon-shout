package com.patreonshout.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorPage;
import com.patreonshout.beans.PatreonCampaign;
import com.patreonshout.beans.WebAccount;
import com.patreonshout.beans.patreon_api.PatreonCampaignV2;
import com.patreonshout.beans.patreon_api.PatreonDataArrayEntryV2;
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

	/**
	 * An autowired Spring component that endpoints utilize to send or receive data from the database
	 */
	@Autowired
	WebAccountFunctions webAccountFunctions;

	/**
	 * Jackson object mapper that allows converting Java type {@link Object} to custom POJOs.
	 */
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * gets a campaign id by the user's web account id
	 *
	 * @param id is the user's web account id
	 * @return a {@link com.patreonshout.beans.PatreonCampaign} object
	 */
	public PatreonCampaign getCampaignByWebaccountId(Long id) {
		return patreonCampaignsRepository.findByWebaccountId(id);
	}

	@Transactional
	public void getCampaign(Long id) throws PSException {
		Optional<PatreonCampaign> patreonCampaign = patreonCampaignsRepository.findById(id); //patreonCampaignsRepository.findByCampaignId(id);

	}

//	public void putCampaign(PatreonCampaignV2.Data campaign) {
//		PatreonCampaign patreonCampaign = new PatreonCampaign();
//
//		patreonCampaign.setCampaignId(campaign.getId());
//		patreonCampaign.setVanity(campaign.getAttributes().getVanity());
//	}

	/**
	 * wrapper for putting a {@link com.patreonshout.beans.PatreonCampaign} object into the database
	 *
	 * @param loginToken the user's login token
	 * @param campaign the campaign we want to add to the database
	 * @throws PSException in case there is a problem with the database or a user mismatch
	 */
	public void putCampaign(String loginToken, PatreonDataArrayEntryV2 campaign) throws PSException {
		WebAccount webAccount = webAccountFunctions.getAccount(loginToken);
		this.putCampaign(webAccount, campaign);
	}

	/**
	 * puts a {@link com.patreonshout.beans.PatreonCampaign} object into the database
	 *
	 * @param webAccount is the user's {@link com.patreonshout.beans.WebAccount} object
	 * @param campaignDataEntry is the information we want to put into the database for a campaign
	 * @throws PSException in case there is a problem with the database or a user mismatch
	 */
	public void putCampaign(WebAccount webAccount, PatreonDataArrayEntryV2 campaignDataEntry) throws PSException {
		PatreonCampaign patreonCampaign = new PatreonCampaign();

		patreonCampaign.setWebaccountId(webAccount.getWebAccountId());
		patreonCampaign.setCampaignId(campaignDataEntry.getId());

		PatreonCampaignV2 convertedCampaign = objectMapper.convertValue(campaignDataEntry.getAttributes(), PatreonCampaignV2.class);
		patreonCampaign.setVanity(convertedCampaign.getVanity());

		patreonCampaignsRepository.save(patreonCampaign);
	}
}
