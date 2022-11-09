package com.patreonshout.jpa;

import com.patreonshout.beans.CreatorPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorPageRepository extends JpaRepository<CreatorPage, Long> {

	/**
	 * find a creator page by their page url
	 *
	 * @param pageUrl the page url of the creator
	 * @return the {@link com.patreonshout.beans.CreatorPage} linked to the page url
	 */
	CreatorPage findByPageUrl(String pageUrl);
}
