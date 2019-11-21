package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.vo.LeaveVo;
import com.chitraveerakhil.pathivedu.vo.PathiveduRequest;

public interface LeaveRequestService {

	LeaveVo addLeave(PathiveduRequest<LeaveVo> toAddRequest);

	LeaveVo editLeave(PathiveduRequest<LeaveVo> toEditRequest);

	List<LeaveVo> fetchLeavesByUser(long userId);

	String deleteLeave(PathiveduRequest<Long> request);

}
