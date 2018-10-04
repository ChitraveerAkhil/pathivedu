package com.chitraveerakhil.pathivedu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class LeaveRequest {

	private long id;
	@OneToOne
	@JoinColumn (name = "userId")
	private long userId;
	private String leaveType;
	private boolean status;
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
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
