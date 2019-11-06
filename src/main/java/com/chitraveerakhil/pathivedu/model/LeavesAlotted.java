package com.chitraveerakhil.pathivedu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LeavesAlotted {

	private long userId;
	private int casualLeave;
	private int sickLeave;
	private int plannedLeave;
	private int religiousLeave;
}
