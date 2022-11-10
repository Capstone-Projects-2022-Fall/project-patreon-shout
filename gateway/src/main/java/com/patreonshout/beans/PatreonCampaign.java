package com.patreonshout.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * POJO that relates to the patreon_campaigns table in our database
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "patreon_campaigns")
public class PatreonCampaign {

	@Column(name = "webaccount_id")
	protected Integer webaccountId;

	@Id
	@Column(name = "campaign_id", unique = true)
	protected Integer campaignId;

	@Column(name = "vanity")
	protected String vanity;

}
