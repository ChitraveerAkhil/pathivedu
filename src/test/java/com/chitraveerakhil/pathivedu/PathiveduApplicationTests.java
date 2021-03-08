package com.chitraveerakhil.pathivedu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.helper.ObjectValidator;
import com.chitraveerakhil.pathivedu.helper.SecurePassword;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PathiveduApplicationTests {

	@Autowired
	private ObjectValidator validator;

	@Test
	public void testSecurePassword() throws NumberFormatException, NoSuchAlgorithmException, InvalidKeySpecException {
		Map<String, String> generatedPassword = SecurePassword.generateHashedPassword("123456");

		assertTrue(SecurePassword.validatePassword("123456", generatedPassword.get(UtilConstants.KEY_SALT),
				generatedPassword.get(UtilConstants.KEY_HASH),
				Integer.valueOf(generatedPassword.get((UtilConstants.KEY_ITERATOR)))));
		assertFalse(SecurePassword.validatePassword("123467", generatedPassword.get(UtilConstants.KEY_SALT),
				generatedPassword.get(UtilConstants.KEY_HASH),
				Integer.valueOf(generatedPassword.get((UtilConstants.KEY_ITERATOR)))));
	}

	@Test
	public void testObjectValidator() {
		assertFalse(validator.validateMailId("12345"));
		assertFalse(validator.validateMailId("12345@"));
		assertFalse(validator.validateMailId("12345@test"));
		assertFalse(validator.validateMailId("12345 @test"));
		assertFalse(validator.validateMailId("a@b@test.com"));
		assertFalse(validator.validateMailId("a)b@test.com"));
		assertFalse(validator.validateMailId("@test.com"));

		assertFalse(validator.validatePhoneNo("98989"));
		assertFalse(validator.validatePhoneNo("abcd"));
		assertFalse(validator.validatePhoneNo("122abcd234"));
		assertFalse(validator.validatePhoneNo("122234"));

		assertTrue(validator.validateMailId("a.b@test.com"));
		assertTrue(validator.validateMailId("a1.b@test.com"));
		assertTrue(validator.validateMailId("12345@test.com"));
		assertTrue(validator.validateMailId("ab@test.com"));
		assertTrue(validator.validateMailId("123.ab@test.com"));
		assertTrue(validator.validateMailId("a_b@test.com"));

		assertTrue(validator.validatePhoneNo("9191919191"));
	}
}
