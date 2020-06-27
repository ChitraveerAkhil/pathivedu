package com.chitraveerakhil.pathivedu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.chitraveerakhil.pathivedu.cache.service.impl.LeaveCacheService;
import com.chitraveerakhil.pathivedu.constants.UtilConstants;
import com.chitraveerakhil.pathivedu.helper.VoPopulator;
import com.chitraveerakhil.pathivedu.model.LeaveHistory;
import com.chitraveerakhil.pathivedu.model.LeaveRequest;
import com.chitraveerakhil.pathivedu.repository.LeaveHistoryRepository;
import com.chitraveerakhil.pathivedu.repository.LeaveRequestRepository;
import com.chitraveerakhil.pathivedu.service.LeaveRequestService;
import com.chitraveerakhil.pathivedu.vo.LeaveType;
import com.chitraveerakhil.pathivedu.vo.LeaveVo;
import com.chitraveerakhil.pathivedu.vo.PathiveduRequest;
import com.chitraveerakhil.pathivedu.vo.RequestStatus;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	LeaveRequestRepository leaveRequestRepository;

	@Autowired
	LeaveHistoryRepository leaveHistoryRepository;

	@Autowired
	@Qualifier("leaveCacheService")
	LeaveCacheService leaveCacheService;

	@Override
	public LeaveVo addLeave(PathiveduRequest<LeaveVo> leaveVo) {
		LeaveRequest leaveRequest = populateLeaveRequest(leaveVo, new LeaveRequest());
		LeaveVo response = saveAndFetchResponse(leaveVo, leaveRequest);
		return response;
	}

	@Override
	public LeaveVo editLeave(PathiveduRequest<LeaveVo> leaveVo) {
		LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveVo.getData().getLeaveId()).get();
		LeaveVo response = saveAndFetchResponse(leaveVo, leaveRequest);
		return response;
	}

	@Override
	public List<LeaveVo> fetchLeavesByUser(long userId) {
		List<LeaveVo> response = leaveCacheService.getListByUser(userId);
		if (response.isEmpty()) {
			List<LeaveRequest> leaveList = leaveRequestRepository.retrievedByUserId(userId);
			leaveList.forEach(leaveRequest -> {
				LeaveVo leaveVo = extractResponse(leaveRequest);
				response.add(leaveVo);
			});
			leaveCacheService.populateCacheByUser(response, userId);
		}
		return response;
	}

	@Override
	public String deleteLeave(PathiveduRequest<Long> request) {
		leaveRequestRepository.deleteById(request.getData());
		return UtilConstants.LEAVE_DELETED_RESPONSE;
	}

	private LeaveRequest populateLeaveRequest(PathiveduRequest<LeaveVo> leaveVo, LeaveRequest leaveRequest) {
		VoPopulator<LeaveVo, LeaveRequest> requestPopulator = new VoPopulator<>();

		leaveRequest = requestPopulator.populateObject(leaveVo.getData(), leaveRequest);
		leaveRequest.setLastUpdate(new Date());
		leaveRequest.setStatus(RequestStatus.PENDING);
		if (leaveVo.getData().getLeaveStatus() != null) {
			leaveRequest.setStatus(Enum.valueOf(RequestStatus.class, leaveVo.getData().getLeaveStatus()));
		}
		if (leaveVo.getData().getLeaveType() != null) {
			leaveRequest.setLeaveType(Enum.valueOf(LeaveType.class, leaveVo.getData().getLeaveStatus()));
		}
		return leaveRequest;
	}

	private LeaveVo saveAndFetchResponse(PathiveduRequest<LeaveVo> leaveVo, LeaveRequest leaveRequest) {
		leaveRequest.setAppliedOn(new Date());
		leaveRequest = leaveRequestRepository.save(leaveRequest);

		populateAndAddLeaveHistory(leaveRequest, leaveVo);

		LeaveVo response = extractResponse(leaveRequest);
		return response;
	}

	private void populateAndAddLeaveHistory(LeaveRequest leaveRequest, PathiveduRequest<LeaveVo> pathiveduReq) {
		LeaveHistory leaveHistory = null;
		List<LeaveHistory> leaveHistorys = leaveHistoryRepository.fetchLastRecoredByLeaveId(leaveRequest.getId(),
				PageRequest.of(0, 1));
		if (leaveHistorys != null && !leaveHistorys.isEmpty())
			leaveHistory = leaveHistorys.get(0);
		if (leaveHistory != null) {
			if (!leaveHistory.getFromDateAfter().equals(leaveRequest.getLeaveFrom())) {
				leaveHistory.setFromDateBefore(leaveHistory.getFromDateAfter());
				leaveHistory.setFromDateAfter(leaveRequest.getLeaveFrom());
			}
			if (!leaveHistory.getToDateAfter().equals(leaveRequest.getLeaveTo())) {
				leaveHistory.setToDateBefore(leaveRequest.getLeaveTo());
				leaveHistory.setToDateAfter(leaveRequest.getLeaveTo());
			}

		} else {
			leaveHistory = new LeaveHistory();
			leaveHistory.setFromDateAfter(leaveRequest.getLeaveFrom());
			leaveHistory.setToDateAfter(leaveRequest.getLeaveTo());
			leaveHistory.setFromDateBefore(leaveRequest.getLeaveFrom());
			leaveHistory.setToDateBefore(leaveRequest.getLeaveFrom());
		}
		leaveHistory.setChangedBy(pathiveduReq.getRequestorId());
		leaveHistory.setLeaveId(leaveRequest.getId());
		leaveHistory.setChangesDone(leaveRequest.getReason());
		leaveHistory.setUpdatedOn(new Date());

		leaveHistoryRepository.save(leaveHistory);
	}

	private LeaveVo extractResponse(LeaveRequest leaveRequest) {
		VoPopulator<LeaveRequest, LeaveVo> responsePopulator = new VoPopulator<>();

		LeaveVo response = new LeaveVo();
		response = responsePopulator.populateObject(leaveRequest, response);
		response.setLeaveId(leaveRequest.getId());

		if (leaveRequest.getStatus() != null) {
			response.setLeaveStatus(leaveRequest.getStatus().name());
		}

		if (leaveRequest.getLeaveType() != null) {
			response.setLeaveType(leaveRequest.getLeaveType().name());
		}
		return response;
	}

}
