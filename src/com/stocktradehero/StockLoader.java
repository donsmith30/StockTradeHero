package com.stocktradehero;

/*
 *
 * StockLoader will load a csv into our application.
 * We have a list of stocks that players will select from
 *
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class StockLoader {

    private final static String COMMA_DELIMITER = ",";
    private final Path dataFilepath;

    public StockLoader(String dataFilePath) {
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
