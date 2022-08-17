package com.stocktradehero;

/*
 * StockType
 * attributes:
 * double volatility
 * double investmentReturn
 */

public enum StockType {

    TECH (0.05, 0.03),
    INDUSTRIAL(0.03, 0.025),
    AGRICULTURE(0.02, 0.02),
    PRECIOUS_METALS(0.01, 0.01);

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