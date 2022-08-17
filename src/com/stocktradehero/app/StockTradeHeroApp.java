package com.stocktradehero.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.stocktradehero.GameMarketController;

import java.util.Scanner;

public class StockTradeHeroApp implements SplashApp {

    Prompter prompter = new Prompter(new Scanner(System.in));

    @Override
    public void start() {
        System.out.println();
        System.out.println("booting up...");
        Console.pause(2000);
        Console.clear();
        GameMarketController market = new GameMarketController();
        market.initialize();
    }
}