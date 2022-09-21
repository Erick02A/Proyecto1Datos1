package com.example.proyecto1datos1;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;

public class Arduino {
    public void Arduino() throws InterruptedException {
        var sp = SerialPort.getCommPort("COM5");

        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);

        var hasOpened = sp.openPort();
        if(!hasOpened){throw new IllegalStateException("Error al abrir el serial port");}

        Scanner data = new Scanner(sp.getInputStream());

        while (data.hasNextLine()){
            if (data.nextLine() == "Play0"){
                System.out.println("Play");
                break;
            }
            else if (data.nextLine() == "Previous"){
                System.out.println("Play");
                break;
            }else if (data.nextLine() == "Next"){
                System.out.println("Next");
                break;
            }else if (data.nextLine() == "Like"){
                System.out.println("Like");
                break;
            }else if (data.nextLine() == "NOLike"){
                System.out.println("DisLike");
                break;
            }else if (data.nextLine() == "Bucle"){
                System.out.println("Bucle");
                break;
            }else if (data.nextLine() == "NOBucle"){
                System.out.println("NoBucle");
                break;
            }else {
                System.out.println(data.nextLine());
            }
        }

    }

}
