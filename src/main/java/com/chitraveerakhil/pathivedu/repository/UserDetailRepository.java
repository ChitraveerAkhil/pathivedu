package com.chitraveerakhil.pathivedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chitraveerakhil.pathivedu.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

}
