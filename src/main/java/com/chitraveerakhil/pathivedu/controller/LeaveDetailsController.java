package com.chitraveerakhil.pathivedu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chitraveerakhil.pathivedu.model.LeaveRequest;
import com.chitraveerakhil.pathivedu.model.PathiveduResponse;
import com.chitraveerakhil.pathivedu.service.LeaveRequestService;

@RestController
@RequestMapping("leaves/")
public class LeaveDetailsController {

	@Autowired
	LeaveRequestService leaveRequestService;
	@GetMapping("fetchByUser")
	public PathiveduResponse<List<LeaveRequest>> fetchByUser(long id) {
		List<LeaveRequest> leaveRequest = leaveRequestService.fetchLeavesByUser();
		return new PathiveduResponse<>(leaveRequest);
	}

	@PostMapping("request")
	public PathiveduResponse<LeaveRequest> requestLeave(@RequestBody LeaveRequest toAddRequest) {
		LeaveRequest leaveRequest = leaveRequestService.addLeave(toAddRequest);
		return new PathiveduResponse<>(leaveRequest);
	}
	@PutMapping("edit")
	public PathiveduResponse<LeaveRequest> editLeave(@RequestBody LeaveRequest toEditRequest) {
		LeaveRequest leaveRequest = leaveRequestService.editLeave(toEditRequest);
		return new PathiveduResponse<>(leaveRequest);
	}
	@DeleteMapping("delete")
	public PathiveduResponse<String> requestLeave(@RequestParam long id) {
		String resp = leaveRequestService.deleteLeave(id);
		return new PathiveduResponse<>(resp);
	}
}
