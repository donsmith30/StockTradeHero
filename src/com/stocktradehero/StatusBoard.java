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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StatusBoard {

    private double playerNetWorth;
//    private double stockPrice = getPrice();
//    private final List<Player> winners = new ArrayList<>();
//    public final List<Stock> stocksList = loadStocksList();
    private final static String COMMA_DELIMITER = ",";
    private final Path dataFilepath;

    public StatusBoard(String dataFilePath) {
        this.dataFilepath = Path.of(dataFilePath);
    }

    public List<Stock> load() throws IOException {
        List<Stock> stocksList = new ArrayList<>();

        Files.readAllLines(dataFilepath).forEach(line -> {
            String[] tokens = line.split(",");

            String companyName = tokens[0];
            String tickerSymbol = tokens [1];
            Double price = Double.valueOf(tokens[2]);
            StockType stockType = StockType.valueOf(tokens[3]);

            stocksList.add(new Stock(companyName, tickerSymbol, price, stockType));
        });
        return stocksList;
    }
}
