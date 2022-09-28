package com.example.proyecto1datos1;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class reproControler implements Initializable {
    @FXML
    private Button pauseButton;
    @FXML
    private Button PreviusButton;
    @FXML
    private Button NextButton;
    @FXML
    private Button BucleButton;
    @FXML
    private Button LikeButton;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonAdd;
    @FXML
    private Slider volumenbar;
    @FXML
    private ProgressBar SongProgresbar;
    @FXML
    private ComboBox<String> BiblioBox;
    private String[] Biblios = {"Biblioteca01","Biblioteca02"};
    private reproductor repro;
    private Timer timer;
    private TimerTask task;
    private boolean play;
    private boolean bucle;
    private static boolean like;
    private double volumen;
    private boolean runing;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repro = new reproductor("Biblioteca01");
        Hilo hilo = new Hilo();
        hilo.start();
        volumenbar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                volumen=volumenbar.getValue();
                repro.setVolumen(volumenbar.getValue());
            }
        });
        for (int i = 0;i <Biblios.length;i++){
            BiblioBox.getItems().add(Biblios[i]);
        }
    }
    public void PlayPause(){
        if(play==false){
            repro.playpause(play);
            pauseButton.setText("⏸");
            beginTimer();
            play=true;
        }else {
            repro.playpause(play);
            pauseButton.setText("▶");
            cancelTimer();
            play=false;
        }
    }
    public void previusSong(){
        repro.previus(false);
        repro.setVolumen(volumen);
        play = false;
        PlayPause();
    }
    public void NextSong(){
        repro.next(false);
        repro.setVolumen(volumen);
        play = false;
        PlayPause();
    }
    public void listBucle(){
        if (bucle==false) {
            BucleButton.setText("🔁");
            repro.Bucle(bucle);
            bucle= true;
        }else {
            BucleButton.setText("🔀");
            repro.Bucle(bucle);
            bucle= false;
        }
    }
    public void LikeSong(){
        if(like==false) {
            repro.like(like);
            LikeButton.setTextFill(Paint.valueOf("#e70606"));
            like= true;
        }else {
            repro.like(like);
            LikeButton.setTextFill(Paint.valueOf("#000000"));
            like= false;
        }
    }
    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runing = true;
                double current = repro.getCurrenttime();
                double end = repro.getDuration();
                SongProgresbar.setProgress(current/end);

                if (current/end == 1){
                    cancelTimer();
                    NextSong();
                }
            }
        };
        timer.scheduleAtFixedRate(task,1000,1000);
    }
    public void cancelTimer(){
        runing=false;
        timer.cancel();
    }
    public void Delete(){
        repro.delete();
    }
    public void Add(){
        repro.ADD();
    }
    public void changeBiblio(ActionEvent event){
        repro = new reproductor(BiblioBox.getValue());
    }
    public void setBucle(boolean B){
        bucle=B;
    }
    public static void setLike(boolean D){
        like=D;
    }
    public  void setPlay(boolean F){
        play=F;
    }
}
