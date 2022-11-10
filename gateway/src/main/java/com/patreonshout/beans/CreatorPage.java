package com.patreonshout.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * POJO that relates to the creator_pages table in our database
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "creator_pages")
public class CreatorPage {

	/**
	 * <b>id</b> is a {@link Integer} primary key for the webaccounts table in the database
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="campaign_id", unique = true)
	protected int campaignId;

	/**
	 * Campaign's page name
	 */
	@Column(name = "creator_page_name")
	protected String pageName;

	/**
	 * Campaign's category
	 */
	@Column(name = "creator_page_category")
	protected String pageCategory;

//	@OneToOne
//	@MapsId
//	@JoinColumn(name = "webaccount_id")
//	protected WebAccount webAccount;
}
