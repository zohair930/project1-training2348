package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}