package com.chitraveerakhil.pathivedu.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "SALT")
	private String salt;

	@Column(name = "HASH")
	private String hash;

	@Column(name = "ITERATOR")
	private int iterator;

	@Column(name = "is_active")
	private boolean isActive;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private UserDetail userDetail;
}
