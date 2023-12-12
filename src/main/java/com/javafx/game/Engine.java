package com.javafx.game;

import java.io.*;

import javafx.animation.Animation;

public class Engine { // using Sounds // Dependency

    public static final double STAND_CLIFF_X = 80;
    public static final double STAND_CLIFF_Y = Main.SCREEN_HEIGHT - Pillar.HEIGHT;

    Play_Screen play_screen;
    int cherry_count;
    int score_count;
    boolean is_cherry_capture;
    boolean is_level_clear;
    Animation current_animation;

    // current state of the game // they also represent association
    boolean can_stick_grow; // ensure that stick not grow/rotate when mouse pressed 2n time
    Pillar stand_pillar;// its will always be at the right edge of the screen and cliff at x = 80
    Pillar reach_pillar;
    Stick new_stick; // stick that will be grow and rotate
    Stick old_stick; // stick that is already rotated and become bridge
    Hero hero;
    Cherry cherry;

    public void new_game_init() {
        this.cherry_count = Cherry.get_total_cherry();
        this.can_stick_grow = true;
        this.stand_pillar = new Pillar(0, STAND_CLIFF_X);
        this.reach_pillar = Pillar.pillar_factory();
        this.old_stick = new Stick();
        this.new_stick = new Stick();
        this.hero = new Hero();
        this.cherry = new Cherry(-100); // vherry is not shown on screen
        this.is_cherry_capture = false;
        this.is_level_clear = false;
        this.current_animation = hero.idle_animation;
    }

    public void saved_game_init(GameState game_state) {
        this.score_count = game_state.score_count;
        this.cherry_count = Cherry.get_total_cherry();
        this.can_stick_grow = true;
        this.stand_pillar = new Pillar(0, STAND_CLIFF_X);
        this.reach_pillar = new Pillar(game_state.reach_pillar_x, game_state.reach_pillar_width);
        this.old_stick = new Stick();
        this.new_stick = new Stick();
        this.hero = new Hero();
        this.cherry = new Cherry(game_state.cherry_x);
        this.is_cherry_capture = false;
        this.is_level_clear = false;
        this.current_animation = hero.idle_animation;
    }

    public Engine(Play_Screen play_screen, GameState game_state) {
        this.score_count = 0;
        this.play_screen = play_screen;
        if (game_state == null) {
            new_game_init();
        } else {
            saved_game_init(game_state);
        }
        play_screen.pane.getChildren().addAll(stand_pillar.pillar, reach_pillar.pillar, reach_pillar.red_platform,
                old_stick.rectangle, new_stick.rectangle, hero.imageview, cherry.imageview);
        play_screen.score_counter.setText(Integer.toString(score_count));
        play_screen.cherry_counter.setText(Integer.toString(cherry_count));
    }

    public void stick_grow() {
        if (can_stick_grow) {
            Sound.stick_grow();
            new_stick.grow();
            can_stick_grow = false;
        }
    }

    public void try_stop_stick_grow() {
        if (new_stick.stop_grow()) {
            Sound.stop_stick_grow();
            rotate_stick();
        }
    }

    public void rotate_stick() {
        current_animation = new_stick.rotate(() -> {
            move_hero(); // This code will execute after the animation completes
        });
        // might raise concurrency issue // for fix use this directly in move_hero
    }

    public void hero_flip() {
        hero.flip();
    }

    public void move_hero() {
        is_level_clear = is_level_clear();
        double distance;
        if (is_level_clear) {
            //
            double reach_pillar_end_x = reach_pillar.pillar.getX() + reach_pillar.pillar.getWidth();
            distance = reach_pillar_end_x - STAND_CLIFF_X;
            //
            current_animation = hero.run(this, distance, () -> {
                level_cleared();
            });
        } else { // gameover
            //
            distance = new_stick.rectangle.getHeight() + 10;
            //
            current_animation = hero.run(this, distance, () -> {
                game_over();
            });
        }
    }

    public void level_cleared() {
        update_score();
        play_screen.gained_score();
        if (is_cherry_capture) {
            cherry_count++;
            play_screen.gained_cherry();
        }
        double reach_pillar_end_x = reach_pillar.pillar.getX() + reach_pillar.pillar.getWidth();
        double distance = reach_pillar_end_x - STAND_CLIFF_X;
        current_animation = Effects.move_left(distance, () -> {
            // This code will execute after the animation completes
            next_level_init();
            // Place your next instruction here
        }, reach_pillar.pillar, stand_pillar.pillar, reach_pillar.red_platform, new_stick.rectangle,
                old_stick.rectangle, hero.imageview, cherry.imageview);
    }

    public void game_over() {
        if (Score.get_best_score() < score_count) {
            Score.set_best_score(score_count);
        }
        Cherry.set_total_cherry(cherry_count);
        current_animation = hero.fall(() -> {
            Sound.hero_fall();
            Screen_Loader.game_over(score_count, play_screen);
        });
        new_stick.rotate(() -> {
        });
    }

    public void next_level_init() {
        play_screen.pane.getChildren().removeAll(stand_pillar.pillar, reach_pillar.red_platform,
                old_stick.rectangle, cherry.imageview, hero.imageview);
        stand_pillar = reach_pillar;
        reach_pillar = Pillar.pillar_factory();
        old_stick = new_stick;
        new_stick = new Stick();
        double pillars_gap = reach_pillar.pillar.getX() - Engine.STAND_CLIFF_X;
        cherry = cherry.generate_random_cherry(pillars_gap);
        play_screen.pane.getChildren().addAll(reach_pillar.pillar, reach_pillar.red_platform,
                new_stick.rectangle, cherry.imageview, hero.imageview);
        can_stick_grow = true;
        is_cherry_capture = false;
        current_animation = hero.idle_animation;
    }

    public boolean is_level_clear() {
        double bridge_length = new_stick.rectangle.getHeight() - (Stick.WIDTH / 2);
        double reach_pillar_start_x = reach_pillar.pillar.getX();
        double reach_pillar_end_x = reach_pillar_start_x + reach_pillar.pillar.getWidth();
        double bridge_end_x = bridge_length + STAND_CLIFF_X;
        if (reach_pillar.pillar.getX() < bridge_end_x && bridge_end_x < reach_pillar_end_x) {
            double red_platform_start_x = reach_pillar.red_platform.getX();
            double red_platform_end_x = red_platform_start_x + Pillar.RED_PLATFORM_WIDTH;
            if (red_platform_start_x <= bridge_end_x && bridge_end_x <= red_platform_end_x) {
                Sound.perfect_score();
            }
            return true; // level cleard
        }
        return false; // game over
    }

    public void update_score() {
        double bridge_length = new_stick.rectangle.getHeight() - (Stick.WIDTH / 2);
        double reach_pillar_start_x = reach_pillar.pillar.getX();
        double reach_pillar_end_x = reach_pillar_start_x + reach_pillar.pillar.getWidth();
        double bridge_end_x = bridge_length + STAND_CLIFF_X;
        if (reach_pillar.pillar.getX() < bridge_end_x && bridge_end_x < reach_pillar_end_x) {
            score_count++;
            double red_platform_start_x = reach_pillar.red_platform.getX();
            double red_platform_end_x = red_platform_start_x + Pillar.RED_PLATFORM_WIDTH;
            if (red_platform_start_x <= bridge_end_x && bridge_end_x <= red_platform_end_x) {
                score_count++;
            }
        }
    }

    public void pause() {
        if (Score.get_best_score() < score_count) {
            Score.set_best_score(score_count);
        }
        Cherry.set_total_cherry(cherry_count);
        current_animation.pause();
        Screen_Loader.pause(score_count, play_screen);
    }

    public void resume() {
        current_animation.play();
    }

    public void revive() {
        play_screen.pane.getChildren().removeAll(stand_pillar.pillar, stand_pillar.red_platform, reach_pillar.pillar,
                reach_pillar.red_platform, old_stick.rectangle, new_stick.rectangle, cherry.imageview, hero.imageview);
        new_game_init();
        play_screen.pane.getChildren().addAll(stand_pillar.pillar, reach_pillar.pillar, reach_pillar.red_platform,
                old_stick.rectangle, new_stick.rectangle, hero.imageview, cherry.imageview);
        play_screen.score_counter.setText(Integer.toString(score_count));
        play_screen.cherry_counter.setText(Integer.toString(cherry_count));
    }

}
