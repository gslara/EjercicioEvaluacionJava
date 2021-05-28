package com.ejercicio.main.entities;

public class Frame {

	//Declaramos variables
	String messageType, messageId, logNumber;
    GpsData gpsData;
    int eventId, checksum;
    long batteryLevel, timer1, timer2, timer3, initialOdometer;

    
    //Obtenemos y asignamos valor de messageType
    public void setMessageType(String trama) {
        this.messageType = trama.substring(4, 6);
        System.out.println("messageType: " + messageType);
    }

    
    //Obtenemos y asignamos valores de gpsData
    public String setGpsData(String contenido) {
        String datosGPS = contenido.substring(0, contenido.indexOf(';'));
        this.gpsData = new GpsData();
        
        gpsData.setDate(datosGPS);
        gpsData.setLatitude(datosGPS);
        gpsData.setLongitude(datosGPS);
        gpsData.setDirection(datosGPS);
        gpsData.setSpeed(datosGPS);
        gpsData.setPower(datosGPS);
        gpsData.setSatelliteCount(datosGPS);
        gpsData.setGpsAge(datosGPS);
        gpsData.setPositionType(datosGPS);
        gpsData.setHdop(datosGPS);
        gpsData.setGprs(datosGPS);
        gpsData.setGsmStatus(datosGPS);
        gpsData.setSignal(datosGPS);
        gpsData.setOdometer(datosGPS);
        gpsData.setInputs(datosGPS);

        return datosGPS;
    }

    
    //Obtenemos y asignamos valor de eventId
    public int setEventId(String contenido, String datosGPS, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', datosGPS.length()+1);
        this.eventId = Integer.parseInt(contenido.substring(datosGPS.length()+1, finTramaActual));
        System.out.println("eventId: " + eventId);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de batteryLevel
    public int setBatteryLevel(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.batteryLevel = Long.parseLong(contenido.substring(finTramaAnterior+1, finTramaActual));
        System.out.println("batteryLevel: " + batteryLevel);
        return finTramaActual;
    }
    

    //Obtenemos y asignamos valor de timer1
    public int setTimer1(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.timer1 = Long.parseLong(contenido.substring(finTramaAnterior+1, finTramaActual));
        System.out.println("timer1: " + timer1);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de timer2
    public int setTimer2(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.timer2 = Long.parseLong(contenido.substring(finTramaAnterior+1, finTramaActual));
        System.out.println("timer2: " + timer2);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de timer3
    public int setTimer3(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.timer3 = Long.parseLong(contenido.substring(finTramaAnterior+1, finTramaActual));
        System.out.println("timer3: " + timer3);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de initialOdometer
    public int setInitialOdometer(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.initialOdometer = Long.parseLong(contenido.substring(finTramaAnterior+1, finTramaActual));
        System.out.println("initialOdometer: " + initialOdometer);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de messageId
    public int setMessageId(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.messageId = contenido.substring(finTramaAnterior+1, finTramaActual);
        System.out.println("messageId: " + messageId);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de logNumber
    public int setLogNumber(String contenido, int finTramaAnterior) {
        int finTramaActual = contenido.indexOf(';', finTramaAnterior+1);
        this.logNumber = contenido.substring(finTramaAnterior+1, finTramaActual);
        System.out.println("logNumber: " + logNumber);
        return finTramaActual;
    }

    
    //Obtenemos y asignamos valor de checksum convertido de Hexadecimal a Decimal
    public void setChecksum(String trama) {
        String checksumS = trama.substring(trama.indexOf('*')+1, trama.length()-1);
        this.checksum = Integer.parseInt(checksumS, 16);
        System.out.println("checksum: " + checksum);
    }
	
}
