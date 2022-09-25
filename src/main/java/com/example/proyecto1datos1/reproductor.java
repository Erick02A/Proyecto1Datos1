package com.example.proyecto1datos1;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class reproductor {
    private File directory;
    private File[] files;
    private listaSongs songs;
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean like;
    private boolean runing;
    private Songs song;
    private String Dato;
    public reproductor(){
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
        }
        song = songs.getCabeza();
        System.out.println(song.getdata().toURI().toString());
        media= new Media(song.getdata().toURI().toString());
        mediaPlayer= new MediaPlayer(media);
    }
    public void pausa(){
        mediaPlayer.pause();
    }
    public void play(){
        mediaPlayer.play();
    }
    public void previus(){
        if (song.getPrev() != null) {
            song = song.getPrev();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
        }else {
            mediaPlayer.seek(Duration.seconds(0));
        }
    }
    public void next(){
        if (song.getNext() != null) {
            song = song.getNext();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
        }
    }
    public void setVolumen(double vol){
        mediaPlayer.setVolume(vol);
    }
    public void Bucle(boolean bucle){
        songs.bucle(bucle);
    }
    public void like(){

    }
    public double getCurrenttime(){
       return mediaPlayer.getCurrentTime().toSeconds();
    }
    public double getDuration(){
        return media.getDuration().toSeconds();
    }
    public void delete(){
        songs.eliminar(song);
    }
    public void ADD(){
        FileChooser F = new FileChooser();
        File file = F.showOpenDialog(null);
        songs.addsongfirst(file.getName(),"genero","artista","album","2001","letra",file);
    }
}
