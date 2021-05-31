package com.chitraveerakhil.pathivedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.PathiveduResponse;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@CrossOrigin
@RestController
@RequestMapping("users/")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("createAdmin")
	public PathiveduResponse<UserProfile> createAdmin(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.createAdmin(userProfileAndPass);
		return new PathiveduResponse<UserProfile>(userProfile, UtilConstants.ADMIN_CREATED_RESPONSE);
	}

	@PostMapping("createManager")
	public PathiveduResponse<?> createManager(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.createManager(userProfileAndPass);
		PathiveduResponse<?> response = null;
		response = new PathiveduResponse<UserProfile>(userProfile, UtilConstants.MANAGER_CREATED_RESPONSE);
		return response;
	}

	@PostMapping()
	public PathiveduResponse<?> add(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.addUser(userProfileAndPass);
		PathiveduResponse<?> response = null;
		response = new PathiveduResponse<UserProfile>(userProfile, UtilConstants.USER_CREATED_RESPONSE);
		return response;
	}

	@PutMapping()
	public PathiveduResponse<UserProfile> update(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.updateUser(userProfileAndPass);
		return new PathiveduResponse<UserProfile>(userProfile, UtilConstants.USER_UPDATED_RESPONSE);
	}

	@GetMapping("{id}")
	public PathiveduResponse<UserProfile> get(@PathVariable Long id) {
		UserProfile userProfile = userService.fetchUserProfileById(id);
		return new PathiveduResponse<UserProfile>(userProfile);
	}

	@GetMapping()
	public PathiveduResponse<List<UserProfile>> fetchUsers() {
		List<UserProfile> userList = userService.fetchUserList();
		return new PathiveduResponse<List<UserProfile>>(userList);
	}
}
