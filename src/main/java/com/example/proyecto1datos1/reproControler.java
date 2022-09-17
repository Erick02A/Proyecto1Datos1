package com.example.proyecto1datos1;


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
        }
        //media = new Media(song.get(songNumber).toURI().toString());
        //mediaplayer = new MediaPlayer(media);
    }
    public void PlayPause(){
        if(play==false){
            //mediaplayer.play();
            System.out.println("play");
            play=true;
        }else {
            //mediaplayer.pause();
            System.out.println("pause");
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
        System.out.println("bucle");
    }
    public void LikeSong(){
        System.out.println("like");
    }
}
