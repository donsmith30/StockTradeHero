package com.stocktradehero;

import com.apps.util.Prompter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class TopScores {
    //private Map<String, Double> topTenMap = new HashMap<>();
    private List<Player> toptenwinners = new ArrayList<>();
    Prompter prompter = new Prompter(new Scanner(System.in));
    private String winnerNames;

    private static final String dataFilePath = "data/board.dat";

    private void namesPrompt() {
        setWinnerNames(prompter.prompt("Enter the 3 letters of initial of the winner: ", "[A-Z]{3}",
                "please enter a valid name for the winner."));
    }

    //ctor
    public TopScores() {

    }

    public void update(Player winner) {
        System.out.println(winner.getName());
        namesPrompt();
        winner.setName(getWinnerNames());

        if (toptenwinners.size() < 10) {
            toptenwinners.add(winner);

        } else if (toptenwinners.get(9).getTotalAmountBalance() < winner.getTotalAmountBalance()) {
            System.out.println(winner.getName());
            namesPrompt();
            winner.setName(getWinnerNames());

            toptenwinners.remove(9);
            toptenwinners.add(winner);
        }
        save();
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            out.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void show() {
        Collection<Player> winners = new ArrayList<>();
        System.out.println("T O P    W I N N E R S");

        for (Player winner : toptenwinners) {
            System.out.println(winner.getName()+ "'s total amount balance is:  " + winner.getTotalAmountBalance());
        }
    }


    public static TopScores getInstance() {
        // declare return value
        TopScores topScores = null;
        if (Files.exists(Path.of(dataFilePath))) {
            // de-serialize the binary file board.dat back into the board object
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFilePath))) {
                topScores = (TopScores) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            topScores = new TopScores();
        }
        return topScores;
    }


    public String getWinnerNames() {
        return winnerNames;
    }

    public void setWinnerNames(String winnerNames) {
        this.winnerNames = winnerNames;
    }
}