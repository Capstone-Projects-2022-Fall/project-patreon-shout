package com.patreonshout.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * POJO that relates to the old_passwords table in our database
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "old_passwords")
public class OldPasswords {

	/**
	 * Holds the ID of the {@link WebAccount} this object belongs to
	 */
	@Id
	@Column(name="webaccount_id")
	protected int webAccountId;

	/**
	 * The most recent previous password
	 */
	@Column(name = "old_password_1")
	protected String oldPasswordOne;

	/**
	 * The second most recent previous password
	 */
	@Column(name = "old_password_2")
	protected String oldPasswordTwo;

	/**
	 * The third most recent previous password
	 */
	@Column(name = "old_password_3")
	protected String oldPasswordThree;

	/**
	 * The {@link com.patreonshout.beans.WebAccount} object linked with this object
	 */
	@OneToOne
	@MapsId
	@JoinColumn(name = "webaccount_id")
	protected WebAccount webAccount;

	/**
	 * Returns whether this entity contains any valid old passwords
	 *
	 * @return {@link Boolean#TRUE} if any old password is not null, {@link Boolean#FALSE} otherwise
	 */
	public boolean isEmpty() {
		return oldPasswordOne == null && oldPasswordTwo == null && oldPasswordThree == null;
	}
}
