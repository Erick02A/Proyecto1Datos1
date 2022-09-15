package com.example.proyecto1datos1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Usuarios implements Initializable {
    @FXML
    private ChoiceBox<String> Provincias;
    @FXML
    private Button Login;
    @FXML
    private Label Error;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Correo;
    @FXML
    private PasswordField Contraseña;


    private String[] provincias = {"Alajuela", "San Jose", "Cartago", "Limon", "Puntarenas", "Heredia", "Guanacaste"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Provincias.getItems().addAll(provincias);
        Provincias.setOnAction(this::getProvincia);
    }

    public void getProvincia(ActionEvent event) {
        String provincia = Provincias.getValue();
        //System.out.println(provincia);

    }

    public void user(ActionEvent event) throws IOException {

        VerificarUsuario();
    }


    private void VerificarUsuario() throws IOException {
        String provincia = Provincias.getValue();
        if (Nombre.getText().equals("Andres") && Contraseña.getText().equals("12345") && Correo.getText().equals("Andresito@gmail.com") && provincia.equals("Alajuela")) {

            System.out.println(Nombre.getText());
            System.out.println(Contraseña.getText());
            System.out.println(Correo.getText());
            System.out.println(provincia);
        }else if (Nombre.getText().isEmpty() && Contraseña.getText().isEmpty() && Correo.getText().isEmpty() && provincia == null) {
            Error.setText("Verifique que todos los espacios esten llenos");
        } else {
            Error.setText("Usuario incorrecto");
        }
    }
}
