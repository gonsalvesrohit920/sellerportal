package com.springau.sellerportal.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class PasswordHash.
 *
 * @author Rohit Gonsalves
 */
public class PasswordHash {
	
	

	/**
	 * Private Constructor to disable instantiation.
	 */
	private PasswordHash() {

	}

	/**
	 * Gets the MD5 hash.
	 *
	 * @param data the string
	 * @return the MD5 hash
	 */
	public static String getMd5Hash(String data) {
		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
		if (data != null) {
			try {

				// Static getInstance method is called with hashing MD5
				MessageDigest md = MessageDigest.getInstance("MD5");

				// digest() method is called to calculate message digest
				// of an input digest() return array of byte
				byte[] messageDigest = md.digest(data.getBytes());

				// Convert byte array into signum representation
				BigInteger no = new BigInteger(1, messageDigest);

				// Convert message digest into hex value
				String hashtext = no.toString(16);
				while (hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				return hashtext;
			}

			// For specifying wrong message digest algorithms
			catch (NoSuchAlgorithmException e) {
				logger.info(e);
				return "";
			}

		}
		return "";

	}

}
