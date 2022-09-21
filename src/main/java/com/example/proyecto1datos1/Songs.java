package com.example.proyecto1datos1;

import java.io.File;

public class Songs {
    private String cancion;
    private String genero;
    private String artista;
    private String album;
    private String año;
    private String letra;
    private File data;
    private Songs next;
    private static Songs prev;

    public Songs(String cancion, String genero, String artista, String album, String año, String letra, File data) {
        this.cancion = cancion;
        this.genero = genero;
        this.artista = artista;
        this.album = album;
        this.año = año;
        this.letra = letra;
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    public static Songs getPrev() {
        return prev;
    }

    public void setPrev(Songs prev) {
        this.prev = prev;
    }

    public void setPad(File data) {
        this.data = data;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public File getdata() {
        return data;
    }

    public String getLetra() {
        return letra;
    }

    public String getAño() {
        return año;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public String getCancion() {
        return cancion;
    }

    public void setNext(Songs songs){
        next = songs;
    }
    public Songs getNext(){
        return next;
    }
}
