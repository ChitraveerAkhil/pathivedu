package com.chitraveerakhil.pathivedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.service.LeaveRequestService;
import com.chitraveerakhil.pathivedu.vo.LeaveVo;
import com.chitraveerakhil.pathivedu.vo.PathiveduRequest;
import com.chitraveerakhil.pathivedu.vo.PathiveduResponse;

@RestController
@RequestMapping("leaves")
public class LeaveDetailsController {

	@Autowired
	LeaveRequestService leaveRequestService;

	@PostMapping
	public PathiveduResponse<LeaveVo> requestLeave(@RequestBody PathiveduRequest<LeaveVo> toAddRequest) {
		LeaveVo LeaveVo = leaveRequestService.addLeave(toAddRequest);
		return new PathiveduResponse<>(LeaveVo);
	}

	@PutMapping
	public PathiveduResponse<LeaveVo> editLeave(@RequestBody PathiveduRequest<LeaveVo> toEditRequest) {
		LeaveVo LeaveVo = leaveRequestService.editLeave(toEditRequest);
		return new PathiveduResponse<>(LeaveVo);
	}

	@RequestMapping("fetchByUser")
	public PathiveduResponse<List<LeaveVo>> fetchByUser(@RequestParam("id") long userId) {
		List<LeaveVo> LeaveVo = leaveRequestService.fetchLeavesByUser(userId);
		return new PathiveduResponse<>(LeaveVo);
	}

	@DeleteMapping
	public PathiveduResponse<String> deleteLeave(@RequestBody PathiveduRequest<Long> request) {
		String resp = leaveRequestService.deleteLeave(request);
		return new PathiveduResponse<>(resp);
	}
}
