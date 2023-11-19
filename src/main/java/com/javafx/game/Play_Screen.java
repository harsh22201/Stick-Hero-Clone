package com.javafx.game;

import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.File;

public class Play_Screen {

    @FXML
    private Button pause_button;
    @FXML
    private ImageView pause_button_imageview;
    @FXML
    private ColorAdjust pause_button_coloradjust;

    // all functions for pause button
    @FXML
    private void pause_button_enter() {
        pause_button_coloradjust.setBrightness(-0.2);
    }

    @FXML
    private void pause_button_exit() {
        pause_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void pause_button_click() {
        Sound.click();
        Stage primaryStage = (Stage) pause_button.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Pause_Screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 750);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Text score_counter;;
    @FXML
    private Button score_button;
    private int score_count = 0;

    @FXML
    private void score_button_click() {
        score_count++;
        score_counter.setText(Integer.toString(score_count));
        Effects.Bounce(score_counter);
    }

    // make cherry counter
    @FXML
    private ImageView cherry_image_view;
    @FXML
    private Text cherry_counter;
    @FXML
    private Button cherry_button;
    private int cherry_count = 0;

    @FXML
    private void cherry_button_click() {
        cherry_count++;
        cherry_counter.setText(Integer.toString(cherry_count));
        Effects.Bounce(cherry_image_view);
    }

    @FXML
    void onMousePressed() {
        Sound.stick_grow();
    }

    @FXML
    void onMouseReleased() {
        Sound.stick_grow_stop();
    }

    @FXML
    private void initialize() {
    }
}
