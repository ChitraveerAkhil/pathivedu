package com.chitraveerakhil.pathivedu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.chitraveerakhil.pathivedu.helper.VoPopulator;
import com.chitraveerakhil.pathivedu.model.OverTimeHistory;
import com.chitraveerakhil.pathivedu.model.OverTimeRequest;
import com.chitraveerakhil.pathivedu.repository.OverTimeHistoryRepository;
import com.chitraveerakhil.pathivedu.repository.OverTimeRequestRepository;
import com.chitraveerakhil.pathivedu.service.OverTimeRequestService;
import com.chitraveerakhil.pathivedu.vo.OverTimeVo;
import com.chitraveerakhil.pathivedu.vo.PathiveduRequest;
import com.chitraveerakhil.pathivedu.vo.RequestStatus;

@Service
public class OverTimeRequestServiceImpl implements OverTimeRequestService {

	@Autowired
	OverTimeRequestRepository overTimeRequestRepository;

	@Autowired
	OverTimeHistoryRepository overTimeHistoryRepository;

	@Override
	public OverTimeVo addOverTime(PathiveduRequest<OverTimeVo> overTimeVo) {
		OverTimeRequest overTimeRequest = populateOverTimeRequest(overTimeVo, new OverTimeRequest());
		OverTimeVo response = saveAndFetchResponse(overTimeVo, overTimeRequest);
		return response;
	}

	@Override
	public OverTimeVo editOverTime(PathiveduRequest<OverTimeVo> overTimeVo) {
		OverTimeRequest overTimeRequest = overTimeRequestRepository.findById(overTimeVo.getData().getOverTimeId())
				.get();
		OverTimeVo response = saveAndFetchResponse(overTimeVo, overTimeRequest);
		return response;
	}

	@Override
	public List<OverTimeVo> fetchOverTimesByUser(long userId) {
		List<OverTimeVo> response = new ArrayList<>();
		List<OverTimeRequest> levaesList = overTimeRequestRepository.retrievedByUserId(userId);
		levaesList.forEach(overTimeRequest -> {
			OverTimeVo overTimeVo = extractResponse(overTimeRequest);
			response.add(overTimeVo);
		});
		return response;
	}

	@Override
	public String deleteOverTime(PathiveduRequest<Long> request) {
		overTimeRequestRepository.deleteById(request.getData());
		return null;
	}

	private OverTimeRequest populateOverTimeRequest(PathiveduRequest<OverTimeVo> overTimeVo,
			OverTimeRequest overTimeRequest) {
		VoPopulator<OverTimeVo, OverTimeRequest> requestPopulator = new VoPopulator<>();

		overTimeRequest = requestPopulator.populateObject(overTimeVo.getData(), overTimeRequest);
		overTimeRequest.setLastUpdate(new Date());
		overTimeRequest.setStatus(RequestStatus.PENDING);
		if (overTimeVo.getData().getOverTimeStatus() != null) {
			overTimeRequest.setStatus(Enum.valueOf(RequestStatus.class, overTimeVo.getData().getOverTimeStatus()));
		}
		return overTimeRequest;
	}

	private OverTimeVo saveAndFetchResponse(PathiveduRequest<OverTimeVo> overTimeVo, OverTimeRequest overTimeRequest) {
		overTimeRequest.setAppliedOn(new Date());
		overTimeRequest = overTimeRequestRepository.save(overTimeRequest);

		populateAndAddOverTimeHistory(overTimeRequest, overTimeVo);

		OverTimeVo response = extractResponse(overTimeRequest);
		return response;
	}

	private void populateAndAddOverTimeHistory(OverTimeRequest overTimeRequest,
			PathiveduRequest<OverTimeVo> pathiveduReq) {
		OverTimeHistory overTimeHistory = null;
		List<OverTimeHistory> overTimeHistorys = overTimeHistoryRepository
				.fetchLastRecoredByOverTimeId(overTimeRequest.getId(), PageRequest.of(0, 1));
		if (overTimeHistorys != null && !overTimeHistorys.isEmpty())
			overTimeHistory = overTimeHistorys.get(0);
		if (overTimeHistory != null) {
			if (!overTimeHistory.getOverTimeDateAfter().equals(overTimeRequest.getOverTimeDate())) {
				overTimeHistory.setOverTimeDateBefore(overTimeHistory.getOverTimeDateAfter());
				overTimeHistory.setOverTimeDateAfter(overTimeRequest.getOverTimeDate());
			}
			if (overTimeHistory.getOverTimePeriodAfter() != overTimeRequest.getOverTimePeriod()) {
				overTimeHistory.setOverTimePeriodBefore(overTimeHistory.getOverTimePeriodAfter());
				overTimeHistory.setOverTimePeriodAfter(overTimeRequest.getOverTimePeriod());
			}

		} else {
			overTimeHistory = new OverTimeHistory();
			overTimeHistory.setOverTimeDateBefore(overTimeRequest.getOverTimeDate());
			overTimeHistory.setOverTimeDateAfter(overTimeRequest.getOverTimeDate());
			overTimeHistory.setOverTimePeriodBefore(overTimeRequest.getOverTimePeriod());
			overTimeHistory.setOverTimePeriodAfter(overTimeRequest.getOverTimePeriod());
		}
		overTimeHistory.setChangedBy(pathiveduReq.getRequestorId());
		overTimeHistory.setOverTimeId(overTimeRequest.getId());
		overTimeHistory.setChangesDone(overTimeRequest.getReason());
		overTimeHistory.setUpdatedOn(new Date());

		overTimeHistoryRepository.save(overTimeHistory);
	}

	private OverTimeVo extractResponse(OverTimeRequest overTimeRequest) {
		VoPopulator<OverTimeRequest, OverTimeVo> responsePopulator = new VoPopulator<>();

		OverTimeVo response = new OverTimeVo();
		response = responsePopulator.populateObject(overTimeRequest, response);
		response.setOverTimeId(overTimeRequest.getId());

		if (overTimeRequest.getStatus() != null) {
			response.setOverTimeStatus(overTimeRequest.getStatus().name());
		}
		return response;
	}

}
