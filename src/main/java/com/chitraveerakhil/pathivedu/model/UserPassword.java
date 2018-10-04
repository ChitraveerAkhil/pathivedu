package com.chitraveerakhil.pathivedu.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class UserPassword {

	@JoinColumn(name = "user_id")
	private long userId;
	private String salt;
	private String hashedPassword;
	private int iterator;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public int getIterator() {
		return iterator;
	}

	public void setIterator(int iterator) {
		this.iterator = iterator;
	}

}
