package com.chitraveerakhil.pathivedu.helper;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.repository.UserRepository;
import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@Component
public class ObjectInitializer {

	@Value("temp.user.firstname")
	private String TEMP_FIRST_NAME;

	@Value("temp.user.lastname")
	private String TEMP_LAST_NAME;

	@Value("temp.user.email")
	private String TEMP_EMAIL;

	@Value("temp.user.phoneNumber")
	private String TEMP_PHONE_NUMBER;

	@Value("temp.user.pass")
	private String TEMP_USER_PASS;

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
			userProfileAndPass.setPassword(TEMP_USER_PASS);
			UserProfile userProfile = new UserProfile();
			userProfile.setDesignation(UtilConstants.ROLE_ADMIN);
			userProfile.setFirstName(TEMP_FIRST_NAME);
			userProfile.setLastName(TEMP_LAST_NAME);
			userProfile.setEmail(TEMP_EMAIL);
			userProfile.setPhoneNumber(TEMP_PHONE_NUMBER);
			userProfileAndPass.setUserProfile(userProfile);
			userService.createAdmin(userProfileAndPass);
			logger.log(null, "Object Created");
		}
	}
}
