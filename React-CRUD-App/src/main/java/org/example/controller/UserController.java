package org.example.controller;

import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/getUsers")
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User NewUser, @PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            user.setName(NewUser.getName());
            user.setUserName(NewUser.getUserName());
            user.setEmail(NewUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User With id "+id+" has been deleted successfully!";
    }
}
