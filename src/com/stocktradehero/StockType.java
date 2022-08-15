package com.stocktradehero;

/*
 * StockType
 * attributes:
 * double volatility
 * double investmentReturn
 */

enum StockType {

    TECH (0.5, 3.0),
    INDUSTRIAL(0.3, 2.0),
    AGRICULTURE(0.2, 1.8),
    PRECIOUS_METALS(0.1, 1.3);

    private final double volatility;
    private final double investmentReturn;


    StockType(double volatility, double investmentReturn) {
        this.volatility = volatility;
        this.investmentReturn = investmentReturn;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getInvestmentReturn() {
        return investmentReturn;
    }
}