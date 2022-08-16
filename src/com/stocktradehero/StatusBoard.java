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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class StatusBoard extends Stock {

    private double playerNetWorth;
    private double stockPrice = getPrice();
    private final List<Player> winners = new ArrayList<>();


    public StatusBoard(String companyName, String tickerSymbol, double price, StockType stockType) {
        super(companyName, tickerSymbol, price, stockType);
    }

    public void showBoard() { // if game is started, and game is not over, show board

    }


    }
//    public double getStockPrice() {
//        System.out.println("The price of the stock is " + getStockPrice());
//    }
}