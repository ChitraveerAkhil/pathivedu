package com.chitraveerakhil.pathivedu.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OverTimeRequest {

	private long id;
	private long userId;
	private boolean status;
	private int minutes;
	private Date leaveOn;
	private Date appliedOn;
	
}
