package com.example.proyecto1datos1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

/**
 * Controlador de la ventana de verificación de usuarios.
 */
public class iniciadorController {
    @FXML
    private Button Login;
    @FXML
    private Label Error;
    @FXML
    private TextField Correo;

    @FXML
    private PasswordField Contraseña;

    /**
     * Se encarga de llamar a la calse que verifica los usauarios pasandole lo que el usuario escribió en los espacios.
     * @param event
     */
    public void user(ActionEvent event){
        //Arduino.Arduino();
        Usuarios.Datos(Contraseña.getText(),Correo.getText(),Error);
    }
}