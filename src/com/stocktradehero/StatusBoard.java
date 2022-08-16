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
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

class StatusBoard extends Stock {

    private double playerNetWorth;
    private double stockPrice = getPrice();
    private final List<Player> winners = new ArrayList<>();

    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws IOException {
        List<List<String>> result = Files.readAllLines(Paths.get("conf/Stocks.csv"))
                .stream()
                .map(line -> Arrays.asList(line.split(COMMA_DELIMITER)))
                .collect(Collectors.toList());
        System.out.println(result);
    }


    public StatusBoard(String companyName, String tickerSymbol, double price, StockType stockType) {
        super(companyName, tickerSymbol, price, stockType);
    }

}
