package com.stock.exchange.service;

import com.stock.exchange.Repository.UserRepository;
import com.stock.exchange.model.Order;
import com.stock.exchange.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;

    private PriorityQueue<Order> buyQueue = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Order> sellQueue = new PriorityQueue<>();

    public void buyOrder(Order order) {
        buyQueue.add(order);
        trade();
    }

    public void sellOrder(Order order) {
        sellQueue.add(order);
        trade();
    }

    private void trade() {
        if (buyQueue.size()==0 || sellQueue.size()==0) {
            System.out.println("Buy/Sell Orders Missing");
            return;
        }
        if(buyQueue.peek().getPrice()>=sellQueue.peek().getPrice()) {
            Order buyOrder = buyQueue.poll();
            Order sellOrder = sellQueue.poll();
            Integer quantity = 0;
            Float sellPrice = sellOrder.getPrice();
            if (buyOrder.getQuantity()>sellOrder.getQuantity()) {
                quantity = sellOrder.getQuantity();
                buyOrder.setQuantity(buyOrder.getQuantity()-quantity);
                buyQueue.add(buyOrder);
            }
            else if (buyOrder.getQuantity()<sellOrder.getQuantity()) {
                quantity = sellOrder.getQuantity();
                sellOrder.setQuantity(sellOrder.getQuantity()-quantity);
                sellQueue.add(sellOrder);
            }
            System.out.println(buyOrder.getUsername()+" credited "+quantity+" on price "+sellPrice);
            System.out.println(sellOrder.getUsername()+" debited "+quantity+" on price "+sellPrice);
            updateBuyer(buyOrder.getUsername(), buyOrder.getStockSymbol(), quantity, sellPrice);
//            updateSeller(sellOrder.getUsername(), buyOrder.getStockSymbol(), quantity, sellPrice);

//            Recursively call till no trades left
            trade();
        }
        else {
            System.out.println("No trades to execute");
        }
    }

    private void updateBuyer(String username, String stockSymbol, Integer quantity, Float sellPrice) {
        User buyer = userRepository.findByUsername(username);
        buyer.setBalance(buyer.getBalance()-(quantity*sellPrice));
        HashMap<String, Integer> stocksHeld = null;
        if(buyer.getStocksHeld()!=null)
            stocksHeld = buyer.getStocksHeld();
        else
            stocksHeld = new HashMap<>();
        if (stocksHeld.get(stockSymbol)!=null)
            stocksHeld.put(stockSymbol, stocksHeld.get(stockSymbol)+quantity);
        else
            stocksHeld.put(stockSymbol, quantity);
        buyer.setStocksHeld(stocksHeld);
        userRepository.save(buyer);
    }

    private void updateSeller(String username, String stockSymbol, Integer quantity, Float sellPrice) {
        User user = userRepository.findByUsername(username);
        user.setBalance(user.getBalance()-(quantity*sellPrice));
        HashMap<String, Integer> stocksHeld = null;
        if(user.getStocksHeld()!=null)
            stocksHeld = user.getStocksHeld();
        else
            stocksHeld = new HashMap<>();
//        assuming seller has the stock
        if (stocksHeld.get(stockSymbol)==quantity)
            stocksHeld.remove(stockSymbol);
        else
            stocksHeld.put(stockSymbol, stocksHeld.get(stockSymbol)-quantity);
        user.setStocksHeld(stocksHeld);
        userRepository.save(user);
    }
}