package com.revature.erms_project1.repositories;

import com.revature.erms_project1.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository


public interface AccountRepository extends JpaRepository<Account, Long> {

}
