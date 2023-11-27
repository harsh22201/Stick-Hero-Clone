package com.javafx.game;

public class Engine {

    public static final double STAND_CLIFF_X = 80;
    public static final double STAND_CLIFF_Y = Main.SCREEN_HEIGHT - Pillar.HEIGHT;

    Play_Screen play_screen;
    int cherry_count;
    int score_count;
    boolean is_level_clear;

    // current state of the game
    boolean can_stick_grow; // ensure that stick not grow/rotate when mouse pressed 2n time
    Pillar stand_pillar;// its will always be at the right edge of the screen and cliff at x = 80
    Pillar reach_pillar;
    Stick new_stick; // stick that will be grow and rotate
    Stick old_stick; // stick that is already rotated and become bridge
    Hero hero;
    Cherry cherry;

    public void new_game_init() {
        this.cherry_count = 0;
        this.score_count = 0;
        this.can_stick_grow = true;
        this.stand_pillar = new Pillar(0, STAND_CLIFF_X);
        this.reach_pillar = Pillar.generate_random_reach_pillar();
        this.old_stick = new Stick();
        this.new_stick = new Stick();
        this.hero = new Hero();
        this.cherry = new Cherry(-100); // vherry is not shown on screen
    }

    public void saved_game_init() {

    }

    public Engine(Play_Screen play_screen) {
        this.play_screen = play_screen;
        new_game_init();
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
        new_stick.rotate(() -> {
            move_hero(); // This code will execute after the animation completes
        });
        // might raise concurrency issue // for fix use this directly in move_hero
        is_level_clear = is_level_clear();

    }

    public void move_hero() {
        if (is_level_clear) {
            //
            double reach_pillar_end_x = reach_pillar.pillar.getX() + reach_pillar.pillar.getWidth();
            double distance = reach_pillar_end_x - STAND_CLIFF_X;
            //
            hero.run(distance, () -> {
                level_cleared();
            });
        } else { // gameover
            new_stick.rotate(() -> {
                game_over();
            });
        }
    }

    public void level_cleared() {
        hero.idle();
        play_screen.gained_score();
        play_screen.gained_cherry();
        move_objects_left();
    }

    public void game_over() {
        Sound.game_over();
        Screen_Loader.game_over();
    }

    public void move_objects_left() {
        double reach_pillar_end_x = reach_pillar.pillar.getX() + reach_pillar.pillar.getWidth();
        double distance = reach_pillar_end_x - STAND_CLIFF_X;
        Effects.move_left(distance, () -> {
            // This code will execute after the animation completes
            next_level_init();
            // Place your next instruction here
        }, reach_pillar.pillar, stand_pillar.pillar, reach_pillar.red_platform, new_stick.rectangle,
                old_stick.rectangle, hero.imageview, cherry.imageview);
    }

    public void next_level_init() {
        play_screen.pane.getChildren().removeAll(stand_pillar.pillar, reach_pillar.red_platform,
                old_stick.rectangle, cherry.imageview);
        stand_pillar = reach_pillar;
        reach_pillar = Pillar.generate_random_reach_pillar();
        old_stick = new_stick;
        new_stick = new Stick();
        double pillars_gap = reach_pillar.pillar.getX() - Engine.STAND_CLIFF_X;
        cherry = cherry.generate_random_cherry(pillars_gap);
        play_screen.pane.getChildren().addAll(reach_pillar.pillar, reach_pillar.red_platform,
                new_stick.rectangle, cherry.imageview);
        can_stick_grow = true;
    }

    public boolean is_level_clear() {
        double bridge_length = new_stick.rectangle.getHeight() - (Stick.WIDTH / 2);
        double reach_pillar_start_x = reach_pillar.pillar.getX();
        double reach_pillar_end_x = reach_pillar_start_x + reach_pillar.pillar.getWidth();
        double bridge_end_x = bridge_length + STAND_CLIFF_X;
        if (reach_pillar.pillar.getX() <= bridge_end_x && bridge_end_x <= reach_pillar_end_x) {
            score_count++;
            double red_platform_start_x = reach_pillar.red_platform.getX();
            double red_platform_end_x = red_platform_start_x + Pillar.RED_PLATFORM_WIDTH;
            if (red_platform_start_x <= bridge_end_x && bridge_end_x <= red_platform_end_x) {
                score_count++;
            }
            return true; // level cleard
        }
        return false; // game over
    }
}
