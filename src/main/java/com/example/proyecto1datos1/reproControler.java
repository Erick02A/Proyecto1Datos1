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

/**
 * Controlador de la pantalla del reproductor, implementando Initializable.
 */
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

    /**
     * Inicializa los recusos de la ventana y las principales cosas que usa, como el reproductor y las bibliotecas del usuario.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repro = new reproductor("Biblioteca01");
        Hilo hilo = new Hilo();
        hilo.start();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Usuario/"+repro.getActivo()+"/Biblio.csv"));
            String line ="";
            String biblios ="";
            while ((line=br.readLine())!=null){
                String[] datos=line.split(";");
                biblios+=datos[0]+";";
            }
            br.close();
            Biblios = biblios.split(";");
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
        BiblioBox.setOnAction(event -> {
            try {
                changeBiblio(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Se encarga de mandar el comando de dar play o pausa al reproductor segun correspoda cuando el respectivo boton es precionado.
     */
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

    /**
     * Se encarga de mandar el comando de cancion anterior al reproductor cuando el respectivo boton es precionado.
     * @throws IOException
     */
    public void previusSong() throws IOException {
        repro.previus(false);
        repro.setVolumen(volumen);
        play = false;
        PlayPause();
        actualizaLike();
    }
    /**
     * Se encarga de mandar el comando de cancion siguiente al reproductor cuando el respectivo boton es precionado.
     * @throws IOException
     */
    public void NextSong() throws IOException {
        repro.next(false);
        repro.setVolumen(volumen);
        play = false;
        PlayPause();
        actualizaLike();
    }

    /**
     * Manda la intrucion para cambiar la lista en reproducion de circular a no circular y viceversa cuando su respectivo boton es precionado.
     */
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

    /**
     *
     * @throws IOException
     */
    public void actualizaLike() throws IOException {
        BufferedReader Br = new  BufferedReader(new FileReader("Usuario/"+repro.getActivo()+"/Likelist.csv"));
        String line = "";
        boolean S = false;
        while ((line=Br.readLine())!=null){
            if (line.equals(repro.getNemeSong())){
                S=true;
            }
        }
        if (S==true){
            like=true;
            LikeButton.setTextFill(Paint.valueOf("#e70606"));
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
                    try {
                        NextSong();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
        BufferedReader BR = new BufferedReader(new FileReader("Usuario/"+repro.getActivo()+"/Biblio.csv"));
        String linea;
        String lineas = "";
        while ((linea = BR.readLine())!=null){
            lineas += "\n"+ linea;
        }
        BR.close();
        System.out.println(lineas+"\n"+nuevo);
        BufferedWriter BW = new BufferedWriter(new FileWriter("Usuario/"+repro.getActivo()+"/Biblio.csv"));
        PrintWriter PW = new PrintWriter(BW);
        PW.write(lineas+"\n"+nuevo);
        BW.close();
    }
    public void changeBiblio(ActionEvent event) throws IOException {
        repro.stop();
        repro = new reproductor(BiblioBox.getValue());
        actualizaLike();
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
