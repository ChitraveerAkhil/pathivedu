package com.chitraveerakhil.pathivedu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_DETAIL")
public class UserDetail {

	@Id
	@Column(name = "USER_ID")
	private long userId;

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
	
	@OneToOne
	@MapsId
	private User user;

}
