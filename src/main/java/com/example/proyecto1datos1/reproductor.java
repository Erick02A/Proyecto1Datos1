package com.example.proyecto1datos1;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;

public class reproductor {
    private File directory;
    private File[] files;
    private static listaSongs songs;
    private static Media media;
    private static MediaPlayer mediaPlayer;
    private static Songs song;
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
    public static void playpause(boolean a){
        if (a==false) {
            mediaPlayer.play();
        }else {
            mediaPlayer.pause();
        }
    }
    public static void previus(){
        if (song.getPrev() != null) {
            song = song.getPrev();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            playpause(false);
        }else {
            mediaPlayer.seek(Duration.seconds(0));
        }
    }
    public static void next(){
        if (song.getNext() != null) {
            song = song.getNext();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            playpause(false);
        }
    }
    public static void setVolumen(double vol){
        mediaPlayer.setVolume(vol);
    }
    public static void Bucle(boolean bucle){
        songs.bucle(bucle);
    }
    public static void like(boolean Like){

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
