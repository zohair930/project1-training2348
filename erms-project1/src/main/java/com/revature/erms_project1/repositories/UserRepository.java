package com.revature.erms_project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.erms_project1.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}