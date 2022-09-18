package com.example.proyecto1datos1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicPro extends Application {
    private static Stage Window;

    @Override
    public void start(Stage stage) throws Exception {
        Arduino.Arduino();
        Window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("users.fxml"));
        stage.setTitle("MusicPro");
        stage.setScene(new Scene(root, 600, 285));
        stage.show();
    }
    public void CambiarPantalla(String fxml) throws IOException{
        Parent pane =  FXMLLoader.load(getClass().getResource(fxml));
        Window.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}