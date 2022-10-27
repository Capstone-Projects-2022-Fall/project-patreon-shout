package com.patreonshout.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true)
	protected int id;

	@Column(name = "creator_page_url", unique = true)
	protected String pageUrl;

	@Column(name = "creator_page_name")
	protected String pageName;

	@Column(name = "creator_page_category")
	protected String pageCategory;

//	@OneToOne
//	@MapsId
//	@JoinColumn(name = "webaccount_id")
//	protected WebAccount webAccount;
}
