package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Repository for easy use of CRUD operations on the {@link com.patreonshout.beans.PatreonCampaign} object
 */
@Repository
public interface PatreonCampaignsRepository extends JpaRepository<PatreonCampaign, Long> {
//	PatreonCampaign findByCampaignId(Long id);

	/**
	 * gets a {@link com.patreonshout.beans.PatreonCampaign} by the user's web account id
	 *
	 * @param id is the user's web account id
	 * @return a {@link com.patreonshout.beans.PatreonCampaign} object
	 */
	PatreonCampaign findByWebaccountId(Long id);
}
