package com.stocktradehero;

/*
 *
 * StockType
 * attributes:
 * double volatility - stock price will go up, or down, a certain amount with buying and selling, depending on stock type
 * double stockDividend - each stock type will have a dividend return in each round that incentivizes players to buy and hold
 *
 */

public enum StockType {

    TECH (0.5, 0.3),
    INDUSTRIAL(0.3, 0.25),
    AGRICULTURE(0.2, 0.2),
    PRECIOUS_METALS(0.1, 0.1);

    private final double volatility;
    private final double stockDividend;


    StockType(double volatility, double stockDividend) {
        this.volatility = volatility;
        this.stockDividend = stockDividend;
    }

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