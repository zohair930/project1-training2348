package com.revature.erms_project1.services;


import com.revature.erms_project1.entities.Account;
import com.revature.erms_project1.exceptions.AccountNotFoundException;
import com.revature.erms_project1.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(Long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }
}
