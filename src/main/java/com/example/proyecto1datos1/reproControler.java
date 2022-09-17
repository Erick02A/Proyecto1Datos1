package com.example.proyecto1datos1;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class reproControler implements Initializable {
    @FXML
    private Button pauseButton, PreviusButton, NextButton, BucleButton,LikeButton;
    @FXML
    private Slider volumenbar;
    @FXML
    private ProgressBar SongProgresbar;
    private boolean play;
    private boolean bucle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play = false;
        bucle = false;
    }
    public void PlayPause(){
        System.out.println("play");
    }
    public void previusSong(){
        System.out.println("back");
    }
    public void NextSong(){
        System.out.println("next");
    }
    public void listBucle(){
        System.out.println("bucle");
    }
    public void LikeSong(){
        System.out.println("like");
    }
}
