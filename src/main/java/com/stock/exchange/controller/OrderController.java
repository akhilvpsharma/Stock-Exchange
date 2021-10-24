package com.stock.exchange.controller;

import com.stock.exchange.model.Order;
import com.stock.exchange.service.OrderService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("buy")
    public Order buy(@RequestBody Order order, @RequestHeader("Authorization") String token) {
//        Allowed to buy?
        String username = Jwts.parser().setSigningKey("mySecretKey".getBytes()).parseClaimsJws(token.split(" ")[1]).getBody().getSubject();
        order.setUsername(username);
        orderService.buyOrder(order);
//        Order x1 = new Order("s", 10, 100f, username);
//        Order x2 = new Order("s", 20, 105f, username);
//        Order x3 = new Order("s", 15, 100f, username);
//        Order x4 = new Order("s", 20, 90f, username);
//        orderService.buyOrder(x1);
//        orderService.buyOrder(x2);
//        orderService.buyOrder(x3);
//        orderService.buyOrder(x4);
        return order;
    }

    @RequestMapping("sell")
    public Order sell(@RequestBody Order order, @RequestHeader("Authorization") String token) {
        //        Allowed to sell?
        String username = Jwts.parser().setSigningKey("mySecretKey".getBytes()).parseClaimsJws(token.split(" ")[1]).getBody().getSubject();
        order.setUsername(username);
        orderService.sellOrder(order);
        return order;
    }
}