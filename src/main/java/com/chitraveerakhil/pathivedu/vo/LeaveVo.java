package com.chitraveerakhil.pathivedu.vo;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("leave_vo")
public class LeaveVo implements Serializable {

	private static final long serialVersionUID = -5599867736199829601L;

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
