package com.javafx.game;

import javafx.scene.layout.Pane;

public class Engine {

    public static final double PILLAR_HEIGHT = 200;
    public static final double STAND_CLIFF_X = 80;

    Play_Screen play_screen;
    boolean can_stick_grow; // ensure that stick not grow/rotate when mouse pressed 2n time
    int cherry_count;
    int score_count;
    Pillar pillar_stand;// its will always be at the right edge of the screen and cliff at x = 80
    Pillar pillar_reach;
    Stick new_stick; // stick that will be grow and rotate
    Stick old_stick; // stick that is already rotated and become bridge

    public void new_game_init() {
        can_stick_grow = true;
        cherry_count = 0;
        score_count = 0;
        pillar_stand = new Pillar(0, PILLAR_HEIGHT, STAND_CLIFF_X);
        pillar_reach = Pillar.generatePillar();
        old_stick = new Stick(pillar_stand);
        new_stick = new Stick(pillar_stand);

    }

    public void saved_game_init() {

    }

    public void move_objects_left() {
        play_screen.pane.getChildren().removeAll(pillar_stand.pillar, old_stick.stick);
        double reach_pillar_end_x = pillar_reach.pillar.getX() + pillar_reach.pillar.getWidth();
        double distance = reach_pillar_end_x - STAND_CLIFF_X;
        Effects.move_left(distance, () -> {
            // This code will execute after the animation completes
            next_level_init();
            // Place your next instruction here
        }, pillar_reach.pillar,
                pillar_reach.red_platform, new_stick.stick);

    }

    public void next_level_init() {
        play_screen.pane.getChildren().removeAll(pillar_reach.red_platform, old_stick.stick);
        // play_screen.pane.getChildren().removeAll(pillar_stand.pillar,
        // old_stick.stick, pillar_reach.red_platform);
        pillar_stand = pillar_reach;
        pillar_reach = Pillar.generatePillar();
        old_stick = new_stick;
        new_stick = new Stick(pillar_stand);

        play_screen.pane.getChildren().addAll(pillar_reach.pillar, pillar_reach.red_platform, new_stick.stick);

        can_stick_grow = true;

    }

    public Engine(Play_Screen play_screen) {
        this.play_screen = play_screen;
        new_game_init();
        play_screen.pane.getChildren().addAll(pillar_stand.pillar,
                pillar_reach.pillar,
                pillar_reach.red_platform, old_stick.stick, new_stick.stick);
        play_screen.score_counter.setText(Integer.toString(score_count));
        play_screen.cherry_counter.setText(Integer.toString(cherry_count));
    }

    public void stick_grow() {
        new_stick.grow();
    }

    public void stop_stick_grow() {
        new_stick.stop_grow();
    }

    boolean is_level_clear;

    public void rotate_stick() {
        new_stick.rotate(() -> {
            // This code will execute after the animation completes
            after_rotate(is_level_clear);
            // Place your next instruction here
        });
        is_level_clear = is_level_clear();
        can_stick_grow = false; // you cannot grow the stick after it is rotated 1st time

    }

    public void after_rotate(boolean is_level_clear) {
        if (is_level_clear) {
            play_screen.gained_score();
            move_objects_left();
        } else {
            new_stick.rotate(() -> {
                // This code will execute after the animation completes
                Sound.gameover();
                Screen_Loader.gameover();
                // Place your next instruction here
            });
        }
    }

    public boolean is_level_clear() {
        double bridge_length = new_stick.stick.getHeight() - (Stick.WIDTH / 2);
        double bridge_end_x = bridge_length + STAND_CLIFF_X;
        double reach_pillar_start_x = pillar_reach.pillar.getX();
        double reach_pillar_end_x = reach_pillar_start_x + pillar_reach.pillar.getWidth();
        if (reach_pillar_start_x <= bridge_end_x && bridge_end_x <= reach_pillar_end_x) {
            score_count++;
            double red_platform_start_x = pillar_reach.red_platform.getX();
            double red_platform_end_x = red_platform_start_x + Pillar.RED_PLATFORM_WIDTH;
            if (red_platform_start_x <= bridge_end_x && bridge_end_x <= red_platform_end_x) {
                score_count++;
                cherry_count++;
            }
            return true; // level cleard
        }
        return false; // game over
    }
}
