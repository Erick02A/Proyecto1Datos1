package com.example.proyecto1datos1;

import java.io.*;

/**
 * Nodo de canciones en las que se instancia cada una.
 */
public class Songs {
    private String cancion;
    private File data;
    private Songs next;
    private Songs prev;

    /**
     * Inicializa lel nodo con sus variables.
     * @param cancion Nobre de la cancion que se le ingresa al crearlo.
     * @param data Objeto tipo File que trae la cancion de archivos.
     */
    public Songs(String cancion, File data) {
        this.cancion = cancion;
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Retorna el nodo anterior a este.
     * @return Nodo Songs
     */
    public Songs getPrev() {
        return prev;
    }

    /**
     * Cambia cual nodo va entes de este.
     * @param prev Nodo Songs
     */
    public void setPrev(Songs prev) {
        this.prev = prev;
    }

    /**
     * Retorna el archivo media de la cancion.
     * @return File data media.
     */
    public File getdata() {
        return data;
    }

    /**
     * Retorna el nombrfe de la cancion del nodo.
     * @return String nombre de la cancion.
     */
    public String getCancion() {
        return cancion;
    }

    /**
     * Cambia el nodo que va despues de este
     * @param songs Nodo Songs
     */
    public void setNext(Songs songs){
        next = songs;
    }

    /**
     * Retorna el nodo que va despues de este.
     * @return Nodo Songs
     */
    public Songs getNext(){
        return next;
    }
}
