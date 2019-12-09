package com.chitraveerakhil.pathivedu.vo;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("over_time_vo")
public class OverTimeVo {

	@Id
	private long overTimeId;
	private long userId;
	private String overTimeDate;
	private long overTimePeriod;
	private String reason;
	private String overTimeStatus;
	private String lastUpdate;
	private long approvedBy;

}
