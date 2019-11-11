package com.chitraveerakhil.pathivedu.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.hleper.SecurePassword;
import com.chitraveerakhil.pathivedu.hleper.VoPopulator;
import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.model.UserDetail;
import com.chitraveerakhil.pathivedu.repository.UserDetailRepository;
import com.chitraveerakhil.pathivedu.repository.UserRepository;
import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public UserProfile fetchUserProfileById(long id) {
		return null;
	}

	private static final Logger Log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserProfile addUser(UserProfileAndPass userProfileAndPass) {
		Log.debug("Adding user " + userProfileAndPass.getUserProfile());
		VoPopulator<UserProfile, User> userPopulator = new VoPopulator<>();
		User user = new User();
		user = userPopulator.populateObject(userProfileAndPass.getUserProfile(), user);
		securePassword(userProfileAndPass.getPassword(), user);

		UserDetail userDetail = new UserDetail();
		VoPopulator<UserProfile, UserDetail> userDetailPopulator = new VoPopulator<>();
		userDetailPopulator.populateObject(userProfileAndPass.getUserProfile(), userDetail);

		user.setUserDetail(userDetail);
		userDetail.setUser(user);
		user = userRepository.save(user);

		Log.debug("New User Added::" + user);

		UserProfile userProfile = new UserProfile();

		extractUserProfileResponse(user, userProfile);

		return userProfile;
	}

	private void extractUserProfileResponse(User user, UserProfile userProfile) {
		VoPopulator<User, UserProfile> userProfileFromUserPopulator = new VoPopulator<>();
		VoPopulator<UserDetail, UserProfile> userProfileFromUserDetailPopulator = new VoPopulator<>();

		userProfileFromUserPopulator.populateObject(user, userProfile);
		userProfileFromUserDetailPopulator.populateObject(user.getUserDetail(), userProfile);

		Log.debug(userProfile.toString());
	}

	public void securePassword(String password, User user) {
		Map<String, String> generatedPassword = SecurePassword.generateHashedPassword(password);
		user.setHash(generatedPassword.get(UtilConstants.KEY_HASH));
		user.setIterator(Integer.valueOf(generatedPassword.get((UtilConstants.KEY_ITERATOR))));
		user.setSalt(generatedPassword.get(UtilConstants.KEY_SALT));
	}

	@Override
	public UserProfile updateUser(UserProfile user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserProfile> fetchUserList() {
		// TODO Auto-generated method stub
		return null;
	}

}
