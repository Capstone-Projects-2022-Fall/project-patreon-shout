package com.patreonshout.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * POJO that relates to the patreon_campaigns table in our database(soon going to be no longer used/deprecated)
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "patreon_campaigns")
public class PatreonCampaign {

	/**
	 * webaccountId holds the user's web account id
	 */
	@Column(name = "webaccount_id")
	protected Integer webaccountId;

	/**
	 * campaignId holds the OAuth'd user's Patreon campaign id
	 */
	@Id
	@Column(name = "campaign_id", unique = true)
	protected Integer campaignId;

	/**
	 * vanity holds the user's Patreon url name
	 */
	@Column(name = "vanity")
	protected String vanity;

}
