package com.javafx.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cherry {

    ImageView imageview;
    static final double DIMESION = 20;

    public Cherry(double positionX) {
        double positionY = Engine.STAND_CLIFF_Y + Stick.WIDTH;
        this.imageview = new ImageView(new Image(getClass().getResourceAsStream("bitmaps/CHERRY.png")));
        this.imageview.setFitHeight(DIMESION);
        this.imageview.setFitWidth(DIMESION);
        this.imageview.setY(Engine.STAND_CLIFF_Y + 2 * Stick.WIDTH);
        this.imageview.setX(positionX);
        Effects.move_up_down(this.imageview, 5).play();
    }

    public Cherry generate_random_cherry(double pillars_gap) {
        double positionX;
        if (pillars_gap < Hero.DIMESION) {
            positionX = -100; // vherry is not shown on screen
            return new Cherry(positionX);
        }
        positionX = Math.random() * (pillars_gap - Cherry.DIMESION) + Engine.STAND_CLIFF_X;
        return new Cherry(positionX);
    }

}
