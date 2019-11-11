package com.chitraveerakhil.pathivedu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LEAVES_ALOTTED")
public class LeavesAlotted {

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)
	private long userId;

	@Column(name = "PLANNED_LEAVE")
	private int plannedLeave;

	@Column(name = "SICK_LEAVE")
	private int sickLeave;
	
	@Column(name = "CASUAL_LEAVE")
	private int casualLeave;

	@Column(name = "COMP_OFF")
	private int compOff;
}
