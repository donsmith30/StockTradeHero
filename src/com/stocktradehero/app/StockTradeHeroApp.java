package com.stocktradehero.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.stocktradehero.GameMarket;

import java.util.Scanner;

public class StockTradeHeroApp implements SplashApp {

    Prompter prompter = new Prompter(new Scanner(System.in));

    @Override
    public void start() {
        System.out.println();
        System.out.println("booting up...");
        promptForName();
        Console.pause(250);
        Console.clear();
        GameMarket market = new GameMarket();
        market.initialize();
    }

    private String promptForName(){ ///might move this to the Status board...
        return prompter.prompt("Top score, Enter initials: ", "[A-Z]{3}","you did not enter 3 letters" );
    }

}