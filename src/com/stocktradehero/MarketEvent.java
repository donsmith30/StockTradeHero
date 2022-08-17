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

enum MarketEvent { //consider MarketEvent

    CHIP_SHORTAGE(.7,1.0,1.0,1.0, "There is a chip shortage. Tech stocks are down 30%"),
    METAVERSE_EXPANDING(1.3, 1.0, 1.0, 1.0, "Investments in the Metaverse have increased tenfold. Tech stocks up 30%."),
    ENVIRONMENTAL_SANCTIONS(1.0, 0.7, 1.0, 1.0, "New worldwide sanctions on mining operations. Precious Metal stocks are down 30%"),
    GOLD_STANDARD(1.0, 1.3, 1.0, 1.0, "The government has moved back to a Gold Standard for currency. Precious Metals are up 30%"),
    CONSTRUCTION_MATERIAL_SHORTAGE(1.0, 1.0, 0.7, 1.0, "Global shortages in lumber and other raw construction material. Industrial stocks are down 30%"),
    CONSTRUCTION_MATERIAL_SURPLUS(1.0, 1.0, 1.3, 1.0, "There is a global surplus of lumber and industrial supplies, and demand is high. Industrial stocks are up 30%"),
    FOOD_SHORTAGE(1.0, 1.0, 1.0, 0.7, "There is a food shortage. Agricultural stocks are down 30%"),
    HEALTHY_CLIMATE(1.0, 1.0, 1.0, 1.3, "Climate change is reversing. There are no droughts. Agriculture stocks are booming, and are up 30%"),
    POLITICAL_DILEMMA(0.9, 0.9, 0.9, 0.9, "There has been a political dilemma. The market is down 10%"),
    PANDEMIC(0.7, 0.7, 0.7, 0.7, "There is a new pandemic. The market is down 30%"),
    INFLATION_DOWN(1.1, 1.1, 1.1, 1.1, "Inflation is down. Market is up 10%"),
    INFLATION_UP(0.9, 0.9, 0.9, 0.9, "Inflation is up. Market is down 10%"),
    STIMULUS_PACKAGE(1.2, 1.2, 1.2, 1.2, "The government has signed a new stimulus bill. Investors are elated. The market is up 20%."),
    INTEREST_RATES_LOWER(1.15, 1.15, 1.15, 1.15, "The Fed has lowered interest rates. Economic activity is bustling. Market is up 15%"),
    POSITIVE_EARNINGS(1.3, 1.3, 1.3, 1.3, "Economy is thriving. Revenues are up for all stocks. The market is up 30%"),
    NEGATIVE_EARNINGS(.6, .6, .6,.6, "Economy is dragging. Revenues are down for all stocks. The market is down 40%");

    private double xTech;
    private double xPreciousMetals;
    private double xIndustrial;
    private double xAgriculture;
    private String cardText;

    MarketEvent(double xTech, double xPreciousMetals, double xIndustrial, double xAgriculture, String cardText) {
        this.xTech = xTech;
        this.xPreciousMetals = xPreciousMetals;
        this.xIndustrial = xIndustrial;
        this.xAgriculture = xAgriculture;
        this.cardText = cardText;
    }

    public double getxTech() {
        return xTech;
    }

    public void setxTech(double xTech) {
        this.xTech = xTech;
    }

    public double getxPreciousMetals() {
        return xPreciousMetals;
    }

    public void setxPreciousMetals(double xPreciousMetals) {
        this.xPreciousMetals = xPreciousMetals;
    }

    public double getxIndustrial() {
        return xIndustrial;
    }

    public void setxIndustrial(double xIndustrial) {
        this.xIndustrial = xIndustrial;
    }

    public double getxAgriculture() {
        return xAgriculture;
    }

    public void setxAgriculture(double xAgriculture) {
        this.xAgriculture = xAgriculture;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }
}