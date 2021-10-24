package com.stock.exchange.model;

public class Order implements Comparable<Order>{
    private String stockSymbol;
    private Integer quantity;
    private Float price;
    private String username;

    public Order(String stockSymbol, Integer quantity, Float price, String username) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.username = username;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(Order o) {
        if(price>o.getPrice())
                return 1;
        else if (price<o.getPrice())
            return -1;
        else return 0;
    }
}
