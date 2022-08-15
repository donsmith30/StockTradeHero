package com.stocktradehero;

import com.apps.util.Prompter;

import java.util.Map;
import java.util.Scanner;
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
    Prompter prompter = new Prompter(new Scanner(System.in));
    private String playerOption;
    private int stockShareBalance;
    private double stockAmountBalance;
    private double totalAmountBalance;


    //constructors
    public Player(String name) {
        this.name = name;
    }

    //methods
    public void playerOption() {
        setPlayerOption(prompter.prompt("Choose one of the following, [B]uy stocks, [S]ell stocks, or [E]nd turn :", "[A-Z]{1}",
                "you did not enter a correct response, must choose one of the following: [B], [S], or [E]."));
    }


    public void buyStock(int qty, Stock stockName) {
        if (stockName.getPrice() * qty > cashBalance) {
            System.out.println("Insufficient Balance. Your current cash balance is " + cashBalance);
        } else {
            stockShareBalance += qty;
            stockAmountBalance += qty * stockName.getPrice();
            cashBalance -= qty * stockName.getPrice();
            totalAmountBalance = cashBalance + stockAmountBalance;
            System.out.println(name + " just bought " + qty + stockName + " The cash balance is " + cashBalance +
                    ". Total stock share is " + stockShareBalance + ". Total balance is " + totalAmountBalance); //might need modified

        }
        System.out.println("buying stock");
    }

    public void sellStock(int qty, Stock stockName) throws IllegalArgumentException{

            if (qty<= stockShareBalance){
                stockShareBalance -=qty;
                stockAmountBalance -= qty*stockName.getPrice();
                cashBalance += qty* stockName.getPrice();
                totalAmountBalance =cashBalance +stockAmountBalance;
                System.out.println(name + " just sold "+qty +stockName);  //might need modified
            }
            else{
                System.out.println("Insufficient stock share balance. Your current stock share balance is " + stockShareBalance+
                        " Please try again! ");
        }



    }

    public void printBalance() {
        System.out.println(" The cash balance is " + cashBalance +
                ". Total stock share is " + stockShareBalance + ". Total balance is " + totalAmountBalance ); //might need modified



    }




    //getters & setters


    public String getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(String playerOption) {
        this.playerOption = playerOption;
    }

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