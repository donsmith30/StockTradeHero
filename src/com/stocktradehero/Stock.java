package com.stocktradehero;

/*
 * Stock
 * attributes:
 * String companyName
 * String tickerSymbol
 * Enum   StockType
 * double price
 */
public class Stock {

    // Fields, or attributes, of a stock
    private String companyName;
    private String tickerSymbol;
    private double price;
    private final StockType stockType;

    public Stock(String companyName, String tickerSymbol, double price, StockType stockType) {
        this.companyName = companyName;
        this.tickerSymbol = tickerSymbol;
        this.price = price;
        this.stockType = stockType;
    }

    // Accessor methods
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStockVolatility() {
        return this.stockType.getVolatility();
    }

    public double getStockDividend() {
        return this.stockType.getStockDividend();
    }

}