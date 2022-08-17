package com.stocktradehero;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class StatusBoardTest {

    @Test
    public void load_shouldReturnStocksList() throws IOException{
        StatusBoard statusBoardLoader = new StatusBoard("conf/StocksList.csv");
        List<Stock> stocks = statusBoardLoader.load();
//        assertEquals(20, stocks.size());

        Stock stock0 = stocks.get(19);
        assertEquals("Manure R Us", stock0.getCompanyName());
//        assertEquals("GRPF", stock0.getTickerSymbol());
//        assertEquals(java.util.Optional.ofNullable(Double.valueOf(150.00)), stock0.getPrice());
//        assertEquals(StockType.AGRICULTURE, stock0.getStockType());
    }
}