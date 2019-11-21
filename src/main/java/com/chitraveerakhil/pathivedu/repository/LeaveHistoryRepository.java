package com.chitraveerakhil.pathivedu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chitraveerakhil.pathivedu.model.LeaveHistory;

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Long> {

	@Query("FROM LeaveHistory WHERE LEAVE_ID = :leaveid ORDER BY ID DESC")
	List<LeaveHistory> fetchLastRecoredByLeaveId(@Param(value = "leaveid") long leaveid, Pageable pageable);

}
