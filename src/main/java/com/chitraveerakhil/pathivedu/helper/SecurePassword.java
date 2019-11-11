package com.chitraveerakhil.pathivedu.helper;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;

public class SecurePassword {

	static final int ITERATOR_MAX_LIMIT = 4000;
	static final int ITERATOR_MIN_LIMIT = 1000;
	static final String SECRET_KEY = "PBKDF2WithHmacSHA1";

	private static final String RANDOM_SECURE = "SHA1PRNG";

	public static Map<String, String> generateHashedPassword(String password) {
		Map<String, String> hashed = new HashMap<>();
		char[] chars = password.toCharArray();
		try {
			byte[] salt = getSalt();
			int iterator = (int) (Math.random() * 4000 + 1000);
			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterator, 64 * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY);
			byte[] hash = skf.generateSecret(spec).getEncoded();
			hashed.put(UtilConstants.KEY_HASH, toHex(hash));
			hashed.put(UtilConstants.KEY_SALT, toHex(salt));
			hashed.put(UtilConstants.KEY_ITERATOR, String.valueOf(iterator));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return hashed;
	}

	public static boolean validatePassword(String originalPassword, String storedSalt, String storedHash,
			int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = fromHex(storedSalt);
		byte[] hash = fromHex(storedHash);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY);
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance(RANDOM_SECURE);
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
}
