package com.javafx.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

interface GameStateMangaer extends Serializable {

    public void save();

    public GameState load();

}

public class GameState implements GameStateMangaer {

    int score_count;
    double reach_pillar_x;
    double reach_pillar_width;
    double cherry_x;

    public GameState(int score_count, double reach_pillar_x, double reach_pillar_width, double cherry_x) {
        this.score_count = score_count;
        this.reach_pillar_x = reach_pillar_x;
        this.reach_pillar_width = reach_pillar_width;
        this.cherry_x = cherry_x;
    }

    // serialize and deserialize
    @Override
    public void save() {
        String filename = "game_data/saved_game.ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            System.err.println("error in serialize");
        }
    }

    @Override
    public GameState load() {
        GameState game_state = null;
        try {
            FileInputStream fileIn = new FileInputStream("game_data/saved_game.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game_state = (GameState) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("error in deserialize");
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Engine class not found");
            return null;
        }
        return game_state;
    }

}
