package com.patreonshout.jpa.constants;

import com.patreonshout.beans.WebAccountBean;

/**
 * Enum containing the types of social platforms that can be added to a {@link WebAccountBean}
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
