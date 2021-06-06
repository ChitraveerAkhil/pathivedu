package com.chitraveerakhil.pathivedu;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.helper.VoPopulator;
import com.chitraveerakhil.pathivedu.model.User;
import com.chitraveerakhil.pathivedu.model.UserDetail;
import com.chitraveerakhil.pathivedu.vo.UserProfile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectVoTest {

	private User user;
	private UserDetail detail;
	private UserProfile userProfile;
	private VoPopulator<User, UserProfile> userProfileFromUserPopulator;
	private VoPopulator<UserDetail, UserProfile> userProfileFromUserDetailPopulator;

	@Before
	public void init() {
		user = new User();
		detail = new UserDetail();
		userProfile = new UserProfile();
		user.setActive(true);
		user.setEmail("test@test.com");
		user.setHash("ahdoaoiodknalk");
		user.setIterator(22);
		user.setId(0);
		user.setPhoneNumber("1111100000");
		user.setRole(UtilConstants.ROLE_USER);
		user.setSalt("90903092342");

		detail.setDesignation("Developer");
		detail.setDob(new Date());
		detail.setFirstName("First");
		detail.setLastName("Last");
		detail.setManagerId(0);
		detail.setPermanentAddress("100 Xx Streeet, YYYY, ZZZZ-4321");
		detail.setResidentAddress("100 Xx Streeet, YYYY, ZZZZ-4321");
		detail.setResidentCity("ZZZZ");
		detail.setResidentLocality("YYYY");
		detail.setSalary(100000000L);
		detail.setUser(user);

		user.setUserDetail(detail);

		userProfileFromUserPopulator = new VoPopulator<>();
		userProfileFromUserDetailPopulator = new VoPopulator<>();

		userProfileFromUserPopulator.populateObject(user, userProfile);
		userProfileFromUserDetailPopulator.populateObject(user.getUserDetail(), userProfile);
	}

	@Test
	public void testVoPopulator() {

		assertEquals(userProfile.getDesignation(), detail.getDesignation());
		assertEquals(userProfile.getEmail(), user.getEmail());
		assertEquals(userProfile.getFirstName(), detail.getFirstName());
		assertEquals(userProfile.getLastName(), detail.getLastName());
		assertEquals(userProfile.getLastName(), detail.getLastName());
		assertEquals(userProfile.getManagerId(), detail.getManagerId());
		assertEquals(userProfile.getPermanentAddress(), detail.getPermanentAddress());
		assertEquals(userProfile.getPhoneNumber(), user.getPhoneNumber());
		assertEquals(userProfile.getResidentAddress(), detail.getResidentAddress());
		assertEquals(userProfile.getResidentCity(), detail.getResidentCity());
		assertEquals(userProfile.getResidentLocality(), detail.getResidentLocality());
		assertEquals(userProfile.getSalary(), detail.getSalary());
		assertEquals(userProfile.getUserId(), detail.getUser().getId());
		assertEquals(userProfile.isActive(),user.isActive());
	}
}
