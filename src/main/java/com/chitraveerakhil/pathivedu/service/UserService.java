package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

public interface UserService {

	UserProfile createAdmin(UserProfileAndPass userProfileAndPass);

	UserProfile createManager(UserProfileAndPass userProfileAndPass);

	UserProfile addUser(UserProfileAndPass userProfileAndPass);

	UserProfile updateUser(UserProfileAndPass userProfileAndPass);

	UserProfile fetchUserProfileById(long id);

	List<UserProfile> fetchUserList();
	
	User findByUsername(String username);

}
