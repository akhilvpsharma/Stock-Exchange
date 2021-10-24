package com.stock.exchange.model;

import java.util.HashMap;

public class User {
    private String username;
    private Float balance;
    private HashMap<String, Integer> stocksHeld;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
