package com.example.proyecto1datos1;

public class Songs {
    private String cancion;
    private String genero;
    private String artista;
    private String album;
    private String año;
    private String letra;
    private String pad;
    private Songs next;
    private Songs prev;

    public Songs(String cancion, String genero, String artista, String album, String año, String letra, String pad) {
        this.cancion = cancion;
        this.genero = genero;
        this.artista = artista;
        this.album = album;
        this.año = año;
        this.letra = letra;
        this.pad = pad;
        this.next = null;
        this.prev = null;
    }
    public Songs getPrev() {
        return prev;
    }

    public void setPrev(Songs prev) {
        this.prev = prev;
    }

    public void setPad(String pad) {
        this.pad = pad;
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

    public String getPad() {
        return pad;
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
