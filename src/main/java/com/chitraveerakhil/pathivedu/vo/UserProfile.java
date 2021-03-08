package com.chitraveerakhil.pathivedu.vo;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.ToString;

@Data
@RedisHash("user_profile")
public class UserProfile implements Serializable {
	
	private static final long serialVersionUID = -6151628180119398969L;

	@Id
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
