package com.chitraveerakhil.pathivedu.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chitraveerakhil.pathivedu.model.OverTimeHistory;

public interface OverTimeHistoryRepository extends JpaRepository<OverTimeHistory, Long> {

	@Query("FROM OverTimeHistory WHERE OVER_TIME_ID = :overtimeid ORDER BY ID DESC")
	List<OverTimeHistory> fetchLastRecoredByOverTimeId(@Param(value = "overtimeid") long overTimeId, Pageable pageable);

}
