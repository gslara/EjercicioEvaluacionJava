package com.ejercicio.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ejercicio.main.entities.Frame;
import com.google.gson.Gson;

@SpringBootApplication
public class EjercicioEvaluacionJavaApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(EjercicioEvaluacionJavaApplication.class, args);
	
		String trama = ">RUS0E,260421180138-3298670-068855420000001110000100711200000000000F8;03;04191439;0000000480;0000060;0000660;0000010000;ID=125433;#LOG:07A4;*4F<";
		Frame frame = new Frame();
		
		//Validamos que la trama ingresada sea correcta
        if(validarTrama(trama)) {
            setCamposTrama(trama, frame);
            
            //Declaramos la URL
    		URL url = new URL ("https://bosprgvc44.execute-api.us-east-1.amazonaws.com/default/trax");
    		
    		//Creamos la conexión, indicando el método de request y el formato
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("POST");
    		con.setRequestProperty("Content-Type", "application/json; utf-8");	
    		
    		//Habilitamos el envío de contenido
    		con.setDoOutput(true);
    		
    		//Usamos Gson para parsear el objeto frame a un json
    		Gson g = new Gson();
    		String jsonString = g.toJson(frame);
    		
    		//Enviamos la información
    		try(OutputStream os = con.getOutputStream()) {
    		    byte[] input = jsonString.getBytes("utf-8");
    		    os.write(input, 0, input.length);			
    		}
    		
    		//Creamos el lector para la ruta
    		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
    			StringBuilder response = new StringBuilder();
    			String responseLine = null;
    			while ((responseLine = br.readLine()) != null) {
    				response.append(responseLine.trim());
    		    }
    			System.out.println(response.toString());
    		}
            
        } else {
        	//Si la trama no cumple la validación enviamos un mensaje de error
            System.out.println("ERROR: TRAMA NO VÁLIDA"); 
        }
	
	}

	
	//Método para validar que la trama cumpla los requisitos
	public static boolean validarTrama(String trama) {
        boolean validacion = false;
        int contador=0;

        //Los mensajes se delimitan con > y <
        if(trama.charAt(0)=='>' && trama.charAt(trama.length()-1) == '<') {

            //Luego de > se esperan los caracteres RUS
            if(trama.indexOf("RUS", 1) == 1){

                //Comprobamos que haya , luego de dos espacios para caracteres de messageType
                if(trama.charAt(6)==',') {

                    //Recorremos la trama para comprobar que hayan tantos ; como campos del contenido
                    for(int i=0; i<trama.length(); i++) {
                        if(trama.charAt(i) == ';') {
                            contador++;

                          //Son 9 campos, por lo que tienen que haber 9 ;
                            if(contador==9) { 

                            	//Comprobamos que haya * después del último ;
                                if(trama.charAt(i+1) == '*') { 
                                    String checksumAux = trama.substring(i+2, trama.length()-1);
                                    
                                  //Comprobamos que haya un número hexadecimal (checksum) después del *
                                    if(checksumAux.matches("[0-9A-F]+")) {
                                        //Si todas estas condiciones se cumplen, queda validada la trama
                                        validacion = true;
                                    }

                                }

                            }

                        }
                    }

                }

            }
            
        }

        return validacion;
    }
	
	
	//Asignamos los valores para las variables de Frame y GpsData. Devolvemos el objeto
	public static Frame setCamposTrama(String trama, Frame frame) {
        String contenido = setContenido(trama);
        int finTrama = 0;

        frame.setMessageType(trama);
        String datosGPS = frame.setGpsData(contenido);
        finTrama = frame.setEventId(contenido, datosGPS, finTrama);
        finTrama = frame.setBatteryLevel(contenido, finTrama);
        finTrama = frame.setTimer1(contenido, finTrama);
        finTrama = frame.setTimer2(contenido, finTrama);
        finTrama = frame.setTimer3(contenido, finTrama);
        finTrama = frame.setInitialOdometer(contenido, finTrama);
        finTrama = frame.setMessageId(contenido, finTrama);
        frame.setLogNumber(contenido, finTrama);
        frame.setChecksum(trama);

        return frame;
    }


	//Obtenemos el contenido de la trama
    public static String setContenido(String trama) {
        int inicioContenido = trama.indexOf(',');
        int finContenido = trama.indexOf('*');
        String contenido = trama.substring(inicioContenido+1, finContenido);

        return contenido;
    }
	
}
