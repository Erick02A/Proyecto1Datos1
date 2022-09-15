package com.example.proyecto1datos1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicPro extends Application {
    public String window;
    @Override
    public void start(Stage stage) throws IOException {
        window ="users.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MusicPro.class.getResource(window));
        Scene scene = new Scene(fxmlLoader.load(), 600, 285);
        stage.setTitle("Inicio de sesion");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}