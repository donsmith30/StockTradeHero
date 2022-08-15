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

public class GameMarket {

    private int turns = 5;


    public static void initialize(){
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        Player p3 = new Player("Player 3");
        System.out.println(p1.getName()+ " It is your turn...");

    }

    public void play() { //This will be a condition for the entire game, takes turns and runs a for each loop to create a round...

    }

    public void round(){ //thinking the order of play will be here

    }
}