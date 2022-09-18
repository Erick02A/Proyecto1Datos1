package com.example.proyecto1datos1;

import com.fazecast.jSerialComm.*;
import java.util.Timer;


public class Arduino {
    public static void Arduino() {
        long timeStart = System.currentTimeMillis();
        var sp = SerialPort.getCommPort("COM5");


        sp.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,300,300);

        var hasOpened = sp.openPort();
        if(!hasOpened){throw new IllegalStateException("Error al abrir el serial port");}

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {sp.closePort();}));
        var timer = new Timer();
        var timedSchedule = new TimeScheduleHandler(timeStart);

        sp.addDataListener(timedSchedule);
        System.out.println("Listen: " + timedSchedule.getListeningEvents());
        timer.schedule(timedSchedule,0 , 1000);

    }
}