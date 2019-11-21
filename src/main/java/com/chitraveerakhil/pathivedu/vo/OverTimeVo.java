package com.chitraveerakhil.pathivedu.vo;

import lombok.Data;

@Data
public class OverTimeVo {

	private long overTimeId;
	private long userId;
	private String overTimeDate;
	private long overTimePeriod;
	private String reason;
	private String overTimeStatus;
	private String lastUpdate;
	private long approvedBy;

}
