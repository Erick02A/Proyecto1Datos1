package com.example.proyecto1datos1;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class Arduino extends reproControler{
    public static String Arduino() throws InterruptedException {
        var sp = SerialPort.getCommPort("COM3");

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
    private static void Orden(String dato) {
            if (dato == "Play") {
                System.out.println("Play");
                reproductor.playpause(false);
            }
            else if (dato =="Previous") {
                System.out.println("Previous");
                reproductor.previus();
            }
            else if (dato =="Next") {
                System.out.println("Next");
                reproductor.next();
            }
            else if (dato == "Like") {
                System.out.println("Like");
                reproductor.like(false);
            }
            else if (dato=="NOLike") {
                System.out.println("NoLike");
                reproductor.like(true);
            }
            else if (dato == "Bucle") {
                System.out.println("Bucle");
                reproductor.Bucle(false);
            }
            else if (dato == "NOBucle") {
                System.out.println("noBucle");
                reproductor.Bucle(true);
            }
            else {
                System.out.println(dato);
                reproductor.setVolumen(Double.parseDouble(dato));
            }
    }
}
