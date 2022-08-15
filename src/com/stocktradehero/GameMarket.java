package com.stocktradehero;
/*
 * Market
 * attributes:
 * String name
 * List<Player> players (HAS-A)
 * Stock                (HAS-A)
 * MarketForces         (HAS-A)
 * behaviors:
 * shuffle()   -- shuffle MarketForces
 * deal()      -- initial deal to set up game
 * giveStock() -- to Player
 */

import com.apps.util.Prompter;
import com.stocktradehero.app.StockTradeHeroApp;
import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameMarket {

    List<Stock> stocks = new ArrayList<>(List.of(
            new Stock("Grapefruit Inc","GRPF", 50., StockType.TECH),
            new Stock("Stock2","st2", 50., StockType.PRECIOUS_METALS),
            new Stock("Stock3","st3", 50., StockType.INDUSTRIAL),
            new Stock("Stock4","st4", 50., StockType.AGRICULTURE)));
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    Player p3 = new Player("Player 3");
    Stock s1 = new Stock("Grapefruit Inc","GRPF", 50., StockType.TECH);
    private int turns = 5;
    private int currentRound;
    private Player currentPlayer=p1;
    private boolean gameOver = false;
    private boolean endTurn = false;
    Prompter prompter = new Prompter(new Scanner(System.in));





    public static void initialize(){
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        Player p3 = new Player("Player 3");
        Stock s1 = new Stock("Grapefruit Inc","GRPF", 50., StockType.TECH);
        System.out.println(p1.getName()+ " It is your turn...");

    }

    public void play() { //This will be a condition for the entire game, takes turns and runs a for each loop to create a round...

    }

    public void round(){ //thinking the order of play will be here
         ;

    }

    public void turn(){
        boolean endTurn = false;
        currentPlayer.playerOption();
        while (!endTurn) {
        if (currentPlayer.getPlayerOption().equals("B")) {
            currentPlayer.buyStock();
            System.out.println(currentPlayer + " bought stock!");
            currentPlayer.playerOption();
            System.out.println("old stock price " + s1.getPrice());
            s1.setPrice(s1.getPrice() * s1.getStockVolatility());
            System.out.println("New stock price " + s1.getPrice());
        }
        else if ( currentPlayer.getPlayerOption().equals("S")) {
                currentPlayer.sellStock();
            System.out.println(currentPlayer + " sold stock!");
            currentPlayer.playerOption();
        }
        else if (currentPlayer.getPlayerOption().equals("E")) { endTurn= true;
            System.out.println("Ending turn...");
        }
        }
    }

    /*
    public void playerOption(){
        prompter.prompt("Choose one of the following, [B]uy stocks, [S]ell stocks, or [E]nd turn :", "[A-Z]{1}",
                "you did not enter a correct response, must choose one of the following: [B], [S], or [E]." );
    }

     */

    public static void main(String[] args) {
        GameMarket game = new GameMarket();
        game.turn();
    }
}