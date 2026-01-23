package com.revature.erms_project1.controllers;


import com.revature.erms_project1.entities.Account;
import com.revature.erms_project1.exceptions.AccountNotFoundException;
import com.revature.erms_project1.exceptions.UserNotFoundException;
import com.revature.erms_project1.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) throws AccountNotFoundException {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() throws AccountNotFoundException {
        List<Account> account = accountService.getAllAccounts();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}