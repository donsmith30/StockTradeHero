package com.stocktradehero.client;

import com.apps.util.client.SplashAppMain;
import com.stocktradehero.app.StockTradeHeroApp;


class Main {

    public static void main(String[] args) {
        StockTradeHeroApp app = new StockTradeHeroApp();
        app.welcome("images/officespace.jpeg");
        app.start();
    }
}