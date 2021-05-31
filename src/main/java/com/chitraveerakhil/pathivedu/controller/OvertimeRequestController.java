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

import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.service.OverTimeRequestService;
import com.chitraveerakhil.pathivedu.vo.OverTimeVo;
import com.chitraveerakhil.pathivedu.vo.PathiveduRequest;
import com.chitraveerakhil.pathivedu.vo.PathiveduResponse;

@RestController
@RequestMapping("overtime/")
public class OvertimeRequestController {

	@Autowired
	OverTimeRequestService overTimeRequestService;

	@GetMapping("fetchByUser")
	public PathiveduResponse<List<OverTimeVo>> fetchByUser(@RequestParam("userId") long id) {
		List<OverTimeVo> response = overTimeRequestService.fetchOverTimesByUser(id);
		return new PathiveduResponse<>(response);
	}

	@PostMapping
	public PathiveduResponse<OverTimeVo> requestOverTime(@RequestBody PathiveduRequest<OverTimeVo> toAddRequest) {
		OverTimeVo overtimeRequest = overTimeRequestService.addOverTime(toAddRequest);
		return new PathiveduResponse<>(overtimeRequest, UtilConstants.OVERTIME_CREATED_RESPONSE);
	}

	@PutMapping
	public PathiveduResponse<OverTimeVo> editOverTime(@RequestBody PathiveduRequest<OverTimeVo> toEditRequest) {
		OverTimeVo overtimeRequest = overTimeRequestService.editOverTime(toEditRequest);
		return new PathiveduResponse<>(overtimeRequest, UtilConstants.OVERTIME_UPDATED_RESPONSE);
	}

	@DeleteMapping
	public PathiveduResponse<String> deleteOverTime(@RequestParam("userId") PathiveduRequest<Long> id) {
		return new PathiveduResponse<>(overTimeRequestService.deleteOverTime(id), "204");
	}
}
