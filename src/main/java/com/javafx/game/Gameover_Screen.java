package com.javafx.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Gameover_Screen {

    @FXML
    private Button home_button;
    @FXML
    private ImageView home_button_imageview;
    @FXML
    private ColorAdjust home_button_coloradjust;

    // all functions for home button
    @FXML
    private void home_button_enter() {
        home_button_coloradjust.setBrightness(0.5);
    }

    @FXML
    private void home_button_exit() {
        home_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void home_button_click() {
        Sound.click();
        Stage primaryStage = (Stage) home_button.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Home_Screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 750);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // restart button fxml
    @FXML
    private Button restart_button;
    @FXML
    private ImageView restart_button_imageview;
    @FXML
    private ColorAdjust restart_button_coloradjust;

    // all functions for restart button
    @FXML
    private void restart_button_enter() {
        restart_button_coloradjust.setBrightness(0.5);
    }

    @FXML
    private void restart_button_exit() {
        restart_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void restart_button_click() {
        Sound.click();
        Stage primaryStage = (Stage) restart_button.getScene().getWindow();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Play_Screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 750);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
    }
}
