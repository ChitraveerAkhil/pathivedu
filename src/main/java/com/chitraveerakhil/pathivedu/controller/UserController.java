package com.chitraveerakhil.pathivedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.model.PathiveduResponse;
import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("profileList")
	public PathiveduResponse<UserProfile> fetchUserProfile(@RequestParam long id) {
		UserProfile userProfile = userService.fetchUserProfileById(id);

		return new PathiveduResponse<UserProfile>(userProfile);
	}

	@PostMapping("add")
	public PathiveduResponse<UserProfile> addUser(@RequestBody UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userService.addUser(userProfileAndPass.getUserProfile(),
				userProfileAndPass.getPassword());
		return new PathiveduResponse<UserProfile>(userProfile);
	}

	@PutMapping("update")
	public PathiveduResponse<UserProfile> updateUser(@RequestBody UserProfile user) {
		UserProfile userProfile = userService.updateUser(user);
		return new PathiveduResponse<UserProfile>(userProfile);
	}

	@DeleteMapping("delete")
	public PathiveduResponse<String> deleteUser(@RequestParam long id) {
		String resp = userService.deleteUser(id);
		return new PathiveduResponse<String>(resp);
	}

	@GetMapping("fetchAll")
	public PathiveduResponse<List<UserProfile>> fetchAllUsers() {
		List<UserProfile> userList = userService.fetchUserList();
		return new PathiveduResponse<List<UserProfile>>(userList);

	}
}
