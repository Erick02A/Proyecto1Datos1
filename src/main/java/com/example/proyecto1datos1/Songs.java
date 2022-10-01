package com.example.proyecto1datos1;

import java.io.*;

/**
 * Nodo de canciones en las que se instancia cada una
 */
public class Songs {
    private String cancion;
    private File data;
    private Songs next;
    private Songs prev;

    /**
     *
     * @param cancion
     * @param data
     */
    public Songs(String cancion, File data) {
        this.cancion = cancion;
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     *
     * @return
     */
    public Songs getPrev() {
        return prev;
    }

    /**
     *
     * @param prev
     */
    public void setPrev(Songs prev) {
        this.prev = prev;
    }

    /**
     *
     * @return
     */
    public File getdata() {
        return data;
    }

    /**
     *
     * @return
     */
    public String getCancion() {
        return cancion;
    }

    /**
     *
     * @param songs
     */
    public void setNext(Songs songs){
        next = songs;
    }

    /**
     *
     * @return
     */
    public Songs getNext(){
        return next;
    }
}
