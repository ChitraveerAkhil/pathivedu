package com.chitraveerakhil.pathivedu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "leave_history")
public class LeaveHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(name = "LEAVE_ID", nullable = false)
	private long leaveId;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@Column(name = "CHANGES_DONE")
	private String changesDone;

	@Column(name = "FROM_DATE_BEFORE")
	private Date fromDateBefore;

	@Column(name = "FROM_DATE_AFTER")
	private Date fromDateAfter;

	@Column(name = "TO_DATE_BEFORE")
	private Date toDateBefore;

	@Column(name = "TO_DATE_AFTER")
	private Date toDateAfter;

	@Column(name = "CHANGED_BY")
	private Long changedBy;

}
