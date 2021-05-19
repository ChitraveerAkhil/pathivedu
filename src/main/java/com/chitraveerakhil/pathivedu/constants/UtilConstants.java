package com.chitraveerakhil.pathivedu.constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilConstants {

	public static final String REPOSITORY_LOCATION = "com.chitraveerakhil.pathivedu.repository";
//	public static final String PROPS_FILE_LOCATION = "classpath:application.yaml";
	public static final String ENTITY_LOCATION = "com.chitraveerakhil.pathivedu.model";

	public static final String KEY_ITERATOR = "iterator";
	public static final String KEY_HASH = "hash";
	public static final String KEY_SALT = "salt";
	private static final String DATE_FORMAT = "yyyy-mm-dd";
	public static final String STR_GET = "get";
	public static final String STR_SET = "set";
	public static final String STR_IS = "is";

	public static final String ENUM_APPROVED = "APPROVED";
	public static final String ENUM_DENIED = "DENIED";
	public static final String ENUM_PENDING = "PENDING";

	public static final String ROLE_ADMIN = "ADMIN";
	public static final String ROLE_MANAGER = "MANAGER";
	public static final String ROLE_USER = "USER";

	public static final String AUTH_HEADER = "Authorization";
	public static final String LOGIN_URL = "/login";

	public static final String LEAVE_CREATED_RESPONSE = "Leave Request Created Successfully";
	public static final String LEAVE_UPDATED_RESPONSE = "Overtime Request Updated Successfully";
	public static final String OVERTIME_CREATED_RESPONSE = "Leave Request Created Successfully";
	public static final String OVERTIME_UPDATED_RESPONSE = "Overtime Request Updated Successfully";
	public static final String ADMIN_CREATED_RESPONSE = "Admin Created Successfully";;
	public static final String MANAGER_CREATED_RESPONSE = "Manager Created Successfully";;
	public static final String USER_CREATED_RESPONSE = "User Created Successfully";
	public static final String USER_UPDATED_RESPONSE = "User Updated Successfully";
	public static final String LEAVE_DELETED_RESPONSE = "Leave Request Deleted";
	public static final String OVERTIME_DELETED_RESPONSE = "Overtime Request Deleted";

	public static final String MAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String MOBILE_REGEX = "\\d{10}";

	public static String dateToStr(Date dob) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(dob);
	}

	public static Date strToDate(String date) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date retrnDate = null;
		try {
			retrnDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retrnDate;
	}

}
