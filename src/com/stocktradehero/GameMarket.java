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

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import com.stocktradehero.Stock;

public class GameMarket {
    DecimalFormat df = new DecimalFormat("$" + "#.00");
    //    private List<Stock> stocks = new ArrayList<>(List.of( //stockloader.load
//            new Stock("Grapefruit Inc","GRPF", 50.00, StockType.TECH),
//            new Stock("Stock2","STWO", 50.00, StockType.PRECIOUS_METALS),
//            new Stock("Stock3","THRE", 50.00, StockType.INDUSTRIAL),
//            new Stock("Stock4","FOUR", 50.00, StockType.AGRICULTURE)));
    StatusBoard statusBoardLoader = new StatusBoard("conf/StocksList.csv");
    private List<Stock> stocks;
    {
        try {
            stocks = statusBoardLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Player> players = new ArrayList<>(List.of(
            new Player("Player 1"),
            new Player("Player 2"),
            new Player("Player 3")));

    private List<MarketEvent> cards = new ArrayList<>(List.of(MarketEvent.values()));
    private Map<String, Integer> buyTransactions = new HashMap<>();
    private Map<String, Integer> sellTransactions = new HashMap<>();
    private MarketEvent currentMarketForce;
    private String playerOption;
    private String qty;
    private String stockName;
    private int rounds = 5;
    private int currentRound;
    private Player currentPlayer;
    private boolean gameOver = false;
    private boolean endTurn = false;
    Prompter prompter = new Prompter(new Scanner(System.in));

    public void initialize() {
        //game set-up here
        playGame();
    }

    private void playGame() { //This will be a condition for the entire game, takes turns and runs a for each loop to create a round...
        for (int i = 0; i < rounds; i++) {
            System.out.println("Round: " + (i + 1));
            round();
        }
        for (Player player : players) {
            System.out.println(player.getTotalAmountBalance());
        }
    }

    private void round() { //thinking the order of play will be here
        //todo: add shuffle play card and do math on stock values
        marketForce();
        payDividends();
        for (Player roundPlayer : players
        ) {
            currentPlayer = roundPlayer;
            turn();
            Console.clear(); //ask Jay about console.clear?, idea on snake draft?
        }
    }

    private void payDividends() {
        for (Player player : players) {
            System.out.println(player.getName() + " -- Current cash balance: " + player.getCashBalance());
            if (player.getStockShareBalance() >= 1) {
                for (Stock stock : player.getPlayerStocks()) {
                    player.setCashBalance((stock.getPrice() * stock.getStockDividend()) * stock.getShares() + player.getCashBalance());
                }
                System.out.println(player.getName() + " -- Cash balance after dividend has been paid out: " + player.getCashBalance());
            }
        }

    }

    private void marketForce() {
        Collections.shuffle(cards);
        currentMarketForce = cards.get(0);
        System.out.println(currentMarketForce.toString() + "! " + currentMarketForce.getCardText());
        for (Stock item : stocks) {
            if (item.getStockType().equals(StockType.TECH)) {
                item.setPrice(Math.ceil(item.getPrice() * currentMarketForce.getxTech()));
            } else if (item.getStockType().equals(StockType.PRECIOUS_METALS)) {
                item.setPrice(Math.ceil(item.getPrice() * currentMarketForce.getxPreciousMetals()));
            }
            if (item.getStockType().equals(StockType.AGRICULTURE)) {
                item.setPrice(Math.ceil(item.getPrice() * currentMarketForce.getxAgriculture()));
            }
            if (item.getStockType().equals(StockType.INDUSTRIAL)) {
                item.setPrice(Math.ceil(item.getPrice() * currentMarketForce.getxIndustrial()));
            }
        }
    }

    //add blank lines "Console.blanklines(2)"
    //private everything inside

    private void turn() {  //need to find way to not change price if purchase or sale doesn't happen
        boolean endTurn = false;
        playerOption();
        while (!endTurn) {
            if (getPlayerOption().equals("B")) {
                System.out.println("S T O C K  P U R C H A S E");
                stockPrompt();
                qtyPrompt();
                Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                if (currentPlayer.getCashBalance() > s1.getPrice() * Integer.parseInt(getQty())) {
                    currentPlayer.buyStock(Integer.parseInt(getQty()), s1);
                    buyTransactions.put(s1.getTickerSymbol(), Integer.parseInt(getQty()));
//                System.out.println("old stock price " + df.format(s1.getPrice()));
//                s1.setPrice(Math.ceil(s1.getPrice() * (1 + s1.getStockVolatility())));
//                System.out.println("New stock price " + df.format(s1.getPrice()));
                } else {
                    System.out.println("Insufficient Balance. Your current cash balance is " + currentPlayer.getCashBalance());
                }
                playerOption();
            } else if (getPlayerOption().equals("S")) {
                System.out.println("S T O C K  S A L E");
                stockPrompt();
                qtyPrompt();
                Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                if (currentPlayer.getPlayerStocks().contains(s1)) {
                    currentPlayer.sellStock(Integer.parseInt(getQty()), s1);
                    sellTransactions.put(s1.getTickerSymbol(), Integer.parseInt(getQty()));
//            System.out.println("old stock price " + df.format(s1.getPrice()));
//            s1.setPrice(Math.ceil(s1.getPrice() * (1-s1.getStockVolatility())));
//            System.out.println("New stock price " + df.format(s1.getPrice()));
                } else {
                    System.out.println("Player does not have stock");
                }
                playerOption();
            } else if (getPlayerOption().equals("C")) {
                // System.out.println("Cash Balance: " + currentPlayer.getCashBalance());
                //System.out.println("PLAYER STOCK BALANCE");
                currentPlayer.printBalance();
                playerOption();
            } else if (getPlayerOption().equals("E")) {
                endTurn = true;
                System.out.println("Ending turn...");
                for (Map.Entry<String, Integer> entry : buyTransactions.entrySet()
                ) {
                    Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    Stock s2 = Objects.requireNonNull(currentPlayer.getPlayerStocks().stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    System.out.println(s1.getTickerSymbol() + "old stock price " + df.format(s1.getPrice()));
                    s1.setPrice(Math.ceil(s1.getPrice() * (1 + ((entry.getValue() * .1) / 3)) * (1 + s1.getStockVolatility())));
                    s2.setPrice(Math.ceil(s1.getPrice() * (1 + ((entry.getValue() * .1) / 3)) * (1 + s1.getStockVolatility())));
                    System.out.println(s1.getTickerSymbol() + "New stock price " + df.format(s1.getPrice()));

                    //currentPlayer.getStockAmountBalance();
                }
                for (Map.Entry<String, Integer> entry : sellTransactions.entrySet()
                ) {
                    Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    Stock s2 = Objects.requireNonNull(currentPlayer.getPlayerStocks().stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    System.out.println(s1.getTickerSymbol() + "old stock price " + df.format(s1.getPrice()));
                    s1.setPrice(Math.ceil(s1.getPrice() * (1 - ((entry.getValue() * .1) / 3)) * (1 - s1.getStockVolatility())));
                    s2.setPrice(Math.ceil(s1.getPrice() * (1 - ((entry.getValue() * .1) / 3)) * (1 - s1.getStockVolatility())));
                    System.out.println(s1.getTickerSymbol() + "New stock price " + df.format(s1.getPrice()));

                    //currentPlayer.getStockAmountBalance();
                }
                buyTransactions.clear();
                sellTransactions.clear();
            }
        }
    }

    private void stockPrompt() {
        setStockName(prompter.prompt("Enter the ticker symbol for the stock: ", "[A-Z]{1,4}",
                "please enter a valid stock"));
    }

    private void qtyPrompt() {
        setQty(prompter.prompt("You have $" + currentPlayer.getCashBalance() + ", Enter the amount of shares: ", "[0-9]{1,2}",
                "please enter a valid number"));
    }

    private void playerOption() {
        Console.blankLines(2);//todo: use these lines for board, replace with a board.show() method
        System.out.println("C U R R E N T  P R I C E S");
        for (Stock item : stocks) {
            System.out.println("Company: "+item.getCompanyName()+", Stock Ticker: " + item.getTickerSymbol() + ", Stock Price: " + df.format(item.getPrice()));
        }
        Console.blankLines(1);
        System.out.println("P l A Y E R  I N F O");
        currentPlayer.printBalance();
        Console.blankLines(1);//todo: board update to here
        setPlayerOption(prompter.prompt(currentPlayer.getName() + " Choose one of the following, [B]uy stocks, [S]ell stocks, [C]heck Balance or [E]nd turn:", "[A-Z]{1}",
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