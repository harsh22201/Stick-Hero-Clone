package com.javafx.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.Serializable;

public class Hero implements Serializable {
    ImageView imageview;
    Animation idle_animation;
    Animation running_animation_basic;
    Animation moving_right_animation;
    Animation running_animation;
    Animation falling_animation;
    boolean is_down;
    boolean can_flip;
    static final double DIMESION = 50; // image is square so width = height // 50x50
    static final double X_OFFSET = 30; // + to move left - to move right
    static final double Y_OFFSET = 7; // 1.5 * Stick.WIDTH // - to move up + to move down
    // PNG witth analysis 128 x 128 : 16-64-48

    public Hero() {

        this.imageview = new ImageView(new Image(getClass().getResourceAsStream("bitmaps/HERO.png")));
        this.imageview.setFitHeight(Hero.DIMESION);
        this.imageview.setFitWidth(Hero.DIMESION);
        this.imageview.setX(Engine.STAND_CLIFF_X - X_OFFSET);
        this.imageview.setY(Engine.STAND_CLIFF_Y - Hero.DIMESION + Y_OFFSET);
        this.idle_animation = Effects.animation(imageview, "Idle");
        this.running_animation_basic = Effects.animation(imageview, "Run");
        this.falling_animation = Effects.move_down(Pillar.HEIGHT + Hero.DIMESION, imageview);
        idle_animation.play();
        can_flip = false;
    }

    public Animation run(Engine engine, double distance, Runnable onFinished) {

        Timeline timeline = new Timeline(); // Initialize the timeline variable
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), e -> {
            double xCoordinate = imageview.getTranslateX() + imageview.getX() + Hero.DIMESION / 2;
            double cherry_start_x = engine.cherry.imageview.getX();
            double cherry_end_x = cherry_start_x + Cherry.DIMESION;
            if (is_down && engine.is_cherry_capture == false) {
                if (cherry_start_x < xCoordinate && xCoordinate < cherry_end_x) {
                    engine.is_cherry_capture = true;
                    engine.cherry.imageview.setX(-50);
                }
            }
            if (xCoordinate > engine.reach_pillar.pillar.getX()) {
                can_flip = false;
                if (is_down) {
                    moving_right_animation.stop();
                    engine.game_over();
                    timeline.stop();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        moving_right_animation = Effects.move_right(() -> {
        }, distance, imageview);

        moving_right_animation.setOnFinished(event -> {
            if (onFinished != null) {
                timeline.stop();
                running_animation_basic.stop();
                idle_animation.play();
                onFinished.run(); // Execute the next instruction
            }
        });

        idle_animation.stop();
        moving_right_animation.play();
        timeline.play();
        running_animation_basic.play();
        can_flip = true;
        return moving_right_animation;
    }

    public void flip() {
        if (can_flip) {
            int cur_state = is_down ? -1 : 1;
            imageview.setScaleY(-cur_state);
            Sound.flip();
            double FLIP_Y_OFFSET = Hero.DIMESION - 3 * Stick.WIDTH;
            imageview.setY(imageview.getY() + (FLIP_Y_OFFSET) * cur_state);
            is_down = !is_down;
        }
    }

    public Animation fall(Runnable onFinished) {
        can_flip = false;
        falling_animation.setOnFinished(event -> {
            if (onFinished != null) {
                onFinished.run(); // Execute the next instruction
            }
        });
        falling_animation.play();
        return falling_animation;
    }
}
