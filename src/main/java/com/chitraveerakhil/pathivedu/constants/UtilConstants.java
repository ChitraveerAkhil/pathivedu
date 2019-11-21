package com.chitraveerakhil.pathivedu.constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilConstants {

	public static final String REPOSITORY_LOCATION = "com.chitraveerakhil.pathivedu.repository";
	public static final String PROPS_FILE_LOCATION = "classpath:application.yaml";
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
