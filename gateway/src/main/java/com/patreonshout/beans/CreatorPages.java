package com.patreonshout.beans;

import com.patreonshout.beans.constants.CreatorPageCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "creator_pages")
public class CreatorPages {

	/**
	 * The primary & foreign key for this table, pointing to a {@link WebAccount#webAccountId}
	 */
	@Id
	@Column(name="webaccount_id")
	protected int webAccountId;

	protected String creatorPageUrl;

	protected String creatorName;

	protected CreatorPageCategory creatorPageCategory;

	@OneToOne
	@MapsId
	@JoinColumn(name = "webaccount_id")
	protected WebAccount webAccount;
}
