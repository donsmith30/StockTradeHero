package com.stocktradehero;

/*
 * StockType
 * attributes:
 * double volatility
 * double investmentReturn
 */

enum StockType {

    TECH (0.5, 0.03),
    INDUSTRIAL(0.3, 0.025),
    AGRICULTURE(0.2, 0.02),
    PRECIOUS_METALS(0.1, 0.01);

    private final double volatility;
    private final double stockDividend;


    StockType(double volatility, double investmentReturn) {
        this.volatility = volatility;
        this.stockDividend = investmentReturn;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getInvestmentReturn() {
        return stockDividend;
    }
}