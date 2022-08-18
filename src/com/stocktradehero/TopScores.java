package com.stocktradehero;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;

class TopScores implements Serializable{

    private DecimalFormat df = new DecimalFormat("$" + "#.00");
    List<Player> topTenWinners = new ArrayList<>();
    private static final String dataFilePath = "data/top10.dat";

    //ctor
    private TopScores() {
    }

    //methods
    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            out.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        System.out.println("---T O P  1 0  W I N N E R S---");
        for (Player winner : topTenWinners) {
            System.out.println(winner.getName()+ "'s total amount balance is:  " + df.format(winner.getTotalAmountBalance()));
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