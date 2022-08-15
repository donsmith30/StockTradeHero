package com.stocktradehero;

/*
 * Cards
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

    CHIPSHORTAGE(.8,1,1,1, "String here to read back to players"),
    POLITICALDILEMMA(0.9, 0.9, 0.9, 0.9, "There has been a political dilemma"),
    PANDEMIC(0.7, 0.7, 0.7, 0.7, "There is a pandemic"),
    POSITIVE_EARNINGS(1, 1, 1, 1, "This stock has had a positive earnings report"),
    NEGATIVE_EARNINGS(1, 1, 1,1, "This stock has had a negative earnings report"),
    GOOD_BEHAVIOR(1, 1, 1, 1, "This company is in the news for improving the world"),
    BAD_BEHAVIOR(1, 1, 1, 1, "This company is in the news for some egregious executive behavior");




    private double xTech;
    private double xPreciousMetals;
    private double xIndustrial;
    private double xAgriculture;
    private String cardText;

    Cards(double xTech) {
        this.xTech = xTech;
    }

    Cards(double xTech, double xPreciousMetals, double xIndustrial, double xAgriculture, String cardText) {
        this.xTech = xTech;
        this.xPreciousMetals = xPreciousMetals;
        this.xIndustrial = xIndustrial;
        this.xAgriculture = xAgriculture;
        this.cardText = cardText;
    }
}