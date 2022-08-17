package com.stocktradehero;

import com.stocktradehero.app.StockTradeHeroApp;
import com.stocktradehero.GameMarket;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;

/*
 * Player
 * attributes:
 * String name (maybe leave out name input until end of game)
 * double cashBalance
 * double stockBalance
 * double totalBalance
 * behaviors:
 * buyStock()
 * sellStock()
 * printBalance()
 */

public class Player {

    //attributes
    private String name;
    private double cashBalance = 1000.0;
    private double totalAmountBalance;
    private final List<Stock> playerStocks = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("$" + "#.00");

    //constructors
    public Player(String name) {
        this.name = name;
    }

    //methods
    public void buyStock(int qty, Stock stockName) {
        if (stockName.getPrice() * qty > getCashBalance()) {
            System.out.println("Insufficient Balance. Your current cash balance is:  " + df.format(getCashBalance()));
        }
        else if (!playerStocks.contains(stockName)){
            playerStocks.add(stockName);
            stockName.setShares(qty);
            cashBalance -= qty * stockName.getPrice();
            System.out.println(getName() + " just bought " + qty + " shares of " + stockName.getTickerSymbol());
        }
        else{

            Stock s1 = getPlayerStocks().stream().filter(stock -> stockName.equals(stock.getTickerSymbol())).findFirst().orElse(getPlayerStocks().get(0));
            int newShares = s1.getShares()+qty;
            playerStocks.remove(s1);
            stockName.setShares(newShares);
            playerStocks.add(stockName);
            cashBalance -= qty * stockName.getPrice();
            System.out.println(getName() + " just bought " + qty + " shares of " + stockName.getTickerSymbol());
        }
    }

    public void sellStock(int qty, Stock stockName) throws IllegalArgumentException {
        if (qty <= stockName.getShares()) {
            stockName.setShares(stockName.getShares() - qty);
            cashBalance += qty * stockName.getPrice();
            System.out.println(getName() + " just sold " + qty + " shares of " + stockName.getTickerSymbol());
        } else {
            System.out.println("Insufficient stock shares. Your current stock share balance is " + stockName.getShares() +
                    " Please try again! ");
        }
    }

    public void printBalance() {
        System.out.println("PLAYER NET WORTH");
        System.out.printf("%s: name= %s, cashBalance= %s, stockBalance= %s,totalBalance= %s \n", getClass().getSimpleName(), getName(),
                df.format(getCashBalance()), df.format(getStockAmountBalance()), df.format(getTotalAmountBalance()));
        System.out.println("PLAYER STOCK BALANCE");
        for (Stock item : getPlayerStocks()) {
            System.out.println("Stock Ticker: " + item.getTickerSymbol() + ", owned shares: " + item.getShares());
        }
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public List<Stock> getPlayerStocks() {
        return playerStocks;
    }

    public double getStockAmountBalance() {
        double result = 0.0;
        for (Stock stock: playerStocks){
            result = result + (stock.getPrice()*stock.getShares());
        }
        return result;
    }

    public double getTotalAmountBalance() {
        double result = 0.0;
        result=getStockAmountBalance()+getCashBalance();
        return result;
    }


}