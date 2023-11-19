package com.javafx.game;

import java.util.ArrayList;

class Player {
    private float stick_length;
    private boolean isDead;
    private int cherryCount;
    private ArrayList<Revive> revives;

    public Player(float stick_length, boolean isDead, int cherryCount, ArrayList<Revive> revives) {
        this.stick_length = stick_length;
        this.isDead = isDead;
        this.cherryCount = cherryCount;
        this.revives = revives;
    }

    public float getStick_length() {
        return stick_length;
    }

    public void setStick_length(float stick_length) {
        this.stick_length = stick_length;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public ArrayList<Revive> getRevives() {
        return revives;
    }

    public void setRevives(ArrayList<Revive> revives) {
        this.revives = revives;
    }

    public void move() {
    }

    public void extendStick() {
    }

    public void collectCherry() {
    }

    public void flipUp() {
    }

    public void flipDown() {
    }

    public void processUserInput(UserInput userInput) {
    }
}

class UserInput {
    private String phase;
    private String action;
    private String direction;

    public UserInput(String phase, String action, String direction) {
        this.phase = phase;
        this.action = action;
        this.direction = direction;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

class Tree {
    private int y;
    private String color;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Tree(int y, String color) {
        this.y = y;
        this.color = color;
    }

    public void drawTree() {
    }
}

class Cherry {

    private int scoreValue;

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Cherry() {
        this.scoreValue = scoreValue;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public Cherry generateRandomCherry() {
        return new Cherry();
    }
}

class Game {
    private int player_distance_from_edge;
    private ArrayList<Platform> platforms;
    private int player_x_coordinate;
    private int player_y_coordinate;
    private int score;
    private Player player;

    public Game(int player_distance_from_edge, ArrayList<Platform> platforms, int player_x_coordinate,
            int player_y_coordinate, int score, Player player) {
        this.player_distance_from_edge = player_distance_from_edge;
        this.platforms = platforms;
        this.player_x_coordinate = player_x_coordinate;
        this.player_y_coordinate = player_y_coordinate;
        this.score = score;
        this.player = player;
    }

    public int getPlayer_distance_from_edge() {
        return player_distance_from_edge;
    }

    public void setPlayer_distance_from_edge(int player_distance_from_edge) {
        this.player_distance_from_edge = player_distance_from_edge;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }

    public int getPlayer_x_coordinate() {
        return player_x_coordinate;
    }

    public void setPlayer_x_coordinate(int player_x_coordinate) {
        this.player_x_coordinate = player_x_coordinate;
    }

    public int getPlayer_y_coordinate() {
        return player_y_coordinate;
    }

    public void setPlayer_y_coordinate(int player_y_coordinate) {
        this.player_y_coordinate = player_y_coordinate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void resetGame() {
    }

    public void updateGame() {
    }

    public void main(String[] args) {
    }
}

class Platform {
    private int x;
    private int w;

    public Platform(int x, int w) {
        this.x = x;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getW() {
        return w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setW(int w) {
        this.w = w;
    }

    public Platform generateRandomPlatform() {
        return new Platform(0, 0);
    }
}

class GameManager {

    private int current_level;
    private Game game;

    public GameManager(int current_level, Game game) {
        this.current_level = current_level;
        this.game = game;
    }

    public int getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(int current_level) {
        this.current_level = current_level;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameManager() {
    }

    public void startGame() {
    }

    public void endGame() {
    }

    public void continueGame() {
    }
}

class Revive {
    private int cherries_needed;
    private boolean is_revived;

    public int getCherries_needed() {
        return cherries_needed;
    }

    public void setCherries_needed(int cherries_needed) {
        this.cherries_needed = cherries_needed;
    }

    public boolean isIs_revived() {
        return is_revived;
    }

    public void setIs_revived(boolean is_revived) {
        this.is_revived = is_revived;
    }

    public Revive(int cherries_needed) {
        this.cherries_needed = cherries_needed;
    }

    public boolean isRevivePossible(int cherryCount) {
        return false;
    }

    public void executeRevive() {
    }
}

class SoundManager {
    public SoundManager() {
    }
}
