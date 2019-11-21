package com.chitraveerakhil.pathivedu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chitraveerakhil.pathivedu.vo.RequestStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "OVER_TIME_REQUEST")
public class OverTimeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(name = "USER_ID")
	private long userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private RequestStatus status;

	@Column(name = "APPLIED_ON")
	private Date appliedOn;

	@Column(name = "OVER_TIME_DATE")
	private Date overTimeDate;

	@Column(name = "OVER_TIME_PERIOD")
	private long overTimePeriod;

	@Column(name = "APPROVED_BY")
	private long approvedBy;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "REASON")
	private String reason;
}
