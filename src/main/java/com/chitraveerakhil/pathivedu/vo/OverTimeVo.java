package com.chitraveerakhil.pathivedu.vo;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("over_time_vo")
public class OverTimeVo implements Serializable {

	private static final long serialVersionUID = 1140504089902869787L;

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
