package com.chitraveerakhil.pathivedu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.hleper.SecurePassword;
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

	@Override
	public UserProfile addUser(UserProfileAndPass userProfileAndPass) {
		User user = populateUser(userProfileAndPass);
		UserDetail userDetail = new UserDetail();
		UserProfile userProfile = populateUserDetail(userProfileAndPass, userDetail);

		user.setUserDetail(userDetail);
		userDetail.setUser(user);
		user = userRepository.save(user);

		extractUserProfileResponse(user, userProfile);

		return userProfile;
	}

	private User populateUser(UserProfileAndPass userProfileAndPass) {
		UserProfile userProfile = userProfileAndPass.getUserProfile();
		User user = new User();
		user.setActive(userProfile.isActive());
		user.setEmail(userProfile.getEmail());
		user.setPhoneNumber(userProfile.getPhoneNumber());

		securePassword(userProfileAndPass.getPassword(), user);

		return user;
	}

	public UserProfile populateUserDetail(UserProfileAndPass userProfileAndPass, UserDetail userDetail) {
		UserProfile userProfile = userProfileAndPass.getUserProfile();
		userDetail.setAdmin(userProfile.isAdmin());
		userDetail.setDesignation(userProfile.getDesignation());
		userDetail.setFirstName(userProfile.getFirstName());
		userDetail.setLastName(userProfile.getLastName());
		userDetail.setManager(userProfile.isManager());
		userDetail.setManagerId(0);
		userDetail.setPhoneNumber(userProfile.getPhoneNumber());
		userDetail.setSalary(userProfile.getSalary());
		userDetail.setPermanentAddress(userProfile.getPermanentAddress());
		userDetail.setResidentAddress(userProfile.getResidentAddress());
		userDetail.setResidentCity(userProfile.getResidentCity());
		userDetail.setResidentLocality(userProfile.getResidentLocality());
		userDetail.setDob(UtilConstants.strToDate(userProfile.getDob()));
		return userProfile;
	}

	public void extractUserProfileResponse(User user, UserProfile userProfile) {
		userProfile.setUserId(user.getId());
		userProfile.setAdmin(user.getUserDetail().isAdmin());
		userProfile.setDesignation(user.getUserDetail().getDesignation());
		userProfile.setEmail(user.getEmail());
		userProfile.setPhoneNumber(user.getPhoneNumber());
		userProfile.setFirstName(user.getUserDetail().getFirstName());
		userProfile.setLastName(user.getUserDetail().getLastName());
		userProfile.setManager(user.getUserDetail().isManager());
		userProfile.setManagerId(user.getUserDetail().getManagerId());
		userProfile.setActive(user.isActive());
		userProfile.setSalary(user.getUserDetail().getSalary());
		userProfile.setPermanentAddress(user.getUserDetail().getPermanentAddress());
		userProfile.setResidentAddress(user.getUserDetail().getResidentAddress());
		userProfile.setDob(UtilConstants.dateToStr(user.getUserDetail().getDob()));
		userProfile.setResidentLocality(user.getUserDetail().getResidentLocality());
		userProfile.setResidentCity(user.getUserDetail().getResidentCity());
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
