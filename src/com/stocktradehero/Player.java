package com.stocktradehero;

import java.util.Map;
import java.util.TreeMap;
/*
 * Player
 * attributes:
 * String name (maybe leave out name input until end of game)
 * double cashBalance
 * double stockBalance
 * double totalBalance
 * Map<Stocks> stocks
 * behaviors:
 * buyStock()
 * sellStock()
 * printBalance()
 */

class Player {

    //attributes
    private String name;
    private double cashBalance = 1000.0;
    private double stockBalance = 0.0;
    private double totalBalance;
    private final Map<Stock, Integer> Stocks = new TreeMap<>();

    //constructors
    public Player(String name) {
        this.name = name;
    }

    //methods
    public void buyStock(){

    }

    public void sellStock(){

    }

    //getters & setters


    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public double getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(double stockBalance) {
        this.stockBalance = stockBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Map<Stock, Integer> getStocks() {
        return Stocks;
    }

    public String getName() {
        return name;
    }
}