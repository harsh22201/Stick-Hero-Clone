package com.javafx.game;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hero {
    ImageView imageview;
    Animation idle_animation;
    Animation running_animation_basic;
    Animation running_animation;
    boolean can_flip;
    static final double DIMESION = 50; // image is square so width = height // 50x50
    static final double X_OFFSET = 30; // + to move right - to move left
    static final double Y_OFFSET = 7; // 1.5 * Stick.WIDTH // - to move up + to move down
    // PNG witth analysis 128 x 128 : 16-64-48

    public Hero() {

        this.imageview = new ImageView(new Image(getClass().getResourceAsStream("bitmaps/HERO.png")));
        this.imageview.setFitHeight(Hero.DIMESION);
        this.imageview.setFitWidth(Hero.DIMESION);
        this.imageview.setX(Engine.STAND_CLIFF_X - X_OFFSET);
        this.imageview.setY(Engine.STAND_CLIFF_Y - Hero.DIMESION + Y_OFFSET);
        this.idle_animation = Effects.animation(imageview, Effects.idle_frames_list);
        this.running_animation_basic = Effects.animation(imageview, Effects.running_frames_list);
        this.running_animation = running_animation_basic;
        this.can_flip = false;
        idle();
    }

    public void run(double distance, Runnable onFinished) {
        Animation moving_right_animation = Effects.move_right(() -> {
        }, distance, imageview);
        running_animation = new ParallelTransition();
        ((ParallelTransition) running_animation).getChildren().addAll(moving_right_animation, running_animation_basic);
        running_animation.setOnFinished(event -> {
            if (onFinished != null) {
                onFinished.run(); // Execute the next instruction
            }
        });

        idle_animation.stop();
        running_animation.play();
    }

    public void idle() {
        running_animation.stop();
        idle_animation.play();
    }

    public void flip() {
        // make image flip again and again
        if (can_flip) {
            double cur_state = imageview.getScaleY(); // 1 up or -1 down
            imageview.setScaleY(-cur_state);
            Sound.flip();
            double FLIP_Y_OFFSET = Hero.DIMESION - 3 * Stick.WIDTH;
            imageview.setY(imageview.getY() + (FLIP_Y_OFFSET) * cur_state);
        }
    }
}
