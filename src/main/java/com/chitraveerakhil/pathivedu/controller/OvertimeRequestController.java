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

import com.chitraveerakhil.pathivedu.model.OverTimeRequest;
import com.chitraveerakhil.pathivedu.service.OvertimeRequestService;
import com.chitraveerakhil.pathivedu.vo.PathiveduResponse;

@RestController
@RequestMapping("overtime/")
public class OvertimeRequestController {

	@Autowired
	OvertimeRequestService overtimeRequestService;

	@GetMapping("fetchByUser")
	public PathiveduResponse<List<OverTimeRequest>> fetchByUser(long id) {
		List<OverTimeRequest> overtimeRequest = overtimeRequestService.fetchOverTimeByUser();
		return new PathiveduResponse<>(overtimeRequest);
	}

	@PostMapping("request")
	public PathiveduResponse<OverTimeRequest> requestLeave(@RequestBody OverTimeRequest toAddRequest) {
		OverTimeRequest overtimeRequest = overtimeRequestService.addOverTime(toAddRequest);
		return new PathiveduResponse<>(overtimeRequest);
	}

	@PutMapping("edit")
	public PathiveduResponse<OverTimeRequest> editLeave(@RequestBody OverTimeRequest toEditRequest) {
		OverTimeRequest overtimeRequest = overtimeRequestService.editOverTime(toEditRequest);
		return new PathiveduResponse<>(overtimeRequest);
	}

	@DeleteMapping("delete")
	public PathiveduResponse<String> requestLeave(@RequestParam long id) {
		String resp = overtimeRequestService.deleteOverTime(id);
		return new PathiveduResponse<>(resp);
	}
}
