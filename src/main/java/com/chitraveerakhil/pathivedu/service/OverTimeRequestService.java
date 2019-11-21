package com.chitraveerakhil.pathivedu.service;

import java.util.List;

import com.chitraveerakhil.pathivedu.vo.OverTimeVo;
import com.chitraveerakhil.pathivedu.vo.PathiveduRequest;

public interface OverTimeRequestService {

	OverTimeVo editOverTime(PathiveduRequest<OverTimeVo> overTimeVo);

	OverTimeVo addOverTime(PathiveduRequest<OverTimeVo> overTimeVo);

	List<OverTimeVo> fetchOverTimesByUser(long userId);

	String deleteOverTime(PathiveduRequest<Long> request);

}
