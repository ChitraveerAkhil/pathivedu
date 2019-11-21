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
@Table(name = "OVER_TIME_HISTORY")
public class OverTimeHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;

	@Column(name = "OVER_TIME_ID", nullable = false)
	private long overTimeId;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@Column(name = "CHANGES_DONE")
	private String changesDone;

	@Column(name = "CHANGED_BY")
	private Long changedBy;

	@Column(name = "OVER_TIME_DATE_BEFORE")
	private Date overTimeDateBefore;

	@Column(name = "OVER_TIME_DATE_AFTER")
	private Date overTimeDateAfter;

	@Column(name = "OVER_TIME_PERIOD_BEFORE")
	private long overTimePeriodBefore;

	@Column(name = "OVER_TIME_PERIOD_AFTER")
	private long overTimePeriodAfter;
}
