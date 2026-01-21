package com.revature.erms_project1.controllers;

import com.revature.erms_project1.dto.LoginDTO;
import com.revature.erms_project1.entities.User;
import com.revature.erms_project1.exceptions.PasswordFailedException;
import com.revature.erms_project1.exceptions.UserNotFoundException;
import com.revature.erms_project1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        user = this.userService.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // For login, we use a POST request, even though we aren't creating a User
    // Typically, a login operation would create a session to keep track of who is logged in
    // for our use case, we also need to pass the username/password through the body
    // because these are sensitive fields
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) throws PasswordFailedException {
        User person = this.userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long personId) {
        try{
            User user = this.userService.getUserById(personId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (UserNotFoundException e){

        }
        return null;
    }
}
