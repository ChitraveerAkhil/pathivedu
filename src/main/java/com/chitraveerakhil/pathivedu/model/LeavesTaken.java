package com.chitraveerakhil.pathivedu.model;

import lombok.Data;

@Data
public class LeavesTaken {

	private long userId;
	private int casualLeave;
	private int sickLeave;
	private int plannedLeave;
	private int religiousLeave;
}
