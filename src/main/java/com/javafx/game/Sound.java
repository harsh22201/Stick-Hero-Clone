package com.javafx.game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

    private static MediaPlayer rain;
    private static MediaPlayer stick_grow;
    private static MediaPlayer hero_fall;
    private static MediaPlayer whoosh;
    private static MediaPlayer score;
    private static MediaPlayer click;

    public static void rain() {
        String audiopath = Sound.class.getResource("sounds/RAIN.wav").toString();
        Media backgroundMusic = new Media(audiopath);
        rain = new MediaPlayer(backgroundMusic);
        rain.setCycleCount(MediaPlayer.INDEFINITE);
        rain.play();
    }

    public static void stick_grow() {
        String audiopath = Sound.class.getResource("sounds/STICK_GROW.mp3").toString();
        Media backgroundMusic = new Media(audiopath);
        stick_grow = new MediaPlayer(backgroundMusic);
        stick_grow.setCycleCount(MediaPlayer.INDEFINITE);
        stick_grow.play();
    }

    public static void stick_grow_stop() {
        stick_grow.stop();
    }

    public static void hero_fall() {
        String audiopath = Sound.class.getResource("sounds/HERO_FALL.mp3").toString();
        Media backgroundMusic = new Media(audiopath);
        hero_fall = new MediaPlayer(backgroundMusic);
        hero_fall.play();
    }

    public static void whoosh() {
        String audiopath = Sound.class.getResource("sounds/WHOOSH.mp3").toString();
        Media backgroundMusic = new Media(audiopath);
        whoosh = new MediaPlayer(backgroundMusic);
        whoosh.play();
    }

    public static void score() {
        String audiopath = Sound.class.getResource("sounds/SCORE.wav").toString();
        Media backgroundMusic = new Media(audiopath);
        score = new MediaPlayer(backgroundMusic);
        score.play();
    }

    public static void click() {
        String audiopath = Sound.class.getResource("sounds/CLICK.mp3").toString();
        Media backgroundMusic = new Media(audiopath);
        click = new MediaPlayer(backgroundMusic);
        click.play();
    }
}
