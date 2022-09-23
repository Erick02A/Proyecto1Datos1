package com.example.proyecto1datos1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class iniciadorController {
    @FXML
    private Button Login;
    @FXML
    private Label Error;
    @FXML
    private TextField Correo;

    @FXML
    private PasswordField Contraseña;


    public void user(ActionEvent event) throws IOException, InterruptedException {

        //Arduino.Arduino();
        Usuarios.Datos(Contraseña.getText(),Correo.getText(),Error);
    }
}