package com.stock.exchange.service;

import com.stock.exchange.model.Order;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.PriorityQueue;

@Service
public class OrderService {

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
//                TODO: credit buyer
//                TODO: debit seller
//            Recursively call till, trades end
            trade();
        }
        else {
            System.out.println("No trades to execute");
        }
    }
}