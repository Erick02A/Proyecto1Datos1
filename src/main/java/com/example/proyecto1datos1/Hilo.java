package com.example.proyecto1datos1;

public class Hilo extends  Thread{
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
