package com.example.proyecto1datos1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Esta es la clase Main del trabajo, esta se encarga de extarer y utilizar los recursos y las eccenas de los fxml.
 */
public class MusicPro extends Application {
    private static Stage Window;

    /**
     * Esta se encarga de iniciar la ventana, con sus tamños y demás, extrayendo la ecena de un fxml.
     * @param stage la ventana Stage.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Window = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("users.fxml")));
        stage.setTitle("MusicPro");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 600, 285));
        stage.show();
    }

    /**
     * Esta se encarga de cambiar la escena de la ventana, cambiando el fxml a utilizar.
     * @param fxml nuevo archivo fxml a utiliza.
     * @throws IOException
     */
    public void CambiarPantalla(String fxml) throws IOException{
        Parent pane =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        Window.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch();
    }
}