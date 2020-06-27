package com.chitraveerakhil.pathivedu.helper;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.chitraveerakhil.pathivedu.constants.UtilConstants;

@Component
public class ObjectValidator {

	public boolean validateMail(String email) {
		return Pattern.compile(UtilConstants.MAIL_REGEX).matcher(email).matches();
	}
	
	public boolean validatePhoneNo(String mobileNo){
		return Pattern.compile(UtilConstants.MOBILE_REGEX).matcher(mobileNo).matches();
	}
}
