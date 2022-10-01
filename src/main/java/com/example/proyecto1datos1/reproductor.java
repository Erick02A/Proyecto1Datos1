package com.example.proyecto1datos1;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.util.Objects;

/**
 * Clase de reproductor en la que se lleva la logica de crear las listas y reproducirlas.
 */
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

    /**
     * Inicializa el reproductor con las cosas que trae por defecto, crea la lista de reproduccion segun la biblioteca en la que se encuentre.
     * @param biblio Biblioteca de la cuel sacar las caciones a agregar a la lista.
     */
    public reproductor(String biblio){
        biblioteca = biblio;
        songs = new listaSongs();
        directory = new File("music");
        files = directory.listFiles();
        activo = "";
        try {
            BufferedReader BR = new BufferedReader(new FileReader("Usuario/activo.txt"));
            activo= BR.readLine();
            BufferedReader lista = new BufferedReader(new FileReader("Usuario/"+activo+"/Biblio.csv"));
            String line="";
            while ((line=lista.readLine())!=null){
                String[] biblios=line.split(";");
                if (Objects.equals(biblios[0], biblioteca)){
                    Biblioteca = line;
                    //System.out.println(Biblioteca);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (files != null){
            for(File file: files){
                for (String s:Biblioteca.split(";")) {
                    if(s.equals(file.getName())) {
                        songs.addsonglast(file.getName(),file);
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

    /**
     * se encarga de dar pausa o play segun corresponda.
     * @param a
     */
    public static void playpause(boolean a){
        if (a==false) {
            mediaPlayer.play();
        }else {
            mediaPlayer.pause();
        }
    }

    /**
     * retrosede a la anterior cancion en la lista de reproduccion.
     * @param ardu boolean que dice si la instruccion viene del arduino.
     */
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
    /**
     * Avanza a la sigiente cancion en la lista de reproduccion.
     * @param ardu boolean que dice si la instruccion viene del arduino.
     */
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

    /**
     * cambia el volumen del reproductor.
     * @param vol entero que le indica a que valor cambiar.
     */
    public static void setVolumen(double vol){
        if (mediaPlayer!=null) {
            mediaPlayer.setVolume(vol);
        }
    }

    /**
     * Le dice a la lista que se vuelva o deje de ser circular segun corresponda.
     * @param bucle boolean que le indica si la lista era circular o no.
     */
    public static void Bucle(boolean bucle){
        songs.bucle(bucle);
    }

    /**
     * Se encarga de editar el archivo de likes, eliminando o agrgando la cancion segun corresponda.
     * @param Like boolean que dice si ya estaba o no en likes.
     * @throws IOException
     */
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
            System.out.println(song.getCancion());
            res+=song.getCancion();
        }
        Br.close();
        BufferedWriter Bw = new BufferedWriter(new FileWriter("Usuario/"+activo+"/Likelist.csv"));
        PrintWriter Pw = new PrintWriter(Bw);
        Pw.write(res);
        Bw.close();
    }

    /**
     * se encarga de retornar cuanto tiempo lleva reproduciendose la cancion actual.
     * @return ese tiempo en segundos.
     */
    public double getCurrenttime(){
       return mediaPlayer.getCurrentTime().toSeconds();
    }

    /**
     * Retorna cuanto dura la cancion actual.
     * @return ese tienpo en segundos.
     */
    public double getDuration(){
        return media.getDuration().toSeconds();
    }

    /**
     * Le dice a la lista de canciones que elimine la cancion en la que se encuentra en el momento.
     */
    public void delete() throws IOException {
        songs.eliminar(song);
        BufferedReader br = new BufferedReader(new FileReader("Usuario/"+activo+"/Biblio.csv"));
        String linea = "";
        String res="";
        while ((linea=br.readLine())!=null){
            String[] datos = linea.split(";");
            if (datos[0].equals(biblioteca)){
                for (int i = 0; i<datos.length;i++){
                    if (!datos[i].equals(song.getCancion())){
                        if (i==datos.length-1){
                            res+=datos[i];
                        }else {
                            res+=datos[i]+";";
                        }
                    }
                }
                res+="\n";
            }else {
                res+=linea+"\n";
            }
        }
        br.close();
        BufferedWriter bw = new  BufferedWriter(new FileWriter("Usuario/"+activo+"/Biblio.csv"));
        PrintWriter pw = new PrintWriter(bw);
        pw.write(res);
        bw.close();
    }
    /**
     * Detiene por completo el reproductor.
     */
    public void stop(){
        mediaPlayer.stop();
    }

    /**
     * Retorna el nombre de la cancion que esta reproduciendo.
     * @return String con ese nombre.
     */
    public String getNemeSong(){
        return song.getCancion();
    }

    /**
     * Habre una funcion para buscar y agregar una cacion a la lista desde los archivos y actualiza el archivo con la lista de reproduccion.
     */
    public void ADD() throws IOException {
        FileChooser F = new FileChooser();
        File file = F.showOpenDialog(null);
        songs.addsonglast(file.getName(),file);
        BufferedReader br = new BufferedReader(new FileReader("Usuario/"+activo+"/Biblio.csv"));
        String linea = "";
        String res="";
        while ((linea=br.readLine())!=null){
            String[] datos = linea.split(";");
            if (datos[0].equals(biblioteca)){
                res+=linea+";"+file.getName()+"\n";
            }else {
                res+=linea+"\n";
            }
        }
        br.close();
        BufferedWriter bw = new  BufferedWriter(new FileWriter("Usuario/"+activo+"/Biblio.csv"));
        PrintWriter pw = new PrintWriter(bw);
        pw.write(res);
        bw.close();
    }

    /**
     * Retorna al usuario que se encuentra usando el programa.
     * @return String con el nombre del ususario.
     */
    public String getActivo(){
        return activo;
    }
}