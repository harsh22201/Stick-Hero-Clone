package com.javafx.game;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Effects {
    public static void Bounce(Node node) {
        // Create a ScaleTransition for the node (enlarging effect)
        ScaleTransition scaleEnlarge = new ScaleTransition(Duration.seconds(0.1), node);
        scaleEnlarge.setFromX(1.0);
        scaleEnlarge.setToX(1.2);
        scaleEnlarge.setFromY(1.0);
        scaleEnlarge.setToY(1.2);

        // Create a ScaleTransition for the node (returning to original size)
        ScaleTransition scaleOriginal = new ScaleTransition(Duration.seconds(0.1), node);
        scaleOriginal.setFromX(1.2);
        scaleOriginal.setToX(1.0);
        scaleOriginal.setFromY(1.2);
        scaleOriginal.setToY(1.0);

        // Create a ParallelTransition to combine both transitions
        ParallelTransition parallelTransition = new ParallelTransition(scaleEnlarge, scaleOriginal);

        // Play the parallel transition
        parallelTransition.play();
    }
}
