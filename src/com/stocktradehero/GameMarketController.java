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

public class GameMarketController {
    private DecimalFormat df = new DecimalFormat("$" + "#.00");
    private StockLoader stockListLoader = new StockLoader("conf/StocksList.csv");
    private List<Stock> stocks;
    {
        try {
            stocks = stockListLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Player> players = new ArrayList<>();
    private List<MarketEvent> cards = new ArrayList<>(List.of(MarketEvent.values()));
    private Map<String, Integer> buyTransactions = new HashMap<>();
    private Map<String, Integer> sellTransactions = new HashMap<>();
    private MarketEvent currentMarketForce;
    private String playerOption;
    private String qty;
    private String stockName;
    private String rounds = "5";
    private String playerCount = "3";
    private int currentRound;
    private Player currentPlayer;
    private boolean gameOver = false;
    private boolean endTurn = false;
    Prompter prompter = new Prompter(new Scanner(System.in));
    TopScores topScores = TopScores.getInstance();
    private String winnerNames;

    public void initialize() {
        //game set-up here
        Console.blankLines(1);
        System.out.println("---W E L C O M E  T O  S T O C K  T R A D E  H E R O !---\n" +
                "A console based stock market simulator where you can play alone\n" +
                "or with 3 of your coolest friends, gathered around one monitor for keyboard co-op!");
        Console.blankLines(1);
        setPlayers();
        roundsPrompt();
        System.out.println("You choose to have a "+getPlayerCount()+" player game of "+getRounds()+" rounds, good luck!");
        playGame();
    }

    private void setPlayers() {
        playersPrompt();
        for (int i = 0; i < Integer.parseInt(getPlayerCount()); i++) {
            players.add(new Player("Player" + (i + 1)));
        }

    }

    private void playGame() { //This will be a condition for the entire game, takes turns and runs a for each loop to create a round...
        for (int i = 0; i < Integer.parseInt(getRounds()); i++) {
            System.out.println("Round: " + (i + 1));
            round();
        }
        finalStandings();
        topScores.toptenwinners.sort((p1,p2) -> Double.compare(p1.getTotalAmountBalance(),p2.getTotalAmountBalance()));
        Collections.reverse(topScores.toptenwinners);
        for (Player player : players) {

            if (topScores.toptenwinners.size() < 10) {
                System.out.print(player.getName()+" ");
                namesPrompt();
                player.setName(getWinnerNames());
                topScores.toptenwinners.add(player);
                //topScores.update(player);
            } else if (topScores.toptenwinners.get(9).getTotalAmountBalance() < player.getTotalAmountBalance()) {
                System.out.print(player.getName());
                namesPrompt();
                player.setName(getWinnerNames());
                //topScores.update(player);
                System.out.println(player.getName()+" ");
                topScores.toptenwinners.remove(9);
                topScores.toptenwinners.add(player);
            } else {

            }
        }
        topScores.toptenwinners.sort((p1,p2) -> Double.compare(p1.getTotalAmountBalance(),p2.getTotalAmountBalance()));
        Collections.reverse(topScores.toptenwinners);
        topScores.save();
        topScores.show();
    }


    private void finalStandings() {
        players.sort((p1, p2) -> Double.compare(p1.getTotalAmountBalance(), p2.getTotalAmountBalance()));
        Collections.reverse(players);
        System.out.println("---F I N A L  S C O R E S---");
        for (Player player : players) {
            player.printStandings();
        }
    }

    private void round() { //thinking the order of play will be here
        //todo: add shuffle play card and do math on stock values
        marketForce();
        payDividends();
        System.out.println("Markets are opening soon...");
        System.out.println("...");
        Console.pause(3000);
        Console.clear();
        for (Player roundPlayer : players
        ) {
            currentPlayer = roundPlayer;
            turn();
            Console.pause(3000);
            Console.clear();
        }
    }

    private void payDividends() {
        for (Player player : players) {
            double oldCashBalance = player.getCashBalance();
            System.out.println(player.getName() + " -- Current cash balance: " + df.format(player.getCashBalance()));
            if (player.getStockAmountBalance() >= 1) {
                for (Stock stock : player.getPlayerStocks()) {
                    player.setCashBalance((stock.getPrice() * stock.getStockDividend()) * stock.getShares() + player.getCashBalance());
                }
                System.out.println(player.getName() + " -- Dividend paid out:  " + (df.format(player.getCashBalance() - oldCashBalance)));
                System.out.println(player.getName() + " -- Cash balance after dividend has been paid out: " + df.format(player.getCashBalance()));
            }
        }
        Console.pause(3000);
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
        Console.pause(1000);
    }

    private void turn() {
        boolean endTurn = false;
        playerOption();
        while (!endTurn) {
            if (getPlayerOption().equals("B")) {
                System.out.println("S T O C K  P U R C H A S E");
                stockPrompt();
                Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                System.out.println(s1.getTickerSymbol()+"("+df.format(s1.getPrice())+"/per stock)");
                qtyPrompt();
                if (currentPlayer.getCashBalance() > s1.getPrice() * Integer.parseInt(getQty())) {
                    currentPlayer.buyStock(Integer.parseInt(getQty()), s1);
                    buyTransactions.put(s1.getTickerSymbol(), Integer.parseInt(getQty()));
                    Console.pause(2000);
                } else {
                    System.out.println("Insufficient Balance. Your current cash balance is " + df.format(currentPlayer.getCashBalance()));
                }
                playerOption();
            } else if (getPlayerOption().equals("S")) {
                System.out.println("S T O C K  S A L E");
                stockPrompt();
                qtyPrompt();
                Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                Stock s2 = (currentPlayer.getPlayerStocks().stream().filter(stock -> getStockName().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                if (Integer.parseInt(getQty()) <= s2.getShares()) {
                    currentPlayer.sellStock(Integer.parseInt(getQty()), s1);
                    sellTransactions.put(s1.getTickerSymbol(), Integer.parseInt(getQty()));
                    Console.pause(2000);
                } else {
                    System.out.println("Insufficient stock shares. Your current stock share balance is " + s2.getShares() +
                            " Please try again!");
                }
                playerOption();
            } else if (getPlayerOption().equals("C")) {
                currentPlayer.printBalance();
                playerOption();
            } else if (getPlayerOption().equals("R")) {
                currentPlayer.setCashBalance(currentPlayer.getCashBalance()+1000);
                System.out.println("The code [R] has been used + $1000");
                Console.clear();
                playerOption();
            } else if (getPlayerOption().equals("E")) {
                endTurn = true;
                System.out.println("Ending turn...");
                for (Map.Entry<String, Integer> entry : buyTransactions.entrySet()
                ) {
                    Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    Stock s2 = Objects.requireNonNull(currentPlayer.getPlayerStocks().stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    System.out.println(s1.getTickerSymbol() + ", old stock price -> " + df.format(s1.getPrice()));
                    s1.setPrice(Math.ceil(s1.getPrice() * (1 + (entry.getValue() * .005) * (1 + s1.getStockVolatility()))));
                    s2.setPrice(Math.ceil(s1.getPrice() * (1 + (entry.getValue() * .005) * (1 + s1.getStockVolatility()))));
                    System.out.println(s1.getTickerSymbol() + ", new stock price -> " + df.format(s1.getPrice()));
                }
                for (Map.Entry<String, Integer> entry : sellTransactions.entrySet()
                ) {
                    Stock s1 = Objects.requireNonNull(stocks.stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    Stock s2 = Objects.requireNonNull(currentPlayer.getPlayerStocks().stream().filter(stock -> entry.getKey().equals(stock.getTickerSymbol())).findFirst().orElse(stocks.get(0)));
                    System.out.println(s1.getTickerSymbol() + ", old stock price -> " + df.format(s1.getPrice()));
                    s1.setPrice(Math.ceil(s1.getPrice() * (1 + (entry.getValue() * .005) * (1 - s1.getStockVolatility()))));
                    s2.setPrice(Math.ceil(s1.getPrice() * (1 + (entry.getValue() * .005) * (1 - s1.getStockVolatility()))));
                    System.out.println(s1.getTickerSymbol() + ", new stock price -> " + df.format(s1.getPrice()));
                }
                buyTransactions.clear();
                sellTransactions.clear();
                Console.pause(1000);
            }
        }
    }
    private void namesPrompt() {
        setWinnerNames(prompter.prompt("High Score! Enter the 3 letters of initials for player's name: ", "[A-Z]{3}",
                "please enter valid 3 letters for the winner."));
    }

    private void playersPrompt() {
        setPlayerCount(prompter.prompt("Enter the number of players (1-4): ", "[1-4]{1}",
                "please enter a valid set of players"));
    }

    private void roundsPrompt() {
        setRounds(prompter.prompt("Enter the number of rounds to play [5] or [10]: ", "[1,5,10]{1,2}",
                "Wrong input, please enter [5] or [10]!"));
    }

    private void stockPrompt() {
        setStockName(prompter.prompt("Enter the ticker symbol for the stock: ", "[A-Z]{1,4}",
                "please enter a valid stock"));
    }

    private void qtyPrompt() {
        setQty(prompter.prompt("You have $" + currentPlayer.getCashBalance() + ", Enter the amount of shares[0-99]: ", "[0-9]{1,2}",
                "please enter a valid number[0-99]"));
    }

    private void playerOption() {
        showGameBoard();
        setPlayerOption(prompter.prompt(currentPlayer.getName() + " Choose one of the following, [B]uy stocks, [S]ell stocks, [C]heck Balance or [E]nd turn:", "[BSRCE]{1}",
                "you did not enter a correct response, must choose one of the following: [B], [S], [C] or [E]."));
    }

    private void showGameBoard() { //todo: move these lines for board, replace with a board.show() method
        Console.pause(2000);
        Console.clear();
        System.out.println("---C U R R E N T  P R I C E S---  \nMarket Event: " +currentMarketForce.getCardText());
        for (Stock item : stocks) {
            System.out.println("Company: " + item.getCompanyName()+", Stock type: " + item.getStockType()+ ", stock volatility: " + item.getStockVolatility() + ", Dividends: "+ item.getStockDividend() + ", Stock Price: " + df.format(item.getPrice())+ ", Stock Ticker: " + item.getTickerSymbol());
        }
        Console.blankLines(1);
        System.out.println("---P l A Y E R  I N F O---");
        currentPlayer.printBalance();
        Console.blankLines(1);
    }



    //get & set
    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }

    public String getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(String playerCount) {
        this.playerCount = playerCount;
    }

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

    public String getWinnerNames() {
        return winnerNames;
    }

    public void setWinnerNames(String winnerNames) {
        this.winnerNames = winnerNames;
    }

    //main test
    public static void main(String[] args) {
        GameMarketController game = new GameMarketController();
        game.initialize();
    }
}