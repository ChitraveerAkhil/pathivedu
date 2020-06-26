package com.chitraveerakhil.pathivedu.helper;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.chitraveerakhil.pathivedu.repository.UserRepository;
import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@Component
public class ObjectInitializer {

	Logger logger = Logger.getLogger(ObjectInitializer.class);
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Bean
	private void createUser() {
		long count = userRepository.count();

		if (count == 0) {
			UserProfileAndPass userProfileAndPass = new UserProfileAndPass();
			userProfileAndPass.setPassword("password");
			UserProfile userProfile = new UserProfile();
			userProfile.setDesignation("Admin");
			userProfile.setFirstName("Test");
			userProfile.setLastName("User");
			userProfile.setEmail("test@test.com");
			userProfile.setPhoneNumber("1010101010");
			userProfileAndPass.setUserProfile(userProfile);
			userService.createAdmin(userProfileAndPass);
			logger.log(null, "Object Created");
		}
	}
}
