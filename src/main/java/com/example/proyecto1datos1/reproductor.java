package com.example.proyecto1datos1;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.util.Objects;

public class reproductor {
    private File directory;
    private File[] files;
    private static listaSongs songs;
    private static Media media;
    private static MediaPlayer mediaPlayer;
    private static Songs song;
    private static String activo;
    private String biblioteca;
    private String Biblioteca;
    public reproductor(String biblio){
        biblioteca = biblio;
        songs = new listaSongs();
        directory = new File("music");
        files = directory.listFiles();
        activo = "";
        try {
            BufferedReader BR = new BufferedReader(new FileReader("Ususario/activo.txt"));
            activo= BR.readLine();
            BufferedReader lista = new BufferedReader(new FileReader("Ususario/"+activo+"/Biblio.csv"));
            String line="";
            while ((line=lista.readLine())!=null){
                String[] biblios=line.split(";");
                if (Objects.equals(biblios[0], biblioteca)){
                    Biblioteca = line;
                    System.out.println(Biblioteca);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (files != null){
            for(File file: files){
                for (String s:Biblioteca.split(";")) {
                    if(s.equals(file.getName())) {
                        songs.addsonglast(file.getName(),"genero","artista","album","2001","letra",file);
                    }
                }
            }
        }
        song = songs.getCabeza();
        if(song!=null) {
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        }
    }
    public static void playpause(boolean a){
        if (a==false) {
            mediaPlayer.play();
        }else {
            mediaPlayer.pause();
        }
    }
    public static void previus(boolean ardu){
        if (song.getPrev() != null) {
            song = song.getPrev();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            if (ardu == true){
                playpause(false);
            }
        }else {
            mediaPlayer.seek(Duration.seconds(0));
        }
    }
    public static void next(boolean ardu){
        if (song.getNext() != null) {
            song = song.getNext();
            mediaPlayer.stop();
            media = new Media(song.getdata().toURI().toString());
            mediaPlayer= new MediaPlayer(media);
            if (ardu == true){
                playpause(false);
            }
        }
    }
    public static void setVolumen(double vol){
        if (mediaPlayer!=null) {
            mediaPlayer.setVolume(vol);
        }
    }
    public static void Bucle(boolean bucle){
        songs.bucle(bucle);
    }
    public static void like(boolean Like) throws IOException {
        BufferedReader Br = new BufferedReader(new FileReader("Usuario/"+activo+"/Likelist.csv"));
        String line="";
        String res="";
        if (Like==true){
            while ((line=Br.readLine())!=null){
                if (line.equals(song.getCancion())){
                    res+="";
                }else {
                    res+=line+"\n";
                }
            }
        }else {
            while ((line=Br.readLine())!=null){
                res+=line+"\n";
            }
            res+=song.getCancion();
        }
        Br.close();
        System.out.println(res);
        BufferedWriter Bw = new BufferedWriter(new FileWriter("Usuario/"+activo+"/Likelist.csv"));
        PrintWriter Pw = new PrintWriter(Bw);
        Pw.write(res);
        Bw.close();
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
    public void stop(){
        mediaPlayer.stop();
    }
    public void ADD(){
        FileChooser F = new FileChooser();
        File file = F.showOpenDialog(null);
        songs.addsongfirst(file.getName(),"genero","artista","album","2001","letra",file);
    }
    public String getActivo(){
        return activo;
    }
}
