package com.chitraveerakhil.pathivedu.constants;

public class StringUtils {

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (isEmpty(s1) && isEmpty(s2))
			return equals(s1.toLowerCase(), s2.toLowerCase());
		return false;
	}

	public static boolean equals(String s1, String s2) {
		if (isEmpty(s1) && isEmpty(s2))
			return s1.equals(s2);
		return false;
	}

	public static boolean isEmpty(String s) {
		if (s != null && !s.isEmpty())
			return true;
		return false;
	}

}
