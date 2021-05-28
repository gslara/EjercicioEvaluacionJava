package com.ejercicio.main.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GpsData {

	//Declaramos variables
	Date date;
    double latitude, longitude;
    int direction, speed, satelliteCount, gpsAge, hdop, gsmStatus, signal, inputs;
    boolean power, positionType, gprs;
    long odometer;

    
    //Obtenemos y asignamos valor de date
    public void setDate(String datosGPS) {
        this.date = new Date();
        
        //Separamos fecha y hora
        String fechaString = datosGPS.substring(0, 6);
        String horaString = datosGPS.substring(6, 12);

        //Hacemos el formato
        fechaString = fechaString.substring(0, 2) + "/" + fechaString.substring(2, 4) + "/" + fechaString.substring(4, 6);
        horaString = horaString.substring(0, 2) + ":" + horaString.substring(2, 4) + ":" + horaString.substring(4, 6);

        //Convertimos a dato tipo Date
        try {
            this.date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(fechaString + " " + horaString);

        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("gpsData.date: " + formatoFecha.format(date));
    }

    
    //Obtenemos y asignamos valor de latitude
    public void setLatitude(String datosGPS) {
        String latitudS = datosGPS.substring(12, 20);
        this.latitude = Double.parseDouble(latitudS.substring(0, 3) + "." + latitudS.substring(3,8));
        System.out.println("gpsData.latitude: " + latitude);
    }

    
    //Obtenemos y asignamos valor de longitude
    public void setLongitude(String datosGPS) {
        String longitudS = datosGPS.substring(20, 29);
        this.longitude = Double.parseDouble(longitudS.substring(0, 4) + "." + longitudS.substring(4,9));
        System.out.println("gpsData.longitude: " + longitude);
    }

    
    //Obtenemos y asignamos valor de direction
    public void setDirection(String datosGPS) {
        this.direction = Integer.parseInt(datosGPS.substring(29, 32));
        System.out.println("gpsData.direction: " + direction);
    }

    
    //Obtenemos y asignamos valor de speed
    public void setSpeed(String datosGPS) {
        this.speed = Integer.parseInt(datosGPS.substring(32, 35));
        System.out.println("gpsData.speed: " + speed);
    }
    

    //Obtenemos y asignamos valor de power
    public void setPower(String datosGPS) {
        int powerInt = Integer.parseInt(datosGPS.substring(35, 36));
        if(powerInt == 1) {
            this.power = true;
        } else {
            this.power = false;
        }
        System.out.println("gpsData.power: " + power);
    }

    
    //Obtenemos y asignamos valor de satelliteCount
    public void setSatelliteCount(String datosGPS) {
        this.satelliteCount = Integer.parseInt(datosGPS.substring(36, 38));
        System.out.println("gpsData.satelliteCount: " + satelliteCount);
    }
    

    //Obtenemos y asignamos valor de gpsAge
    public void setGpsAge(String datosGPS) {
        this.gpsAge = Integer.parseInt(datosGPS.substring(38, 42));
        System.out.println("gpsData.gpsAge: " + gpsAge);
    }

    
    //Obtenemos y asignamos valor de positionType
    public void setPositionType(String datosGPS) {
        int positionTypeInt = Integer.parseInt(datosGPS.substring(42, 43));
        if(positionTypeInt == 1) {
            this.positionType = true;
        } else {
            this.positionType = false;
        }
        System.out.println("gpsData.positionType: " + positionType);
    }
    

    //Obtenemos y asignamos valor de hdop
    public void setHdop(String datosGPS) {
        this.hdop = Integer.parseInt(datosGPS.substring(43, 46));
        System.out.println("gpsData.hdop: " + hdop);
    }

    
    //Obtenemos y asignamos valor de gprs
    public void setGprs(String datosGPS) {
        int gprsInt = Integer.parseInt(datosGPS.substring(46, 47));
        if(gprsInt == 1) {
            this.gprs = true;
        } else {
            this.gprs = false;
        }
        System.out.println("gpsData.gprs: " + gprs);
    }

    
    //Obtenemos y asignamos valor de gsmStatus
    public void setGsmStatus(String datosGPS) {
        this.gsmStatus = Integer.parseInt(datosGPS.substring(47, 48));
        System.out.println("gpsData.gsmStatus: " + gsmStatus);
    }
    

    //Obtenemos y asignamos valor de signal
    public void setSignal(String datosGPS) {
        this.signal = Integer.parseInt(datosGPS.substring(48, 50));
        System.out.println("gpsData.signal: " + signal);
    }

    
    //Obtenemos y asignamos valor de odometer
    public void setOdometer(String datosGPS) {
        this.odometer = Long.parseLong(datosGPS.substring(50, 60));
        System.out.println("gpsData.odometer: " + odometer);
    }

    
    //Obtenemos y asignamos valor de inputs convertido de Hexadecimal a Decimal
    public void setInputs(String datosGPS) {
        this.inputs = Integer.parseInt(datosGPS.substring(60, 62), 16);
        System.out.println("gpsData.inputs: " + inputs);
    }
	
}
