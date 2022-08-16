package com.stocktradehero;

import com.apps.util.Prompter;
import com.stocktradehero.app.StockTradeHeroApp;

import java.util.*;

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

public class Player {

    //attributes
    private String name;
    private double cashBalance = 1000.0;
    //private double stockBalance = 0.0;
    //private double totalBalance =1000.0;
    private int stockShareBalance;
    private double stockAmountBalance=0.0;
    private double totalAmountBalance;
    //private final Map<Stock, Double> StocksAmountBalanceMap = new HashMap<>();
   // double sum = StocksAmountBalanceMap.values().stream().mapToDouble(d -> d).sum();


    //constructors
    public Player(String name) {
        this.name = name;
    }


//TODO: stock price up and down by buying and selling.

    //methods

    public void buyStock(int qty, Stock stockName) {
        if (stockName.getPrice() * qty > getCashBalance()) {
            System.out.println("Insufficient Balance. Your current cash balance is " + getCashBalance());
        } else {
            stockShareBalance += qty;
            //stockAmountBalance += qty * stockName.getPrice();
            //stockAmountBalanceList.add(stockAmountBalance);
            List<Stock> stocks = new ArrayList<>();  //TODO: it is newly modified.
            for(Stock stock : stocks){
                stockAmountBalance += stockName.getPrice()*qty;
            }
            cashBalance -= qty * stockName.getPrice();
            totalAmountBalance = getCashBalance() + getStockAmountBalance();
            System.out.println(getName() + " just bought " + qty + " shares of " + stockName);


        }




    }

    public void sellStock(int qty, Stock stockName) throws IllegalArgumentException {
        if (qty <= getStockShareBalance()) {
            stockShareBalance -= qty;
            stockAmountBalance -= qty * stockName.getPrice();
            cashBalance += qty * stockName.getPrice();
            totalAmountBalance = getCashBalance() + getStockAmountBalance();
            System.out.println(getName() + " just sold " + qty + " shares of " + stockName);
        } else {
            System.out.println("Insufficient stock shares. Your current stock share balance is " + getStockShareBalance() +
                    " Please try again! ");
        }
    }

    //Map<Stock, int> stockMap = new HashMap<>();

    //TODO: it is newly modified.
    public void printBalance() {
        System.out.printf("%s: name=%s, cashBalance=%s, stockBalance=%s,totalBalance=%s", getClass().getSimpleName(), getName(),
                getCashBalance(), getStockAmountBalance(), getTotalAmountBalance());
    }


    //getters & setters


    public String getName() {
        return name;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public int getStockShareBalance() {
        return stockShareBalance;
    }

    public double getStockAmountBalance() {
        return stockAmountBalance;
    }

    public double getTotalAmountBalance() {
        return totalAmountBalance;
    }

}