package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}