package com.chitraveerakhil.pathivedu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chitraveerakhil.pathivedu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
