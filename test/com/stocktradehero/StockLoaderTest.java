package com.stocktradehero;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class StockLoaderTest {

    @Test
    public void load_shouldReturnStocksList() throws IOException{
        StockLoader stockListLoader = new StockLoader("conf/StocksList.csv");
        List<Stock> stocks = stockListLoader.load();
        assertEquals(8, stocks.size());

        Stock stock0 = stocks.get(0);
        assertEquals("GRPF", stock0.getTickerSymbol());
        assertEquals(StockType.TECH, stock0.getStockType());
    }
}