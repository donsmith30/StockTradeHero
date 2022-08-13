package com.stocktradehero;

public class Game {

    private int turns = 5;


    public static void initialize(){
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        Player p3 = new Player("Player 3");
        System.out.println(p1.getName()+ " It is your turn...");

    }

    public void play() {  //thinking the order of play will be here

    }



}