package com.javafx.game;

import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class Home_Screen {

    @FXML
    private Pane scene_pane;
    @FXML
    private Button play_button;
    @FXML
    private ImageView play_button_imageview;
    @FXML
    private ColorAdjust play_button_coloradjust;

    @FXML
    private void play_button_enter() {
        play_button_coloradjust.setBrightness(0.1);
    }

    @FXML
    private void play_button_exit() {
        play_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void play_button_click() {
        Sound.click();
        Screen_Loader.play();
    }

    @FXML
    Button load_button;

    @FXML
    ImageView load_button_imageview;

    @FXML
    ColorAdjust load_button_coloradjust;

    @FXML
    private void load_button_enter() {
        load_button_coloradjust.setBrightness(0.3);
    }

    @FXML
    private void load_button_exit() {
        load_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void load_button_click() {
        Sound.click();
        System.err.println("Load button clicked");
    }

    @FXML
    Button volume_button;

    @FXML
    ImageView volume_button_imageview;

    @FXML
    ColorAdjust volume_button_coloradjust;

    @FXML
    private void volume_button_enter() {
        volume_button_coloradjust.setBrightness(0.3);
    }

    @FXML
    private void volume_button_exit() {
        volume_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void volume_button_click() {
        Sound.click();
        System.err.println("Volume button clicked");
        if (Sound.isMuted) {
            Sound.unmute();
            volume_button_imageview
                    .setImage(new Image(getClass().getResource("buttons/VOLUME_ON.png").toExternalForm()));
        } else {
            Sound.mute();
            volume_button_imageview
                    .setImage(new Image(getClass().getResource("buttons/VOLUME_OFF.png").toExternalForm()));
        }
    }

    private void play_button_move() {// Creating a TranslateTransition for thebutton
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), play_button);
        translateTransition.setByY(-15); // Set the distance to move up and down
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE); // Set indefinite cycle
        translateTransition.setAutoReverse(true); // Move back and forth
        translateTransition.play();
    }

    @FXML
    private ImageView idle_hero_imageview;

    private void idle_hero_animate() {
        String[] framespath = new String[14];
        for (int i = 0; i < 14; i++) {
            String framepath = "animations/Idle/idle" + (i + 1) + ".png";
            framespath[i] = framepath;
        }
        Timeline timeline = new Timeline();
        for (int i = 0; i < 14; i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.07 * (i + 1)), e -> {
                String imagePath = framespath[index];
                // Remove the "@" symbol when creating the Image object
                Image image = new Image(getClass().getResource(imagePath).toExternalForm());
                idle_hero_imageview.setImage(image);
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        // Make the timeline loop indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void initialize() {
        play_button_move();
        idle_hero_animate();
    }
}