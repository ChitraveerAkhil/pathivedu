package com.chitraveerakhil.pathivedu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OverTimeRequest {

	private long id;
	@ManyToOne
	@JoinColumn(name = "userId")
	private long userId;
	private boolean status;
	private int minutes;
	private Date leaveOn;
	private Date appliedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Date getLeaveOn() {
		return leaveOn;
	}

	public void setLeaveOn(Date leaveOn) {
		this.leaveOn = leaveOn;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}
}
