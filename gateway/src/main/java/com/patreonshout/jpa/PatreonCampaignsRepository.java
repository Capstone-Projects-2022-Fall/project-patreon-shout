package com.patreonshout.jpa;

import com.patreonshout.beans.PatreonCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatreonCampaignsRepository extends JpaRepository<PatreonCampaign, Long> {
//	PatreonCampaign findByCampaignId(Long id);
	PatreonCampaign findByWebaccountId(Long id);
}
