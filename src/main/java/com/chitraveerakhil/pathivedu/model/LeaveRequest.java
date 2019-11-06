package com.chitraveerakhil.pathivedu.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LeaveRequest {

	private long id;
	private long userId;
	private LeaveType leaveType; 
	private boolean status;
	private List<Date> leaveOn;
	private Date appliedOn;
}
