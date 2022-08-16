package com.stocktradehero;

import com.stocktradehero.app.StockTradeHeroApp;
import com.stocktradehero.GameMarket;

import java.util.*;
import java.util.function.Predicate;

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
  private int stockShareBalance;  //delete after recode sell method
    private double stockAmountBalance=0.0;
    private double totalAmountBalance;
    private final Map<Stock, Double> StockMap = new HashMap<>();
    double sum = StockMap.values().stream().mapToDouble(d -> d).sum();
    private final List<Stock> playerStocks = new ArrayList<>();



    //constructors
    public Player(String name) {
        this.name = name;
    }

    //methods

    public void buyStock(int qty, Stock stockName) {
        if (stockName.getPrice() * qty > getCashBalance()) {
            System.out.println("Insufficient Balance. Your current cash balance is " + getCashBalance());
        } else {
            stockName.setShares(qty);
            playerStocks.add(stockName);
//            stockShareBalance += qty;
//            stockAmountBalance += qty * stockName.getPrice();
            //stockAmountBalanceList.add(stockAmountBalance);

//            List<Stock> stocks = new ArrayList<>();  //TODO: it is newly modified.
//            for(Stock stock : stocks){
//                stockAmountBalance += stockName.getPrice()*qty;
//            }
            cashBalance -= qty * stockName.getPrice();
            //totalAmountBalance = getCashBalance() + getStockAmountBalance(); //rewrite this in the getter
            System.out.println(getName() + " just bought " + qty + " shares of " + stockName.getTickerSymbol());


        }


    }

    /*
    private Map<Stock, Double> loadStockMap() {  //TODO: modifying it.
        Map<Stock,Double> stockMap = new HashMap<>();
        return stockMap;
    }


     */


    public void sellStock(int qty, Stock stockName) throws IllegalArgumentException {
        stockName=Objects.requireNonNull(getPlayerStocks().stream().filter(Predicate.isEqual(stockName.getTickerSymbol())).findFirst().orElse(getPlayerStocks().get(0)));
        if (qty <= stockName.getShares()) {
            stockName.setShares(stockName.getShares()-qty);
//            stockShareBalance -= qty;
//            stockAmountBalance -= qty * stockName.getPrice();
            cashBalance += qty * stockName.getPrice();
//            totalAmountBalance = getCashBalance() + getStockAmountBalance();
            System.out.println(getName() + " just sold " + qty + " shares of " + stockName.getTickerSymbol());
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

    public int getStockShareBalance() { //delete after recode sell method to include getPlayerStocks
        return stockShareBalance;
    }

    public List<Stock> getPlayerStocks() {
        return playerStocks;
    }

    public double getStockAmountBalance() {
        return stockAmountBalance;  //TODO
    }

    public double getTotalAmountBalance() {
        return totalAmountBalance;
    }

}