package com.stocktradehero.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.stocktradehero.Game;

import java.util.Scanner;

public class StockTradeHeroApp implements SplashApp {

    Prompter prompter = new Prompter(new Scanner(System.in));

    @Override
    public void start() {
        System.out.println();
        System.out.println("booting up...");
        Console.pause(250);
        Console.clear();
        Game.initialize();
    }

    @Override
    public void welcome(String... strings) throws IllegalArgumentException {
        SplashApp.super.welcome(strings);
    }

    @Override
    public void welcome(long l, String... strings) throws IllegalArgumentException {
        SplashApp.super.welcome(l, strings);
    }

    private String promptForName(){ ///might move this to the Status board...
        String name = null;
        prompter.prompt("Top score, Enter initials: ", "[A-Z]{3}","you did not enter 3 letters" );
        return name;
    }

}