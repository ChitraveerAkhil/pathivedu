package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.model.LeaveRequest;

public interface LeaveRequestService {

	List<LeaveRequest> fetchLeavesByUser();

	LeaveRequest addLeave(LeaveRequest toAddRequest);

	LeaveRequest editLeave(LeaveRequest toEditRequest);

	String deleteLeave(long id);

}
