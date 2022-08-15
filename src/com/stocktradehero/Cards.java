package com.stocktradehero;

/*
 * Cards will be a set of Market Forces
 * attributes:
 * chipShortage         -- multiply TECH by 0.8
 * politicalDilemma     -- multiply all stock types by 0.9
 * pandemic             -- multiply all stock types by 0.7
 * positiveEarnings     -- multiply stock by 1.2
 * negativeEarnings     -- multiply stock by 0.8
 * badCompanyBehavior   -- multiply stock by 0.8
 * goodCompanyBehavior  -- multiply stock by 1.2
 *
 * Chip shortage (negative for tech and industrial),
 * political situations, mergers and acquisitions, war, pandemic, interest rates, inflation,
 * food shortage (drought), climate emergency, oil shortage, bad company behavior, company earnings
 */

enum Cards {

    CHIP_SHORTAGE(.8,1,1,1, "There is a chip shortage. Tech stocks are down 20%"),
    FOOD_SHORTAGE(1, 1, 1, 0.8, "There is a food shortage. Agricultural stocks are down 10%"),
    POLITICAL_DILEMMA(0.9, 0.9, 0.9, 0.9, "There has been a political dilemma. The market is down 10%"),
    PANDEMIC(0.7, 0.7, 0.7, 0.7, "There is a new pandemic. The market is down 30%"),
    INFLATION_DOWN(1.1, 1.1, 1.1, 1.1, "Inflation is down. Market is up 10%"),
    INFLATION_UP(0.9, 0.9, 0.9, 0.9, "Inflation is up. Market is down 10%"),
    POSITIVE_EARNINGS(1, 1, 1, 1, "This stock has had a positive earnings report"), // Individual stock
    NEGATIVE_EARNINGS(1, 1, 1,1, "This stock has had a negative earnings report"), // Individual stock
    GOOD_BEHAVIOR(1, 1, 1, 1, "This company is in the news for improving the world"), // Individual stock
    BAD_BEHAVIOR(1, 1, 1, 1, "This company is in the news for some egregious executive behavior"); // Individual stock

    private double xTech;
    private double xPreciousMetals;
    private double xIndustrial;
    private double xAgriculture;
    private String cardText;

    Cards(double xTech, double xPreciousMetals, double xIndustrial, double xAgriculture, String cardText) {
        this.xTech = xTech;
        this.xPreciousMetals = xPreciousMetals;
        this.xIndustrial = xIndustrial;
        this.xAgriculture = xAgriculture;
        this.cardText = cardText;
    }
}