package com.revature.services;

import com.revature.entities.User;
import com.revature.exceptions.PasswordFailedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private PetRepository petRepository;

    public User register(User person) {
        person = userRepository.save(person);
        return person;
    }

    public User login(String username, String password) throws PasswordFailedException{
        User personDB = this.userRepository.findByUsername(username);
        if(personDB.getPassword().equals(password)) return personDB;
        else throw new PasswordFailedException();
    }
//
//    public User adopt(Long personId, Long petId) throws PetNotFoundException, UserNotFoundException {
//        Optional<Pet> petOptional = petRepository.findById(petId);
//        if(!petOptional.isPresent()) throw new PetNotFoundException();
//
//        Optional<User> personOptional = userRepository.findById(personId);
//        if(!personOptional.isPresent()) throw new UserNotFoundException();
//
//        // If we update the person entity and persist those changes back to the db,
//        // we'll see that the pet table will be updated to reflect this adoption
//        User person = personOptional.get();
//        Pet pet = petOptional.get();
//
//        // add the pet to the person's adoptedPets list
//        person.getAdoptedPets().add(pet);
//        System.out.println(person);
//
//        // persist these changes and return the person
//        person = userRepository.save(person);
//        return person;
//    }
//
//    public List<Pet> getAdoptedPets(Long personId) {
//        return this.petRepository.getAdoptedPets(personId);
//    }




}
