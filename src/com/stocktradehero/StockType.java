package com.stocktradehero;


public enum StockType {

    TECH (0.5, 0.3),
    INDUSTRIAL(0.3, 0.25),
    AGRICULTURE(0.2, 0.2),
    PRECIOUS_METALS(0.1, 0.1);

    //fields
    private final double volatility;
    private final double stockDividend;

    //ctor
    StockType(double volatility, double stockDividend) {
        this.volatility = volatility;
        this.stockDividend = stockDividend;
    }
    //getters & setters
    public double getVolatility() {
        return volatility;
    }

    public double getInvestmentReturn() {
        return stockDividend;
    }

    public double getStockDividend() {
        return stockDividend;
    }
}