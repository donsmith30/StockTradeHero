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

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.*;

public class GameMarket {

    List<Stock> stocks = new ArrayList<>(List.of(
            new Stock("Grapefruit Inc","GRPF", 50., StockType.TECH),
            new Stock("Stock2","STWO", 50., StockType.PRECIOUS_METALS),
            new Stock("Stock3","THRE", 50., StockType.INDUSTRIAL),
            new Stock("Stock4","FOUR", 50., StockType.AGRICULTURE)));
    List<Player> players = new ArrayList<>(List.of(
            new Player("Player 1"),
            new Player("Player 2"),
            new Player("Player 3")));
    List<Cards> cards = new ArrayList<>(List.of(Cards.values()));
    private Cards currentMarketForce;
    private String playerOption;
    private String qty;
    private String stockName;
    private int rounds = 5;
    private int currentRound;
    private Player currentPlayer;
    private boolean gameOver = false;
    private boolean endTurn = false;
    Prompter prompter = new Prompter(new Scanner(System.in));

    public static void initialize(){
        //game set-up here
    }

    public void playGame() { //This will be a condition for the entire game, takes turns and runs a for each loop to create a round...
        for (int i = 0; i < rounds; i++) {
            System.out.println("Round: " + (i + 1));
            round();
        }
        for (Player player : players) { //maybe use on board?
            System.out.println(player.getTotalAmountBalance());
        }
    }

    public void round(){ //thinking the order of play will be here
        //todo: add shuffle play card and do math on stock values
        //add dividends
        marketForce();
        for (Player roundPlayer: players
             ) {currentPlayer = roundPlayer;
            turn();
            Console.clear(); //ask Jay about console.clear?, idea on snake draft?
        }
    }

    public void marketForce() {
        Collections.shuffle(cards);
        currentMarketForce=cards.get(0);
        System.out.println(currentMarketForce.toString());
       for (Stock item : stocks) {
           if (item.getStockType=StockType.TECH) {//need getter for stock type
               item.setPrice(item.getPrice()*currentMarketForce.getXTech);
           }
      }
    }

    public void turn(){  //need to find way to not change price if purchase or sale doesn't happen
        boolean endTurn = false;
        playerOption();
        while (!endTurn) {
        if (getPlayerOption().equals("B")) {
            stockPrompt();
            qtyPrompt();
            Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
            currentPlayer.buyStock(Integer.parseInt(getQty()), s1);
            System.out.println("old stock price " + s1.getPrice());
            s1.setPrice(s1.getPrice() * (1 + s1.getStockVolatility()));
            System.out.println("New stock price " + s1.getPrice());
            playerOption();
        }
        else if ( getPlayerOption().equals("S")) {
            stockPrompt();
            qtyPrompt();
            Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
            currentPlayer.sellStock(Integer.parseInt(getQty()),s1);
            System.out.println("old stock price " + s1.getPrice());
            s1.setPrice(s1.getPrice() * (1-s1.getStockVolatility()));
            System.out.println("New stock price " + s1.getPrice());
            playerOption();
        }
        else if (getPlayerOption().equals("C")) {
            System.out.println("Cash Balance: " + currentPlayer.getCashBalance());
            System.out.println("Stock Balance: "+ currentPlayer.getStockShareBalance());
            playerOption();
        }
        else if (getPlayerOption().equals("E")) { endTurn= true;
            System.out.println("Ending turn...");
        }
        }
    }

    public void stockPrompt() {
        for (Stock item : stocks) { //maybe use on board?
            System.out.println("Stock Ticker: "+ item.getTickerSymbol()+ ",Stock Price: " + item.getPrice() );}
        setStockName(prompter.prompt("Stocks for sale: " + "Enter the ticker symbol for the stock: ", "[A-Z]{4}",
                "please enter a valid stock"));
    }

    public void qtyPrompt() {
        setQty(prompter.prompt("You have $"+currentPlayer.getCashBalance()+", Enter the amount: ", "[0-9]{1,2}",
                "please enter a valid number"));
    }

    public void playerOption() {
        setPlayerOption(prompter.prompt(currentPlayer.getName()+" Choose one of the following, [B]uy stocks, [S]ell stocks, [C]heck Balance or [E]nd turn :", "[A-Z]{1}",
                "you did not enter a correct response, must choose one of the following: [B], [S], [C] or [E]."));
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
    public String getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(String playerOption) {
        this.playerOption = playerOption;
    }

    //main test

    public static void main(String[] args) {
        GameMarket game = new GameMarket();
        game.playGame();
    }
}