package com.example.proyecto1datos1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.FileReader;

public class Usuarios {
    @FXML
    private Button Login;
    @FXML
    private Label Error;
    @FXML
    private TextField Correo;
    @FXML
    private PasswordField Contraseña;

    public void Datos() {
        String linea, Name, correo, contraseña, Provincia;

        try {
            //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\andre\\Desktop\\java\\Proyecto1Datos1\\src\\main\\java\\com\\example\\proyecto1datos1\\Users.csv"));
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Erick\\Desktop\\Datos I\\Proyecto1Datos1\\src\\main\\java\\com\\example\\proyecto1datos1\\Users.csv"));
            linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Name = datos[0];
                correo = datos[1];
                Provincia = datos[2];
                contraseña = datos[3];
                if ( Contraseña.getText().equals(contraseña) && Correo.getText().equals(correo)) {
                    MusicPro m = new MusicPro();
                    m.CambiarPantalla("hello-view.fxml");



                }else if (Contraseña.getText().isEmpty() | Correo.getText().isEmpty()) {
                    Error.setText("Verifique que todos los espacios esten llenos");

                }else{Error.setText("Usuario incorrecto");}
            }



        } catch (IOException est) {
            System.out.println("error");
            throw new RuntimeException(est);

        }
    }
    public void user(ActionEvent event) throws IOException {

        Datos();
    }
}
