package com.javafx.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.animation.RotateTransition;

public class Stick {

    static final double WIDTH = 4;
    static final double STICK_GROW_RATE = 3; // 1x 2x 3x 4x 5x
    static final double STICK_ROTATE_RATE = 2; // 1x 2x 3x 4x 5x
    static final double MAX_STICK_HEIGHT = 400;

    double height = 0;

    Rectangle stick;
    private Timeline growingTimeline;

    public Stick(Pillar pillar_stand) {
        // double positionX = pillar_stand.pillar.getX() +
        // pillar_stand.pillar.getWidth() - Stick.WIDTH;

        double positionX = Engine.STAND_CLIFF_X - Stick.WIDTH;
        double positionY = pillar_stand.pillar.getY(); // Y position of the pillar
        stick = new Rectangle();
        stick.setX(positionX);
        stick.setY(positionY);
        stick.setHeight(height);
        stick.setWidth(Stick.WIDTH);
        stick.setFill(Color.BLACK);
    }

    public void grow() {
        if (growingTimeline == null) {
            growingTimeline = new Timeline(
                    new KeyFrame(Duration.millis(10 / STICK_GROW_RATE), e -> increaseHeight()));
            growingTimeline.setCycleCount(Animation.INDEFINITE);
            growingTimeline.play();
        }
    }

    public void stop_grow() {
        if (growingTimeline != null) {
            growingTimeline.stop();
            growingTimeline = null;
        }
    }

    private void increaseHeight() {
        if (stick.getHeight() >= MAX_STICK_HEIGHT) {
            stop_grow();
            return;
        }
        double newY = stick.getY() - 0.5; // Move the stick upwards
        double newHeight = stick.getHeight() + 0.5; // Increase the height
        stick.setY(newY);
        stick.setHeight(newHeight);
    }

    public void rotate(Runnable onFinished) {
        Rotate rotate = new Rotate();
        rotate.setPivotX(stick.getX() + stick.getWidth() / 2); // Pivot X at center
        rotate.setPivotY(stick.getY() + stick.getHeight()); // Pivot Y at bottom
        stick.getTransforms().add(rotate); // Add rotation to stick

        // Create a Timeline to rotate the stick
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1 / STICK_ROTATE_RATE), new KeyValue(rotate.angleProperty(), 90)));

        timeline.setOnFinished(event -> {
            if (onFinished != null) {
                onFinished.run(); // Execute the next instruction
            }
        });
        timeline.play(); // Play the animation
    }

}
