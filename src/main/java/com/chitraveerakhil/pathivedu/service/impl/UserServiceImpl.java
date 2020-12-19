package com.chitraveerakhil.pathivedu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import com.chitraveerakhil.pathivedu.cache.service.CacheService;
import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.helper.ObjectValidator;
import com.chitraveerakhil.pathivedu.helper.SecurePassword;
import com.chitraveerakhil.pathivedu.helper.VoPopulator;
import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.model.UserDetail;
import com.chitraveerakhil.pathivedu.repository.UserDetailRepository;
import com.chitraveerakhil.pathivedu.repository.UserRepository;
import com.chitraveerakhil.pathivedu.service.UserService;
import com.chitraveerakhil.pathivedu.vo.UserProfile;
import com.chitraveerakhil.pathivedu.vo.UserProfileAndPass;

@Service
public class UserServiceImpl implements UserService {

	Logger Log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;

	@Autowired
	@Qualifier("userCacheService")
	CacheService<UserProfile> userCacheService;

	@Autowired
	ObjectValidator validator;

	@Override
	public UserProfile createAdmin(UserProfileAndPass userProfileAndPass) {
		User user = populateUser(userProfileAndPass);
		user.setRole(UtilConstants.ROLE_ADMIN);
		UserDetail userDetail = populateUserDetail(userProfileAndPass);
		UserProfile userProfile = saveUser(user, userDetail);
		return userProfile;
	}

	@Override
	public UserProfile createManager(UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = null;
		User user = populateUser(userProfileAndPass);
		user.setRole(UtilConstants.ROLE_MANAGER);

		UserDetail userDetail = populateUserDetail(userProfileAndPass);
		userProfile = saveUser(user, userDetail);
		return userProfile;
	}

	@Override
	public UserProfile addUser(UserProfileAndPass userProfileAndPass) {
		User owner = userRepository.getOne(userProfileAndPass.getUserProfile().getManagerId());
		UserProfile userProfile = null;
		if (UtilConstants.ROLE_MANAGER.equals(owner.getRole()) || UtilConstants.ROLE_ADMIN.equals(owner.getRole())) {
			User user = populateUser(userProfileAndPass);
			user.setRole(UtilConstants.ROLE_USER);
			UserDetail userDetail = populateUserDetail(userProfileAndPass);
			userProfile = saveUser(user, userDetail);
		}
		return userProfile;
	}

	@Override
	public UserProfile updateUser(UserProfileAndPass userProfileAndPass) {
		User user = userRepository.findById(userProfileAndPass.getUserProfile().getUserId()).get();
		user = populateUser(userProfileAndPass);
		user.setId(userProfileAndPass.getUserProfile().getUserId());
		UserDetail userDetail = user.getUserDetail();
		userDetail = populateUserDetail(userProfileAndPass);
		user.setUserDetail(userDetail);
		userDetail.setUser(user);
		user = userRepository.save(user);

		UserProfile userProfile = new UserProfile();
		extractUserProfileResponse(user, userProfile);

		try {
			userCacheService.populateCache(userProfile, userProfile.getUserId());
		} catch (RedisConnectionFailureException e) {
			Log.warn("Redis server is not connected for caching " + e.getLocalizedMessage());
		}
		return userProfile;
	}

	@Override
	public UserProfile fetchUserProfileById(long id) {
		UserProfile userProfile = null;
		try {
			userProfile = userCacheService.getFromCache(id);
		} catch (RedisConnectionFailureException e) {
			Log.warn("Redis server is not connected for caching " + e.getLocalizedMessage());
		}
		if (userProfile == null) {
			userProfile = new UserProfile();
			User user = userRepository.getOne(id);
			extractUserProfileResponse(user, userProfile);

			try {
				userCacheService.populateCache(userProfile, id);
			} catch (RedisConnectionFailureException e) {
				Log.warn("Redis server is not connected for caching " + e.getLocalizedMessage());
			}
		}
		return userProfile;
	}

	@Override
	public List<UserProfile> fetchUserList() {
		List<UserProfile> userProfileList = new ArrayList<>();
		List<User> users = userRepository.findAll();
		users.forEach(user -> {
			UserProfile userProfile = new UserProfile();
			extractUserProfileResponse(user, userProfile);
			userProfileList.add(userProfile);
		});
		return userProfileList;
	}

	@Override
	public User findByUsername(String username) {
		User user = null;
		if (validator.validateMail(username)) {
			user = userRepository.findByEmail(username);
		}

		if (validator.validatePhoneNo(username)) {
			user = userRepository.findByPhoneNumber(username);
		}
		return user;
	}

	private UserDetail populateUserDetail(UserProfileAndPass userProfileAndPass) {
		UserDetail userDetail = new UserDetail();
		VoPopulator<UserProfile, UserDetail> userDetailPopulator = new VoPopulator<>();
		userDetailPopulator.populateObject(userProfileAndPass.getUserProfile(), userDetail);
		return userDetail;
	}

	private UserProfile saveUser(User user, UserDetail userDetail) {
		user.setActive(true);
		user.setUserDetail(userDetail);
		userDetail.setUser(user);
		user = userRepository.save(user);

		UserProfile userProfile = new UserProfile();

		extractUserProfileResponse(user, userProfile);
		try {
			userCacheService.populateCache(userProfile, userProfile.getUserId());
		} catch (RuntimeException e) {
			Log.warn(e.getMessage());
		}
		return userProfile;
	}

	private void extractUserProfileResponse(User user, UserProfile userProfile) {
		VoPopulator<User, UserProfile> userProfileFromUserPopulator = new VoPopulator<>();
		VoPopulator<UserDetail, UserProfile> userProfileFromUserDetailPopulator = new VoPopulator<>();

		userProfileFromUserPopulator.populateObject(user, userProfile);
		userProfileFromUserDetailPopulator.populateObject(user.getUserDetail(), userProfile);

		setRole(userProfile, user);
	}

	private void setRole(UserProfile userProfile, User user) {
		String role = user.getRole();
		switch (role) {
		case UtilConstants.ROLE_ADMIN:
			userProfile.setAdmin(true);
			break;
		case UtilConstants.ROLE_MANAGER:
			userProfile.setManager(true);
			break;
		default:
			break;
		}
	}

	private User populateUser(UserProfileAndPass userProfileAndPass) {
		VoPopulator<UserProfile, User> userPopulator = new VoPopulator<>();
		User user = new User();
		user = userPopulator.populateObject(userProfileAndPass.getUserProfile(), user);
		securePassword(userProfileAndPass.getPassword(), user);
		return user;
	}

	private void securePassword(String password, User user) {
		Map<String, String> generatedPassword = SecurePassword.generateHashedPassword(password);
		user.setHash(generatedPassword.get(UtilConstants.KEY_HASH));
		user.setIterator(Integer.valueOf(generatedPassword.get((UtilConstants.KEY_ITERATOR))));
		user.setSalt(generatedPassword.get(UtilConstants.KEY_SALT));
	}

}
