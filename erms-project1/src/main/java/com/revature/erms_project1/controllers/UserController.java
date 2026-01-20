package com.revature.erms_project1.controllers;

import com.revature.erms_project1.dto.LoginDTO;
import com.revature.erms_project1.entities.User;
import com.revature.erms_project1.exceptions.PasswordFailedException;
import com.revature.erms_project1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    // According to REST rules, we pluralize collections of objects
    // We also ids to specify specific records
    // We typically don't include verbs in the path names because
    // the HTTP verb is usually indicative of what the operation is doing
    // localhost:8081/persons/1/pets/1
//    @PutMapping("/persons/{personId}/pets/{petId}")
//    public ResponseEntity<User> adopt(@PathVariable("personId") Long personId, @PathVariable("petId") Long petId) throws AccountNotFoundException, UserNotFoundException {
//        User person = this.userService.adopt(personId, petId);
//        return new ResponseEntity<>(person, HttpStatus.OK);
//    }
//
//    // This method takes a personId from the path and then returns all pets that were
//    // adopted by that person
//    @GetMapping("/persons/{personId}")
//    public ResponseEntity<List<Pet>> getAdopted(@PathVariable("personId") Long personId) {
//        List<Pet> pets = this.userService.getAdoptedPets(personId);
//        return new ResponseEntity<>(pets, HttpStatus.OK);
//    }
}
