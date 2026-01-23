package com.revature.erms_project1.services;

import com.revature.erms_project1.entities.User;
import com.revature.erms_project1.exceptions.PasswordFailedException;
import com.revature.erms_project1.exceptions.UserNotFoundException;
import com.revature.erms_project1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User person) {
        person = userRepository.save(person);
        return person;
    }

    public User login(String username, String password) throws PasswordFailedException{
        User personDB = this.userRepository.findByUsername(username);
        if(personDB.getPassword().equals(password)) return personDB;
        else throw new PasswordFailedException();
    }

    public User getUserById(Integer userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
