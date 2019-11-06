package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.model.OverTimeRequest;

public interface OvertimeRequestService {

	List<OverTimeRequest> fetchOverTimeByUser();

	OverTimeRequest addOverTime(OverTimeRequest toAddRequest);

	OverTimeRequest editOverTime(OverTimeRequest toEditRequest);

	String deleteOverTime(long id);

}
