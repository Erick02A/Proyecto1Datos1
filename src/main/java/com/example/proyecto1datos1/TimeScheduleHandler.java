package com.example.proyecto1datos1;

import com.fazecast.jSerialComm.*;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.TimerTask;

public class TimeScheduleHandler extends TimerTask implements SerialPortDataListener {
    private final long timeStart;

    public TimeScheduleHandler(long timeStart) {
        this.timeStart = timeStart;


    }
    @Override
    public void run(){
        System.out.println("Time elapsed: " +(System.currentTimeMillis()- this.timeStart) + "milliseconds");
        System.out.println(SerialPort.LISTENING_EVENT_DATA_RECEIVED);
        //System.out.println(serialPortEvent.getEventType());

    }
    @Override
    public int getListeningEvents(){
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        System.out.println(serialPortEvent.getEventType());
        if(serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED){
            System.out.println("Funciona xd");

        }

    }

}
