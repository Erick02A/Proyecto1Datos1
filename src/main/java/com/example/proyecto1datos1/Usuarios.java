package com.example.proyecto1datos1;

import javafx.scene.control.*;

import java.io.*;

/**
 * Esta se encarga de verificar el inicio de seción del usuario y guardar al usuario que se encuentra activo.
 */
public class Usuarios{

    /**
     * Esta lee el archivo donde se encuantra la información de los usuarios registrados, los compara con lo que el
     * usuario ingreso, en caso de alguna inconcistencia escribe el error en el Label para mostrarlo.
     * @param Contraseña String de contraseña ingresada.
     * @param Correo String de correo ingresado.
     * @param error Label de error.
     */
    public static void Datos(String Contraseña, String Correo, Label error){
        String linea, Name, correo, contraseña, Provincia;

        try {
            BufferedReader br = new BufferedReader(new FileReader("Usuario/Users.csv"));
            linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Name = datos[0];
                correo = datos[1];
                Provincia = datos[2];
                contraseña = datos[3];
                if ( Contraseña.equals(contraseña) && Correo.equals(correo)) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Usuario/activo.txt"));
                    PrintWriter pw = new PrintWriter(bw);
                    pw.write(Name);
                    bw.close();
                    pw.close();
                    MusicPro m = new MusicPro();
                    m.CambiarPantalla("repro.fxml");



                }else if (Contraseña.isEmpty() | Correo.isEmpty()) {
                    error.setText("Verifique que todos los espacios esten llenos");

                }else{error.setText("Usuario incorrecto");}
            }
        } catch (IOException est) {
           System.out.println("error");
            throw new RuntimeException(est);

        }
    }
}
