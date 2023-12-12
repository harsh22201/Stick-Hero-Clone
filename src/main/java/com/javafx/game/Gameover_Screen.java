package com.javafx.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Gameover_Screen {

    Play_Screen play_screen;
    @FXML
    private Text score_count;

    @FXML
    private Text best_score_count;

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
        Screen_Loader.home();
    }

    // revive button fxml

    @FXML
    private Button revive_button;
    @FXML
    private ImageView revive_button_imageview;
    @FXML
    private ColorAdjust revive_button_coloradjust;

    // all functions for revive button
    @FXML
    private void revive_button_enter() {
        revive_button_coloradjust.setBrightness(0.5);
    }

    @FXML
    private void revive_button_exit() {
        revive_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void revive_button_click() {
        Sound.click();
        if (Cherry.get_total_cherry() >= 10) {
            Cherry.set_total_cherry(Cherry.get_total_cherry() - 10);
            Screen_Loader.set_screen(play_screen.pane.getScene());
            play_screen.engine.revive();
        } else {
            System.out.println("Not enough cherry");
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
        Screen_Loader.play(null);
    }

    @FXML
    private void initialize() {
    }

    public void custom_init(int cur_score_, Play_Screen play_screen) {
        score_count.setText(Integer.toString(cur_score_));
        best_score_count.setText(Integer.toString(Score.get_best_score()));
        this.play_screen = play_screen;
    }
}
