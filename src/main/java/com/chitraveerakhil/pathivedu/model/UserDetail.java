package com.chitraveerakhil.pathivedu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_DETAIL")
public class UserDetail {

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private long userId;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	private String designation;

	@Column(name = "MANAGER_ID")
	private long managerId;

	@Column(name = "IS_ADMIN")
	private boolean isAdmin;

	@Column(name = "IS_MANAGER")
	private boolean isManager;

	@Column(name = "SALARY")
	private long salary;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "RESIDENT_ADDRESS")
	private String residentAddress;

	@Column(name = "PERMANENT_ADDRESS")
	private String permanentAddress;
	
	@Column(name = "RESIDENT_LOCALITY")
	private String residentLocality;
	
	@Column(name = "RESIDENT_CITY")
	private String residentCity;

}
