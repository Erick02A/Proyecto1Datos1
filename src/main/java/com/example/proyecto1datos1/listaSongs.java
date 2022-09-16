package com.example.proyecto1datos1;

public class listaSongs {
    private Songs cabeza;
    private Songs last;

    public listaSongs(Songs song,Songs last){
        cabeza = null;
        last = null;
    }
    public void addsong(String cancion, String genero, String artista, String album, String año, String letra, String pad){
        if (cabeza==null){
            cabeza = new Songs(cancion, genero, artista, album, año, letra, pad);
            last = cabeza;
        }else {
            Songs Temp = cabeza;
            Songs nuevo = new Songs(cancion, genero, artista, album, año, letra, pad);
            nuevo.setNext(Temp);
            Temp.setPrev(nuevo);
            cabeza = nuevo;
        }
    }
    public boolean estaVacio(){
        return (cabeza==null)?true:false;
    }
    public void bucle(Boolean a){
        if(a==false) {
            last.setNext(cabeza);
            cabeza.setPrev(last);
        }else {
            last.setNext(null);
            cabeza.setPrev(null);
        }
    }

}
