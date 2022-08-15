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

public class Player {

    //attributes
    private String name;
    private double cashBalance = 1000.0;
    private double stockBalance = 0.0;
    private double totalBalance;
    private String playerOption;
    private int stockShareBalance;
    private double stockAmountBalance;
    private double totalAmountBalance;
    Prompter prompter = new Prompter(new Scanner(System.in));
    //private final Map<Stock, Integer> Stocks = new TreeMap<>();


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
        if (stockName.getPrice() * qty > getCashBalance()) {
            System.out.println("Insufficient Balance. Your current cash balance is " + getCashBalance());
        } else {
            stockShareBalance += qty;
            stockAmountBalance += qty * stockName.getPrice();
            cashBalance -= qty * stockName.getPrice();
            totalAmountBalance = getCashBalance() + getStockAmountBalance();
            System.out.println(getName() + " just bought " + qty + " shares of " + stockName); //might need modified

        }
    }

    public void sellStock(int qty, Stock stockName) throws IllegalArgumentException{
            if (qty<= getStockShareBalance()){
                stockShareBalance -=qty;
                stockAmountBalance -= qty*stockName.getPrice();
                cashBalance += qty* stockName.getPrice();
                totalAmountBalance =getCashBalance() +getStockAmountBalance();
                System.out.println(getName() + " just sold " + qty + " shares of " + stockName);
            }
            else{
                System.out.println("Insufficient stock shares. Your current stock share balance is " + getStockShareBalance()+
                        " Please try again! ");
        }
    }

    public void printBalance() {
        System.out.println(getName()+ "'cash balance is " + getCashBalance() +
                ". Total stock amount balance is " + getStockAmountBalance()
                + ". Total balance is " + getTotalAmountBalance() );
    }


    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCashBalance() {
        return cashBalance;
    }


    public double getStockBalance() {
        return stockBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public Prompter getPrompter() {
        return prompter;
    }

    public void setPrompter(Prompter prompter) {
        this.prompter = prompter;
    }

    public String getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(String playerOption) {
        this.playerOption = playerOption;
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