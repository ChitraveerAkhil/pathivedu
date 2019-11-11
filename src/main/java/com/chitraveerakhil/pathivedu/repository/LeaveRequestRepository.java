package com.chitraveerakhil.pathivedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chitraveerakhil.pathivedu.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

}
