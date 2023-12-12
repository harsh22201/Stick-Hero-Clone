package com.javafx.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Pause_Screen {

    Play_Screen play_screen;
    @FXML
    private Text score_count;
    @FXML
    private Text best_score_count;
    // home button fxml
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

    // resume button fxml
    @FXML
    private Button resume_button;
    @FXML
    private ImageView resume_button_imageview;
    @FXML
    private ColorAdjust resume_button_coloradjust;

    // all functions for resume button
    @FXML
    private void resume_button_enter() {
        resume_button_coloradjust.setBrightness(0.5);
    }

    @FXML
    private void resume_button_exit() {
        resume_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void resume_button_click() {
        Sound.click();
        Screen_Loader.set_screen(play_screen.pane.getScene());
        play_screen.engine.resume();
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

    // save button fxml
    @FXML
    private Button save_button;
    @FXML
    private ImageView save_button_imageview;
    @FXML
    private ColorAdjust save_button_coloradjust;

    // all functions for save button

    @FXML
    private void save_button_enter() {
        save_button_coloradjust.setBrightness(-0.2);
    }

    @FXML
    private void save_button_exit() {
        save_button_coloradjust.setBrightness(0);
    }

    @FXML
    private void save_button_click() {
        Sound.click();
        Engine engine = play_screen.engine;
        GameState game_state = new GameState(engine.score_count, engine.reach_pillar.pillar.getX(),
                engine.reach_pillar.pillar.getWidth(), engine.cherry.imageview.getX());
        game_state.save();
        System.out.println("Save button clicked");
    }

    @FXML
    private void initialize() {
    }

    public void custom_init(int cur_score_, Play_Screen play_screen) {
        this.score_count.setText(Integer.toString(cur_score_));
        this.best_score_count.setText(Integer.toString(Score.get_best_score()));
        this.play_screen = play_screen;
    }

}
