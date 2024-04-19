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
    User addUser(@RequestBody User user){
       return userRepository.save(user);
    }
    @GetMapping("/getUsers")
    List<User> getUsers(){
       return userRepository.findAll();
    }
@GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
      return  userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
}
}
