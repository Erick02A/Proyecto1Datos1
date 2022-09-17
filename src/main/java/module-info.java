module com.example.proyecto1datos1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.proyecto1datos1 to javafx.fxml;
    exports com.example.proyecto1datos1;
}