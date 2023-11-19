package com.javafx.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {

    private static final double SCREEN_WIDTH = 400;
    private static final double SCREEN_HEIGHT = 750;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Home_Screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 750);
            primaryStage.setTitle("Stick Hero");
            primaryStage.setScene(scene);
            primaryStage.show();
            Sound.rain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}