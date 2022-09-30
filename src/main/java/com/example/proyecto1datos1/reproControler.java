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

import java.io.*;
import java.net.URL;
import java.util.List;
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
    private Button addBiblio;
    @FXML
    private Slider volumenbar;
    @FXML
    private ProgressBar SongProgresbar;
    @FXML
    private ComboBox<String> BiblioBox;
    private String[] Biblios = new String[200];
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
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/proyecto1datos1/"+repro.getActivo()+".csv"));
            String line ="0";
            String biblios="";
            while ((line=br.readLine())!=null){
                String[] datos=line.split(";");
                biblios+=datos[0]+";";
            }
            Biblios = biblios.split(";");
            System.out.println(biblios);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        BiblioBox.setOnAction(this::changeBiblio);
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
    public void LikeSong() throws IOException {
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
    public void AddBiblio() throws IOException {
        String[] nuevo = new String[Biblios.length+1];
        int i=0;
        for (String biblio: Biblios){
            nuevo[i] = biblio;
            i++;
        }
        int f = i+1;
        if (f<10){
            nuevo[i]="Biblioteca0"+f;
        }else {
            nuevo[i]="Biblioteca"+f;
        }
        Biblios = nuevo;
        for (int e = 0;e <Biblios.length;e++){
            if (e <= BiblioBox.getItems().size()-1) {
                BiblioBox.getItems().set(e, Biblios[e]);
            }else {
                BiblioBox.getItems().add(Biblios[e]);
            }
        }
        actualizaBiblio(Biblios[i]);
    }
    public void actualizaBiblio(String nuevo) throws IOException {
        BufferedReader BR = new BufferedReader(new FileReader("src/main/java/com/example/proyecto1datos1/"+repro.getActivo()+".csv"));
        String linea;
        String lineas = "";
        while ((linea = BR.readLine())!=null){
            lineas += "\n"+ linea;
        }
        BR.close();
        System.out.println(lineas+"\n"+nuevo);
        BufferedWriter BW = new BufferedWriter(new FileWriter("src/main/java/com/example/proyecto1datos1/"+repro.getActivo()+".csv"));
        PrintWriter PW = new PrintWriter(BW);
        //PW.println(lineas+"\n"+nuevo);
        PW.write(lineas+"\n"+nuevo);
        BW.close();
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
    public  void setPlay(boolean F) {
        play = F;
    }
}
