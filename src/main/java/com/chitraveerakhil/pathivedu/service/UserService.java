package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

public interface UserService {

	UserProfile fetchUserProfileById(long id);

	List<UserProfile> fetchUserList();

	UserProfile addUser(UserProfileAndPass userProfileAndPass);

	UserProfile createAdmin(UserProfileAndPass userProfileAndPass);

	UserProfile createManager(UserProfileAndPass userProfileAndPass);

	UserProfile updateUser(UserProfileAndPass userProfileAndPass);

}
