package com.stock.exchange.controller;


import com.stock.exchange.Repository.UserRepository;
import com.stock.exchange.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {
    @Autowired
    UserRepository userRepository;

    @PostMapping("create-user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
