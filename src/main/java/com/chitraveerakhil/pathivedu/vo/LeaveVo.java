package com.chitraveerakhil.pathivedu.vo;

import lombok.Data;

@Data
public class LeaveVo {

	private long leaveId;
	private long userId;
	private String leaveFrom;
	private String leaveTo;
	private String reason;
	private String leaveStatus;
	private String lastUpdate;
	private String leaveType;
	private long approvedBy;

}
