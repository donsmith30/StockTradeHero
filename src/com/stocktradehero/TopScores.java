package com.stocktradehero;

import com.apps.util.Prompter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class TopScores implements Serializable{
    //private Map<String, Double> topTenMap = new HashMap<>();
    private List<Player> toptenwinners = new ArrayList<>();


    private static final String dataFilePath = "data/top10.dat";

    //ctor
    private TopScores() {
    }

    public void update(Player winner) {
        System.out.println(winner.getName());

        if (toptenwinners.size() < 10) {
            toptenwinners.add(winner);

        } else if (toptenwinners.get(9).getTotalAmountBalance() < winner.getTotalAmountBalance()) {
            System.out.println(winner.getName());
            toptenwinners.remove(9);
            toptenwinners.add(winner);
        }
        else {

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
        System.out.println("T O P    W I N N E R S");
        for (Player winner : toptenwinners) {
            System.out.println(winner.getName()+ "'s total amount balance is:  " + winner.getTotalAmountBalance());
        }
    }

    static TopScores getInstance() {
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


}