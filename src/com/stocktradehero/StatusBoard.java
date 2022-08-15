package com.stocktradehero;

/*
 * StatusBoard
 * attributes:
 * double playerNetWorth
 * double stockPrice
 * behavior...
 * ... EndGameBoard:
 * attributes:
 * List<Player> winners
 * behaviors:
 * getResult()
 * setName() --- if player score is top 10 all time
 */

import java.util.ArrayList;
import java.util.List;

class StatusBoard extends Stock{

    private double playerNetWorth;
    private double stockPrice = getPrice();
    private final List<Player> winners = new ArrayList<>();

    public StatusBoard(String companyName, String tickerSymbol, double price, StockType stockType) {
        super(companyName, tickerSymbol, price, stockType);
    }

//    public void getStockPrice() {
//        System.out.println("The price of the stock is " + getStockPrice());
//    }
}