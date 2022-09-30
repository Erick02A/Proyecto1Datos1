package com.example.proyecto1datos1;

import java.io.File;

/**
 * listaSongs se encarga de crear listas doblemente enlasadas con objetos tipo Songs.
 */
public class listaSongs {
    private Songs cabeza;
    private Songs last;
    private int size;
    private boolean bucle;

    /**
     * Crea la lista inicial con cabeza y last nulos, zice en 0  y si es circular en false.
     */
    public listaSongs(){
        this.cabeza = null;
        this.last = null;
        this.size = 0;
        this.bucle = false;
    }

    /**
     * Añade un objeto de tipo Songs al principio de la lista, resiviendo como parametros los atributos que va a tener el objeto Songs.
     * @param cancion
     * @param genero
     * @param artista
     * @param album
     * @param año
     * @param letra
     * @param data
     */
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

    /**
     * Añade un objeto de tipo Songs al final de la lista, resiviendo como parametros los atributos que va a tener el objeto Songs.
     * @param cancion
     * @param genero
     * @param artista
     * @param album
     * @param año
     * @param letra
     * @param data
     */
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

    /**
     * Esta elimina un objeto tipo Songs de la lista enlasada de Songs.
     * @param song Objeto tipo Songs que se busca eliminar.
     */
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
    /**
     * Esta pregunta si la lista se encuentra vacía.
     * @return boolean que dice si es vacia.
     */
    public boolean estaVacio(){
        return (cabeza==null)?true:false;
    }
    /**
     * Esta hace que la lista se vuelva circular o hace que deje de serlo.
     * @param a boolean que dice si la lista ya era circular.
     */
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

    /**
     * Esta retorna la cabeza de la lista
     * @return Objeto tipo Songs, cabeza de la lista.
     */
    public Songs getCabeza(){
        return cabeza;
    }
}
