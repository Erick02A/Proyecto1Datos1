package com.example.proyecto1datos1;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class reproControler implements Initializable {
    @FXML
    private Button pauseButton, PreviusButton, NextButton, BucleButton,LikeButton;
    @FXML
    private Slider volumenbar;
    @FXML
    private ProgressBar SongProgresbar;
    private Media media;
    private MediaPlayer mediaplayer;
    private File directory;
    private File[] files;
    private ArrayList<File> song;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean play;
    private boolean bucle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        song = new ArrayList<File>();
        directory = new File("music");
        files = directory.listFiles();
        if (files != null){
            for(File file: files){
                song.add(file);
                System.out.println(file);
            }
            volumenbar.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    System.out.println(volumenbar.getValue());
                }
            });
        }
        System.out.println(song.get(songNumber).toURI().toString());
        //media = new Media(song.get(songNumber).toURI().toString());
        //mediaplayer = new MediaPlayer(media);
    }
    public void PlayPause(){
        if(play==false){
            //mediaplayer.play();
            System.out.println("play");
            pauseButton.setText("⏸");
            play=true;
        }else {
            //mediaplayer.pause();
            System.out.println("pause");
            pauseButton.setText("▶");
            play=false;
        }
    }
    public void previusSong(){
        System.out.println("back");
    }
    public void NextSong(){
        System.out.println("next");
    }
    public void listBucle(){
        if (bucle==false) {
            System.out.println("bucle");
            BucleButton.setText("🔁");
            bucle= true;
        }else {
            System.out.println("No bucle");
            BucleButton.setText("🔀");
            bucle= false;
        }
    }
    public void LikeSong(){
        System.out.println("like");
    }
}
