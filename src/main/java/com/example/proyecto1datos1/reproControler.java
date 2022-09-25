package com.example.proyecto1datos1;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
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
    private String[] Biblios ;
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
    private boolean runing;
    private Songs song;
    private String Dato;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Hilo hilo = new Hilo();
        hilo.start();
        songs = new listaSongs();
        directory = new File("music");
        files = directory.listFiles();
        String activo = "";
        try {
            BufferedReader BR = new BufferedReader(new FileReader("activo.txt"));
            activo= BR.readLine();
            BufferedReader lista = new BufferedReader(new FileReader("src/main/java/com/example/proyecto1datos1/"+activo+".csv"));
            System.out.println(lista.readLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (files != null){
            for(File file: files){

                    songs.addsonglast(file.getName(),"genero","artista","album","2001","letra",file);
            }
            volumenbar.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    mediaPlayer.setVolume(volumenbar.getValue());
                }
            });
        }
        song = songs.getCabeza();
        System.out.println(song.getdata().toURI().toString());
        media= new Media(song.getdata().toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        /*try {
            while (Dato != null){Ardu();}

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
    public void Ardu() throws InterruptedException {
        Dato = Hilo.getData();
        System.out.println("si");
        if(Dato.equals("Play")){
            System.out.println("Saludos");
        }
    }
    public void PlayPause(){
        if(play==false){
            mediaPlayer.play();
            pauseButton.setText("⏸");
            beginTimer();
            play=true;
        }else {
            mediaPlayer.pause();
            pauseButton.setText("▶");
            cancelTimer();
            play=false;
        }
    }
    public void previusSong(){
        if (song.getPrev() != null) {
            song = song.getPrev();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            play=false;
            PlayPause();
        }else {
            mediaPlayer.seek(Duration.seconds(0));
            SongProgresbar.setProgress(0);
        }
    }
    public void NextSong(){
        if (song.getNext() != null) {
            song = song.getNext();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            play=false;
            PlayPause();
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
            LikeButton.setTextFill(Paint.valueOf("#e70606"));
            like= true;
        }else {
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
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                SongProgresbar.setProgress(current/end);

                if (current/end == 1){
                    cancelTimer();
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
        songs.eliminar(song);
    }
    public void Add(){
        FileChooser F = new FileChooser();
        File file = F.showOpenDialog(null);
        songs.addsongfirst(file.getName(),"genero","artista","album","2001","letra",file);
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
