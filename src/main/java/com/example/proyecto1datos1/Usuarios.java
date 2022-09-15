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

    public static ArrayList<usuario> Usuario;


    public static ArrayList<usuario> getUsuarios() {
        return Usuario;
    }
    public Usuarios() {
        if(Usuario == null) {
            this.Usuario = new ArrayList<usuario>();
        }
        else {
            this.Usuario = Usuario;
        }
    }

    public void Datos() {
        String linea, Name, correo, contraseña, Provincia;
        String provincia = Provincias.getValue();

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\andre\\Desktop\\java\\Proyecto1Datos1\\src\\main\\java\\com\\example\\proyecto1datos1\\Users.csv"));
            linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Name = datos[0];
                correo = datos[1];
                Provincia = datos[2];
                contraseña = datos[3];
                if (Nombre.getText().equals(Name) && Contraseña.getText().equals(contraseña) && Correo.getText().equals(correo) && provincia.equals(Provincia)) {
                    System.out.println(Name);
                    System.out.println(contraseña);
                    System.out.println(correo);
                    System.out.println(Provincia);
                    Error.setText("yessss");



                }else if (Nombre.getText().isEmpty() | Contraseña.getText().isEmpty() | Correo.getText().isEmpty() | provincia == null) {
                    Error.setText("Verifique que todos los espacios esten llenos");

                }else{Error.setText("Usuario incorrecto");}
            }



        } catch (IOException est) {
            System.out.println("error");
            throw new RuntimeException(est);

        }
    }
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

        Datos();
    }

}
