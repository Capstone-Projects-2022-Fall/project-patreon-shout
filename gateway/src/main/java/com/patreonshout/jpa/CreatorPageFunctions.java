package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorPage;
import com.patreonshout.beans.constants.CreatorPageCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Component that contains functions for {@link CreatorPage} endpoints that allow interaction with the database
 */
@Component
public class CreatorPageFunctions {

	/**
	 * An autowired Spring repository that handles all database CRUD operations with the creator pages
	 */
	@Autowired
	CreatorPageRepository creatorPageRepository;

	/**
	 * puts a {@link com.patreonshout.beans.CreatorPage} object into the database based on campaignId and vanity name
	 *
	 * @param campaignId is the user's Patreon campaign id
	 * @param vanity is the user's Patreon url name
	 */
	@Transactional
	public void putCreatorPage(int campaignId, String vanity) {
		CreatorPage creatorPage = new CreatorPage();

		creatorPage.setCampaignId(campaignId);
		creatorPage.setPageName(vanity); // TODO: Find a way to get the creator's Patreon Page name
		creatorPage.setPageCategory(CreatorPageCategory.OTHER.name()); // TODO: Try to utilize enum instead of String...  Changing this to enum breaks logging in.

		creatorPageRepository.save(creatorPage);
	}
}
