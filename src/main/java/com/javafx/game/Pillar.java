package com.javafx.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pillar {

    static final double RED_PLATFORM_WIDTH = 8;
    static final double RED_PLATFORM_HEIGHT = 5;
    static final double MIN_WIDTH = 16;
    static final double MAX_WIDTH = 100;
    double positionY;
    double positionX; // X position of the pillar
    double height; // Height of the pillar
    double width; // Width of the pillar
    Rectangle pillar;
    Rectangle red_platform;

    public Pillar(double positionX, double height, double width) {
        this.positionX = positionX;
        this.height = height;
        this.width = width;
        this.positionY = Main.SCREEN_HEIGHT - height;
        pillar = new Rectangle();
        pillar.setX(positionX);
        pillar.setY(positionY);
        pillar.setHeight(height);
        pillar.setWidth(width);
        pillar.setFill(Color.BLACK);
        red_platform = new Rectangle();
        red_platform.setX(positionX + (width / 2) - (RED_PLATFORM_WIDTH / 2));
        red_platform.setY(Main.SCREEN_HEIGHT - height);
        red_platform.setHeight(RED_PLATFORM_HEIGHT);
        red_platform.setWidth(RED_PLATFORM_WIDTH);
        red_platform.setFill(Color.RED);
    }

    public static Pillar generatePillar() {
        double width = Math.random() * (MAX_WIDTH - MIN_WIDTH) + MIN_WIDTH;
        final double MAX_X = Main.SCREEN_WIDTH - width;
        final double MIN_X = Engine.STAND_CLIFF_X + 5;
        double positionX = Math.random() * (MAX_X - MIN_X) + MIN_X;
        return new Pillar(positionX, Engine.PILLAR_HEIGHT, width);
    }

}
