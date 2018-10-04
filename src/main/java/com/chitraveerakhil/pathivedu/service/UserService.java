package com.chitraveerakhil.pathivedu.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.chitraveerakhil.pathivedu.model.User;

public interface UserService {
	User user() throws NoSuchAlgorithmException, InvalidKeySpecException;
}
