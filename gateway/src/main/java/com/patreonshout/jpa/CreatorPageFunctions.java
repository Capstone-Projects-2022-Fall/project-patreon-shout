package com.patreonshout.jpa;

import com.patreonshout.PSException;
import com.patreonshout.beans.CreatorPage;
import com.patreonshout.beans.constants.CreatorPageCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

	@Transactional
	public void putCreatorPage(String pageUrl) throws PSException {
		if (pageUrl == null)
			throw new PSException(HttpStatus.BAD_REQUEST, "Page/Vanity URL was null.");

		CreatorPage creatorPage = creatorPageRepository.findByPageUrl(pageUrl);

		if (creatorPage == null) {
			creatorPage = new CreatorPage();
			creatorPage.setPageUrl(pageUrl);
		}

		creatorPage.setPageUrl(pageUrl);
		creatorPage.setPageName(pageUrl); // TODO: Find a way to get the creator's Patreon Page name
		creatorPage.setPageCategory(CreatorPageCategory.OTHER.name()); // TODO: Try to utilize enum instead of String...  Changing this to enum breaks logging in.

		creatorPageRepository.save(creatorPage);
	}
}
