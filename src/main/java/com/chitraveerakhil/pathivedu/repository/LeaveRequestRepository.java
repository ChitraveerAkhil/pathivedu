package com.chitraveerakhil.pathivedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chitraveerakhil.pathivedu.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

	@Query("FROM LeaveRequest WHERE USER_ID = :userid")
	List<LeaveRequest> retrievedByUserId(@Param("userid") long userId);

}
