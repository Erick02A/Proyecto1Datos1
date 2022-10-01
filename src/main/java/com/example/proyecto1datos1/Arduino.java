package com.example.proyecto1datos1;

import com.fazecast.jSerialComm.SerialPort;

import java.io.*;
import java.util.Scanner;

public class Arduino{
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
                led(sp);

            }

        } catch (Exception e) {
            System.out.println("3");
            throw new RuntimeException(e);
        }


        return null;
    }
    private static void Orden(String dato) throws IOException {
        if (dato.equals("Play")) {
            //System.out.println("Play");
            reproductor.playpause(false);
        }
        else if(dato.equals("Pause")){
            //System.out.println("Pause");
            reproductor.playpause(true);
        }
        else if (dato.equals("Previous")) {
            //System.out.println("Previous");
            reproductor.previus(true);
        }
        else if (dato.equals("Next")) {
            //System.out.println("Next");
            reproductor.next(true);
        }
        else if (dato.equals("Like")) {
            //System.out.println("Like");
            reproductor.like(false);
        }
        else if (dato.equals("NOLike")) {
            //System.out.println("NoLike");
            reproductor.like(true);
        }
        else if (dato.equals("Bucle")) {
            //System.out.println("Bucle");
            reproductor.Bucle(false);
        }
        else if (dato.equals("NOBucle")) {
            //System.out.println("noBucle");
            reproductor.Bucle(true);
        }
        else {
            //System.out.println(dato);
            reproductor.setVolumen(Double.parseDouble(dato));
        }
    }
    public static void led(SerialPort sp) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Usuario/"+reproductor.getactivo()+"/Likelist.csv"));
        String f="";
        boolean s = false;
        while ((f=br.readLine())!=null) {
            if (reproductor.getNemeSong().equals(f)){
                s = true;
            }
        }
        br.close();
        if (s==true){
            PrintWriter p = new PrintWriter(sp.getOutputStream());
            p.print("L");
            p.flush();
        }else {
            PrintWriter p = new PrintWriter(sp.getOutputStream());
            p.print("D");
            p.flush();
        }
    }
}
