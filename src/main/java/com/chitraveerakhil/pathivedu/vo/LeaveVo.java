package com.chitraveerakhil.pathivedu.vo;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("leave_vo")
public class LeaveVo {

	@Id
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
