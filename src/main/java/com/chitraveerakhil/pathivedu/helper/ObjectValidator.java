package com.chitraveerakhil.pathivedu.helper;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ObjectValidator {

	private static final String mailRegex = "^(.+)@(.+)$";
	private static final String mobileNoRegex = "\\d{10}";

	public boolean validateMail(String email) {
		return Pattern.compile(mailRegex).matcher(email).matches();
	}
	
	public boolean validatePhoneNo(String mobileNo){
		return Pattern.compile(mobileNoRegex).matcher(mobileNo).matches();
	}
}
