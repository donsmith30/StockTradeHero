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

import java.util.*;

public class GameMarket {

    List<Stock> stocks = new ArrayList<>(List.of(
            new Stock("Grapefruit Inc","GRPF", 50., StockType.TECH),
            new Stock("Stock2","stk2", 50., StockType.PRECIOUS_METALS),
            new Stock("Stock3","stk3", 50., StockType.INDUSTRIAL),
            new Stock("Stock4","stk4", 50., StockType.AGRICULTURE)));
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    Player p3 = new Player("Player 3");
    private String qty;
    private String stockName;
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
            stockPrompt();
            qtyPrompt();
            Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
            currentPlayer.buyStock(Integer.parseInt(getQty()), s1);
            System.out.println("old stock price " + s1.getPrice());
            s1.setPrice(s1.getPrice() * (1 + s1.getStockVolatility()));
            System.out.println("New stock price " + s1.getPrice());
            currentPlayer.playerOption();
        }
        else if ( currentPlayer.getPlayerOption().equals("S")) {
            stockPrompt();
            qtyPrompt();
            Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
            currentPlayer.sellStock(Integer.parseInt(getQty()),s1);
            System.out.println("old stock price " + s1.getPrice());
            s1.setPrice(s1.getPrice() * s1.getStockVolatility());
            System.out.println("New stock price " + s1.getPrice());
            currentPlayer.playerOption();
        }
        else if (currentPlayer.getPlayerOption().equals("E")) { endTurn= true;
            System.out.println("Ending turn...");
        }
        }
    }

    public void stockPrompt() {
        setStockName(prompter.prompt("Enter the ticker symbol of the stock you want to purchase: ", "[A-Z]{4}",
                "please enter a valid stock"));
    }

    public void qtyPrompt() {
        setQty(prompter.prompt("Enter the ticker symbol of the stock you want to purchase: ", "[1-9]{2}",
                "please enter a valid stock"));
    }

    //get & set

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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