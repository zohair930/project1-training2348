package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // find a person record by their name:
    public User findByName(String name);
}