package com.patreonshout.jpa.constants;

import com.patreonshout.beans.WebAccount;

/**
 * Enum containing the types of social platforms that can be added to a {@link WebAccount}
 * {@link #DISCORD}
 * {@link #TWITTER}
 * {@link #INSTAGRAM}
 */
public enum IntegrationType {
	/**
	 * Discord platform
	 */
	DISCORD,

	/**
	 * Twitter platform
	 */
	TWITTER,

	/**
	 * Instagram platform
	 */
	INSTAGRAM
}
