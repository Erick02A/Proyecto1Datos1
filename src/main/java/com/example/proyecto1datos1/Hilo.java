package com.example.proyecto1datos1;

/**
 * Clase que crea un hilo
 */
public class Hilo extends  Thread{
    /**
     * Función para inicializar el hilo
     */
    public void run() {
        while (true) {
            try {
                Arduino.Arduino();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
