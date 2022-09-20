package com.example.proyecto1datos1;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
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
    private File directory;
    private File[] files;
    private listaSongs songs;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean play;
    private boolean bucle;
    private boolean like;
    private Songs song;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = new listaSongs();
        directory = new File("music");
        files = directory.listFiles();
        if (files != null){
            for(File file: files){
                songs.addsonglast(file.toString(),"genero","artista","album","2001","letra",file);
                System.out.println(file);
            }
            volumenbar.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    System.out.println(volumenbar.getValue());
                    //mediaPlayer.setVolume(volumenbar.getValue());
                }
            });
        }
        song = songs.getCabeza();
        System.out.println(song.getdata().toURI().toString());
        media= new Media(song.getdata().toURI().toString());
        mediaPlayer= new MediaPlayer(media);
    }
    public void PlayPause(){
        if(play==false){
            mediaPlayer.play();
            pauseButton.setText("⏸");
            play=true;
        }else {
            //mediaPlayer.pause();
            pauseButton.setText("▶");
            play=false;
        }
    }
    public void previusSong(){
        if (song.getPrev() != null) {
            song = song.getPrev();
            System.out.println(song.getdata().toURI().toString());
            media = new Media(song.getdata().toURI().toString());
            //mediaPlayer= new MediaPlayer(media);
            play=false;
            PlayPause();
        }else {
            System.out.println(song.getdata().toURI().toString());
            //mediaPlayer.seek(Duration.seconds(0));
        }
    }
    public void NextSong(){
        if (song.getNext() != null) {
            song = song.getNext();
            System.out.println(song.getdata().toURI().toString());
            media = new Media(song.getdata().toURI().toString());
            //mediaPlayer= new MediaPlayer(media);
            play=false;
            PlayPause();
        }else {
            System.out.println(song.getdata().toURI().toString());
        }
    }
    public void listBucle(){
        if (bucle==false) {
            BucleButton.setText("🔁");
            songs.bucle(bucle);
            bucle= true;
        }else {
            BucleButton.setText("🔀");
            songs.bucle(bucle);
            bucle= false;
        }
    }
    public void LikeSong(){
        if(like==false) {
            System.out.println(song.getCancion()+" me gusta");
            LikeButton.setTextFill(Paint.valueOf("#e70606"));
            like= true;
        }else {
            System.out.println(song.getCancion()+" no me gusta");
            LikeButton.setTextFill(Paint.valueOf("#000000"));
            like= false;
        }
    }
    public void setBucle(boolean B){
        bucle=B;
    }
    public void setLike(boolean D){
        like=D;
    }
    public  void setPlay(boolean F){
        play=F;
    }
}
