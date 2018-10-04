package com.chitraveerakhil.pathivedu.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class LeavesAlotted {

	@NotNull
	@OneToOne
	@JoinColumn(name = "user_id")
	private long userId;
	private int casualLeave;
	private int sickLeave;
	private int plannedLeave;
	private int religiousLeave;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getCasualLeave() {
		return casualLeave;
	}

	public void setCasualLeave(int casualLeave) {
		this.casualLeave = casualLeave;
	}

	public int getSickLeave() {
		return sickLeave;
	}

	public void setSickLeave(int sickLeave) {
		this.sickLeave = sickLeave;
	}

	public int getPlannedLeave() {
		return plannedLeave;
	}

	public void setPlannedLeave(int plannedLeave) {
		this.plannedLeave = plannedLeave;
	}

	public int getReligiousLeave() {
		return religiousLeave;
	}

	public void setReligiousLeave(int religiousLeave) {
		this.religiousLeave = religiousLeave;
	}
}
