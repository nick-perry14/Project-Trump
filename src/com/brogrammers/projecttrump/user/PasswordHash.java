package com.brogrammers.projecttrump.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * Class to handle password hashing methods
 * 
 * @author Nick Perry
 *
 */
final class PasswordHash {
	// This is used to prevent the Password Hash Object to ever be instantiated
	private PasswordHash() {
	}

	/**
	 * Hashes the given password using MD5 and the given Salt
	 * 
	 * @param password Password to be hashed
	 * @param salt     User Salt
	 * @return Hashed password and salt
	 */
	static String hashPassword(String password, byte[] salt) {
		String generatedPassword = null;
		try {
			// Create MD instance for MD5
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// Adds the salt to the beginning of the MD algorithm (that way passwords will
			// not come out the same)
			md5.update(salt);
			// Appends the password to the MD then Hashes the passwords bytes into DECIMAL
			// BYTES (to be converted)
			byte[] bytes = md5.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			// Convert into hex for standard formats
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toHexString(bytes[i]).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/**
	 * Generates a new salt
	 * 
	 * @return Newly randomly generated salt
	 */
	static byte[] getSalt() {
		// Uses a secure random generator, using the SHA1PRNG algorithm, as this is a
		// common one.
		SecureRandom sr;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			return null;
		}
		// Create array for salt
		byte[] salt = new byte[32];
		// Get a random salt
		sr.nextBytes(salt);
		// return salt
		return salt;
	}

}
