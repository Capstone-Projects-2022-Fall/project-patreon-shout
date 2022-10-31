package com.patreonshout.config;

import com.patreonshout.jpa.WebAccountFunctions;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.passay.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Spring Security Configuration
 */
@Configuration
public class SecurityConfiguration {

	SecureRandom secureRandom = new SecureRandom();

	/**
	 * Set of requirements a password must meet to be considered valid
	 */
	PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
			// Must be at least 8 and at most 255 characters
			new LengthRule(8, 255),

			// Require at least one uppercase character
			new CharacterRule(EnglishCharacterData.UpperCase, 1),

			// Require at least one lowercase character
			new CharacterRule(EnglishCharacterData.LowerCase, 1),

			// Require at least one digit
			new CharacterRule(EnglishCharacterData.Digit, 1),

			// Require at least one symbol (special character)
			new CharacterRule(EnglishCharacterData.Special, 1),

			// No spaces allowed
			new WhitespaceRule()));

	/**
	 * Pepper value for WebAccount passwords -- used as a constant value included in every password
	 */
	public @Value("${patreonshout.db.webaccount.pepper}") String pepper;

	/**
	 * SHA1 encodes text
	 *
	 * @param text text to encode
	 * @return SHA1 encoded text
	 */
	public String SHA1Encoder(String text) {
		return DigestUtils.shaHex(text);
	}

	/**
	 * Encodes given passwords with {@link BCryptPasswordEncoder}
	 *
	 * The password is encrypted in the following form:
	 * (pepper + rawPassword + salt) where the "pepper" constant is assigned in the Spring
	 * application properties
	 *
	 * @return {@link PasswordEncoderFactories} object that defaults to BCrypt encoding
	 */
	public String encodePassword(String rawPassword, String salt) {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
				.encode(pepper + rawPassword + salt);
	}

	/**
	 * Checks if a given password and salt combine to the given encoded password.
	 * This function utilizes the "pepper" constant that is assigned in the application properties.
	 *
	 * @param rawPassword     {@link String} raw password retrieved from user input
	 * @param encodedPassword {@link String} Encoded password retrieved from a {@link WebAccountFunctions} in the database
	 * @return {@link Boolean} true if password matches, false otherwise
	 */
	public boolean passwordMatches(String rawPassword, String salt, String encodedPassword) {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
				.matches(pepper + rawPassword + salt, encodedPassword);
	}

	/**
	 * Generates a pseudo-random salt of 124 bytes utilizing {@link SecureRandom}
	 *
	 * @return 124 bytes
	 */
	public String createSalt() {
		byte[] salt = new byte[124];
		secureRandom.nextBytes(salt);

		return Hex.encodeHexString(salt);
	}

	/**
	 * Checks if the given raw password fits our password requirements.<br/>
	 * <br/>
	 * <b>Requirements</b>
	 * <ul>
	 *     <li>At least one uppercase character</li>
	 *     <li>At least one lowercase character</li>
	 *     <li>At least one digit</li>
	 *     <li>At least one symbol (special character)</li>
	 *     <li>No spaces</li>
	 * </ul>
	 *
	 * @param rawPassword password to check
	 * @return {@link Boolean#TRUE} if password meets our password requirements, {@link Boolean#FALSE} otherwise
	 */
	public boolean passwordIsValid(String rawPassword) {
		return passwordValidator.validate(new PasswordData(rawPassword)).isValid();
	}
}