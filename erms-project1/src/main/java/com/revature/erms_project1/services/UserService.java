package com.revature.erms_project1.services;

import com.revature.erms_project1.entities.User;
import com.revature.erms_project1.exceptions.PasswordFailedException;
import com.revature.erms_project1.exceptions.UserNotFoundException;
import com.revature.erms_project1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User person) {
        person = userRepository.save(person);
        System.out.println("Called register");
        return person;
    }

    public User login(String username, String password) throws PasswordFailedException{
        User personDB = this.userRepository.findByUsername(username);
        if(personDB.getPassword().equals(password)) return personDB;
        else throw new PasswordFailedException();
    }

//    public User getUserById(Long accountId) throws UserNotFoundException {
//        return userRepository.findById(accountId)
//                .orElseThrow(UserNotFoundException::new);
//    }

    public User getUserById(Integer userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
