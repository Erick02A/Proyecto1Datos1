package com.example.proyecto1datos1;

import java.io.File;

public class listaSongs {
    private Songs cabeza;
    private Songs last;
    private int size;
    private boolean bucle;

    public listaSongs(){
        this.cabeza = null;
        this.last = null;
        this.size = 0;
        this.bucle = false;
    }
    public void addsongfirst(String cancion, String genero, String artista, String album, String año, String letra, File data){
        if (cabeza==null){
            cabeza = new Songs(cancion, genero, artista, album, año, letra, data);
            last = cabeza;
        }else {
            Songs Temp = cabeza;
            Songs nuevo = new Songs(cancion, genero, artista, album, año, letra, data);
            nuevo.setNext(Temp);
            Temp.setPrev(nuevo);
            cabeza = nuevo;
        }
        size++;
    }
    public void addsonglast(String cancion, String genero, String artista, String album, String año, String letra, File data){
        if (cabeza==null){
            cabeza = new Songs(cancion, genero, artista, album, año, letra, data);
            last = cabeza;
        }else {
            Songs nuevo = new Songs(cancion, genero,artista, album, año, letra, data);
            last.setNext(nuevo);
            nuevo.setPrev(last);
            last = nuevo;
        }
        size++;
    }
    public void eliminar(Songs song){
        if (bucle==true){
            if (song == cabeza){
                cabeza = cabeza.getNext();
            }else if(song == last){
                last = last.getPrev();
            }
            song.getPrev().setNext(song.getNext());
            song.getNext().setPrev(song.getPrev());
        }
        else {
            if(song == cabeza ){
                cabeza = cabeza.getNext();
                song.getNext().setPrev(null);
            }else if(song == last){
                last = last.getPrev();
                song.getPrev().setNext(null);
            }else {
                song.getPrev().setNext(song.getNext());
                song.getNext().setPrev(song.getPrev());
            }
        }
    }
    public int getSize(){
        return size;
    }
    public boolean estaVacio(){
        return (cabeza==null)?true:false;
    }
    public void bucle(Boolean a){
        if(a==false) {
            last.setNext(cabeza);
            cabeza.setPrev(last);
            bucle = true;
        }else {
            last.setNext(null);
            cabeza.setPrev(null);
            bucle = false;
        }
    }
    public String obtener(int index){
        int contador = 0;
        Songs temporal = cabeza;
        while (contador<index){
            temporal=temporal.getNext();
            contador++;
        }
        return temporal.getCancion();
    }
    public Songs getCabeza(){
        return cabeza;
    }
}
