package com.stocktradehero.app;

import com.apps.util.SplashApp;

public class StockTradeHeroApp implements SplashApp {

    @Override
    public void start() {
        System.out.println();
        System.out.println("Start");
    }

    @Override
    public void welcome(String... strings) throws IllegalArgumentException {
        SplashApp.super.welcome(strings);
    }

    @Override
    public void welcome(long l, String... strings) throws IllegalArgumentException {
        SplashApp.super.welcome(l, strings);
    }

}