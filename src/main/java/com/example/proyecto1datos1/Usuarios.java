package com.example.proyecto1datos1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Usuarios implements Initializable{
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
    public void getProvincia(ActionEvent event){
        String provincia = Provincias.getValue();
        System.out.println(provincia);

    }
    public Usuarios(){

    }
}
