package com.example.proyecto1datos1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Hilo extends  Thread{

    public void run() {
        while (true) {
            try {
                Arduino.Arduino();
                //Usuarios.Datos(Contraseña.getText(),Correo.getText(),Error);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
