package com.javafx.game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound { // singleton pattern

    private static Sound sound = null;

    // get instance for sound
    public static Sound getInstance() {
        if (sound == null) {
            sound = new Sound();
        }
        return sound;
    }

    private Sound() {
    }

    private static boolean isMuted = false;

    public static boolean isMuted() {
        return isMuted;
    }

    private static Media background_Media;
    private static Media stick_grow_Media;
    private static Media hero_fall_Media;
    private static Media whoosh_Media;
    private static Media score_Media;
    private static Media perfect_score_Media;
    private static Media game_over_Media;
    private static Media click_Media;
    private static MediaPlayer background;
    private static MediaPlayer stick_grow;
    private static MediaPlayer hero_fall;
    private static MediaPlayer whoosh;
    private static MediaPlayer score;
    private static MediaPlayer perfect_score;
    private static MediaPlayer game_over;
    private static MediaPlayer click;

    static {
        background_Media = new Media(Sound.class.getResource("sounds/BACKGROUND.wav").toString());
        stick_grow_Media = new Media(Sound.class.getResource("sounds/STICK_GROW.mp3").toString());
        hero_fall_Media = new Media(Sound.class.getResource("sounds/HERO_FALL.mp3").toString());
        whoosh_Media = new Media(Sound.class.getResource("sounds/WHOOSH.mp3").toString());
        score_Media = new Media(Sound.class.getResource("sounds/SCORE.wav").toString());
        perfect_score_Media = new Media(Sound.class.getResource("sounds/PERFECT_SCORE.mp3").toString());
        game_over_Media = new Media(Sound.class.getResource("sounds/GAMEOVER.mp3").toString());
        click_Media = new Media(Sound.class.getResource("sounds/CLICK.mp3").toString());
    }

    private static void play_media(MediaPlayer media, int count) {
        if (isMuted) {
            return;
        }
        media.setCycleCount(count);
        media.play();
    }

    private static void stop_media(MediaPlayer media) {
        if (isMuted) {
            return;
        }
        media.stop();
    }

    public static void background() {
        background = new MediaPlayer(background_Media);
        play_media(background, MediaPlayer.INDEFINITE);
    }

    public static void stick_grow() {

        stick_grow = new MediaPlayer(stick_grow_Media);
        play_media(stick_grow, MediaPlayer.INDEFINITE);
    }

    public static void stop_stick_grow() {
        stop_media(stick_grow);
    }

    public static void hero_fall() {

        hero_fall = new MediaPlayer(hero_fall_Media);
        play_media(hero_fall, 1);
    }

    public static void flip() {

        whoosh = new MediaPlayer(whoosh_Media);
        play_media(whoosh, 1);
    }

    public static void score() {

        score = new MediaPlayer(score_Media);
        play_media(score, 1);
    }

    public static void click() {

        click = new MediaPlayer(click_Media);
        play_media(click, 1);
    }

    public static void game_over() {

        game_over = new MediaPlayer(game_over_Media);
        play_media(game_over, 1);
    }

    public static void perfect_score() {

        perfect_score = new MediaPlayer(perfect_score_Media);
        play_media(perfect_score, 1);
    }

    public static void mute() {
        isMuted = true;
        background.setMute(isMuted);
    }

    public static void unmute() {
        isMuted = false;
        background.setMute(isMuted);
    }
}
