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

    CHIPSHORTAGE(.8,1,1,1, "String here to read back to players");

    private double xTech;
    private double xPreciousMetals;
    private double xIdustrial;
    private double xAgriculture;
    private String cardText;

    Cards(double xTech, double xPreciousMetals, double xIdustrial, double xAgriculture, String cardText) {
        this.xTech = xTech;
        this.xPreciousMetals = xPreciousMetals;
        this.xIdustrial = xIdustrial;
        this.xAgriculture = xAgriculture;
        this.cardText = cardText;
    }
}