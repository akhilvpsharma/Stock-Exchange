package com.stock.exchange.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document
public class User {
    @Id
    private String id;
    @Indexed(unique=true, sparse=true)
    private String username;
    private String firstName;
    private String lastName;
    private String age;
    private Float balance;
    private HashMap<String, Integer> stocksHeld;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public HashMap<String, Integer> getStocksHeld() {
        return stocksHeld;
    }

    public void setStocksHeld(HashMap<String, Integer> stocksHeld) {
        this.stocksHeld = stocksHeld;
    }
}