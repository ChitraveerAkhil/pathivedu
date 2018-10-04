package com.chitraveerakhil.pathivedu.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import com.chitraveerakhil.pathivedu.controls.SecurePassword;
import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.model.UserPassword;

public class ApiService implements UserService {

	String name = "akhil";
	String password = "1234";
	String email = "akhil@nextonder.com";
	String phoneNumber = "9898989898";

	public User user() throws NoSuchAlgorithmException, InvalidKeySpecException {
		final User user = new User();
		user.setFirstName(name);
		user.setEmail(email);
		user.setId(0);
		user.setPhoneNumber(phoneNumber);
		UserPassword userPassword = new UserPassword();
		Map<String, Object> passKeys = SecurePassword.generateHashedPassword(password);
		userPassword.setHashedPassword((String) passKeys.get("hash"));
		userPassword.setIterator((int) passKeys.get("iterator"));
		userPassword.setSalt((String) passKeys.get("salt"));

		boolean status = SecurePassword.validatePassword(password, userPassword.getSalt(),
				userPassword.getHashedPassword(), userPassword.getIterator());
		if (status == true) {
			return user;
		}
		return null;

	}
}
