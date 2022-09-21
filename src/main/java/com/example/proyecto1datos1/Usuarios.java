package com.example.proyecto1datos1;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Usuarios{


    public static void Datos(String Contraseña, String Correo, Label error) throws IOException {
        String linea, Name, correo, contraseña, Provincia;

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\andre\\Desktop\\java\\Proyecto1Datos1\\src\\main\\java\\com\\example\\proyecto1datos1\\Users.csv"));
            //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Erick\\Desktop\\Datos I\\Proyecto1Datos1\\src\\main\\java\\com\\example\\proyecto1datos1\\Users.csv"));
            linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Name = datos[0];
                correo = datos[1];
                Provincia = datos[2];
                contraseña = datos[3];
                if ( Contraseña.equals(contraseña) && Correo.equals(correo)) {
                    MusicPro m = new MusicPro();
                    m.CambiarPantalla("repro.fxml");



                }else if (Contraseña.isEmpty() | Correo.isEmpty()) {
                    error.setText("Verifique que todos los espacios esten llenos");

                }else{error.setText("Usuario incorrecto");}
            }



        } catch (IOException est) {
           System.out.println("error");
            throw new RuntimeException(est);

        }
    }
}
