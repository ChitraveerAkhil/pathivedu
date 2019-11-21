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

import com.chitraveerakhil.pathivedu.vo.LeaveType;
import com.chitraveerakhil.pathivedu.vo.RequestStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "LEAVE_REQUEST")
public class LeaveRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(name = "USER_ID", nullable = false)
	private long userId;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private RequestStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "LEAVE_TYPE")
	private LeaveType leaveType;

	@Column(name = "APPLIED_ON")
	private Date appliedOn;

	@Column(name = "LEAVE_FROM")
	private Date leaveFrom;

	@Column(name = "LEAVE_TO")
	private Date leaveTo;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "APPROVED_BY")
	private Long approvedBy;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

}
