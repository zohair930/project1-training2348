package com.revature.erms_project1.services;

import com.revature.erms_project1.entities.Account;
import com.revature.erms_project1.entities.User;
import com.revature.erms_project1.exceptions.AccountNotFoundException;
import com.revature.erms_project1.exceptions.UserNotFoundException;
import com.revature.erms_project1.repositories.AccountRepository;
import com.revature.erms_project1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account getAccountById(Long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }


    public Account createAccountForUser(int userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);


        if (user.getUserAccount() != null) {
            return user.getUserAccount();
        }

        Account account = new Account();
        account.setStatus("ACTIVE");
        account.setBalance(BigDecimal.ZERO);


        account.setUser(user);
        user.setUserAccount(account);

        return accountRepository.save(account);
    }
}
