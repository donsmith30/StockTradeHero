package com.stocktradehero;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

class TopScores {
    private Map<String, Double> topTenMap = loadTopTenMap(){

    };
    private static final String dataFilePath = "data/board.dat";




    private void update() {
        public void update(int id, Reward reward) {
            DuckRacer racer = null;
            if (racerMap.containsKey(id)) { //in map so get it and make it win
                racer = racerMap.get(id);
            } else { //not in map, so put in map, then make it win
                racer = new DuckRacer(id, studentIdMap.get(id));
                racerMap.put(id, racer);
            }
            //we call win in all cases, so only say it once
            racer.win(reward);
            save();
        }

    }


    public void show() {
        Collection<Player> winners = new ArrayList<>();
        for (Player winner: winners) {
            System.out.println(winner);
        }

    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            out.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Board getInstance() {
        // declare return value
        Board board = null;
        if (Files.exists(Path.of(dataFilePath))){
            // de-serialize the binary file board.dat back into the board object
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFilePath))) {
                board = (Board) in.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            board = new Board();
        }
        return board;
    }

}