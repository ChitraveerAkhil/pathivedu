package com.chitraveerakhil.pathivedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.PathiveduResponse;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("createAdmin")
	public PathiveduResponse<UserProfile> createAdmin(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.createAdmin(userProfileAndPass);
		return new PathiveduResponse<UserProfile>(userProfile);
	}

	@PostMapping("createManager")
	public PathiveduResponse<?> createManager(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.createManager(userProfileAndPass);
		PathiveduResponse<?> response = null;
		if (userProfile != null) {
			response = new PathiveduResponse<UserProfile>(userProfile);
		} else {
			response = new PathiveduResponse<String>("error", "Unable to create manager", "401",
					"Manager can be created only by Admin");
		}
		return response;
	}

	@PostMapping("add")
	public PathiveduResponse<?> addUser(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.addUser(userProfileAndPass);
		PathiveduResponse<?> response = null;
		if (userProfile != null) {
			response = new PathiveduResponse<UserProfile>(userProfile);
		} else {
			response = new PathiveduResponse<String>("error", "Unable to create user", "401",
					"User can be created only by Admin or Manager");
		}
		return response;
	}

	@PutMapping("update")
	public PathiveduResponse<UserProfile> updateUser(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.updateUser(userProfileAndPass);
		return new PathiveduResponse<UserProfile>(userProfile);
	}
	
	@GetMapping("fetchById")
	public PathiveduResponse<UserProfile> fetchUserProfile(@RequestParam long id) {
		UserProfile userProfile = userService.fetchUserProfileById(id);

		return new PathiveduResponse<UserProfile>(userProfile);
	}

	@GetMapping("fetchAll")
	public PathiveduResponse<List<UserProfile>> fetchAllUsers() {
		List<UserProfile> userList = userService.fetchUserList();
		return new PathiveduResponse<List<UserProfile>>(userList);

	}
}
