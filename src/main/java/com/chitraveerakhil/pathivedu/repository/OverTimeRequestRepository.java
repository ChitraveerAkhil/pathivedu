package com.chitraveerakhil.pathivedu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chitraveerakhil.pathivedu.model.OverTimeRequest;

public interface OverTimeRequestRepository extends JpaRepository<OverTimeRequest, Long> {

	@Query("FROM OverTimeRequest WHERE USER_ID = :userid")
	List<OverTimeRequest> retrievedByUserId(@Param("userid") long userId);

}
