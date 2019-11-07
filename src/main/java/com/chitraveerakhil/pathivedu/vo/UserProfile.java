package com.chitraveerakhil.pathivedu.vo;

import lombok.Data;

@Data
public class UserProfile {

	private long userId;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String designation;
	private long managerId;
	private boolean isAdmin;
	private boolean isManager;
	private long salary;
	private boolean isActive;
	private String dob;
	private String residentAddress;
	private String permanentAddress;
	private String residentLocality;
	private String residentCity;
}
