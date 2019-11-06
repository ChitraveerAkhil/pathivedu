package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.vo.UserProfile;

public interface UserService {

	UserProfile fetchUserProfileById(long id);

	UserProfile updateUser(UserProfile user);

	String deleteUser(long id);

	List<UserProfile> fetchUserList();

	UserProfile addUser(UserProfile userProfile, String password);

}
