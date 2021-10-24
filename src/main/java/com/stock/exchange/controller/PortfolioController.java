package com.stock.exchange.controller;

import com.stock.exchange.Repository.UserRepository;
import com.stock.exchange.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("portfolio")
    public User portfolio(@RequestHeader("Authorization") String token) {
        String username = Jwts.parser().setSigningKey("mySecretKey".getBytes()).parseClaimsJws(token.split(" ")[1]).getBody().getSubject();
        return userRepository.findByUsername(username);
    }
}