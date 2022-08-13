package com.stocktradehero.app;

import com.apps.util.Prompter;
import com.apps.util.SplashApp;

import java.util.Scanner;

public class StockTradeHeroApp implements SplashApp {

    @Override
    public void start() {
        System.out.println();
        System.out.println("Start");
        promptForName();
    }

    @Override
    public void welcome(String... strings) throws IllegalArgumentException {
        SplashApp.super.welcome(strings);
    }

    @Override
    public void welcome(long l, String... strings) throws IllegalArgumentException {
        SplashApp.super.welcome(l, strings);
    }

    private String promptForName(){
        String name = null;
        Prompter prompter = new Prompter(new Scanner(System.in));
        prompter.prompt("Top score, Enter initials: ", "[A-Z]{3}","you did not enter 3 letters" );
        return name;
    }

}