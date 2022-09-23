package com.example.proyecto1datos1;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class Arduino extends reproControler{

    private static String Dato = "";
    public static String Arduino() throws InterruptedException {
        var sp = SerialPort.getCommPort("COM5");

        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        var hasOpened = sp.openPort();
        if (!hasOpened) {
            throw new IllegalStateException("Error al abrir el serial port");
        }

        Scanner data = new Scanner(sp.getInputStream());

        try {
            while (data.hasNextLine() ) {
                String dato = (data.nextLine());
                Orden(dato);
            }

        } catch (Exception e) {
            System.out.println("3");
            throw new RuntimeException(e);
        }


        return null;
    }

    public static String getDato() {
        return Dato;
    }

    private static String Orden(String dato) {
        switch (dato) {
            case "Play" -> {

                System.out.println(dato);
                setDato(dato);
                return dato;
            }
            case "Previous" -> System.out.println("Previous");
            case "Next" -> System.out.println("Next");
            case "Like" -> System.out.println("Like");
            case "NOLike" -> System.out.println("NoLike");
            case "Bucle" -> System.out.println("Bucle");
            case "NOBucle" -> System.out.println("noBucle");
        }
        return dato;
    }

    public static void setDato(String dato) {
        Dato = dato;
    }
}
