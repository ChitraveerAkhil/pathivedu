package com.chitraveerakhil.pathivedu.controls;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecurePassword {

	static int iterator;
	static final int ITERATOR_MAX_LIMIT = 4000;
	static final int ITERATOR_MIN_LIMIT = 1000;
	static final String SECRET_KEY = "PBKDF2WithHmacSHA1";
	private static final String KEY_HASH = "hash";
	private static final String KEY_SALT = "salt";
	private static final String RANDOM_SECURE = "SHA1PRNG";
	private static final String KEY_ITERATOR = "iterator";

	public static Map<String, Object> generateHashedPassword(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();

		iterator = (int) (Math.random() * 4000 + 1000);

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterator, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY);
		byte[] hash = skf.generateSecret(spec).getEncoded();
		Map<String, Object> hashed = new HashMap<>();
		hashed.put(KEY_HASH, toHex(hash));
		hashed.put(KEY_SALT, toHex(salt));
		hashed.put(KEY_ITERATOR, iterator);
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
