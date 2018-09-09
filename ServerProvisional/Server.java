/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerProvisional;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;
import Interfaz.Punto;
import java.awt.Color;

/**
 * Server provisional que maneja lógica mientras se crean los sockets y se diferencian las apps.
 * @author kevv87
 */
public class Server {
    
    private static int clickedPointId=-1;
    
    /**
     * Funcion encargada de analizar dos puntos y si están lo suficientemente cerca, unirlos. 
     * La funcion guarda un punto anterior, hasta que le entra un nuevo punto, corre una funcion
     * de analisis para saber si los dos puntos estan lo suficientemente cerca y si lo estan
     * llama a la funcion que dibuja la linea entre ellos. Seguidamente limpia su memoria.
     * @param id Siguiente punto a unir.
     * @throws java.lang.Exception
     */
    public static void setClickedPointId(int id) throws Exception{
        if(clickedPointId == -1){  // No existe punto anterior
            clickedPointId = id;
        }else{  // Existe punto anterior
            LaminaJuego lamina = MarcoJuego.getLamina();
            Punto punto1 = lamina.getPuntos()[id];
            Punto punto2 = lamina.getPuntos()[clickedPointId];
            double distancia = distance(punto1.getX(),punto1.getY(),punto2.getX(),punto2.getY()); 
            if(distancia<=80){
                lamina.addLine(id, clickedPointId, Color.RED);  // Dibuja linea roja
            }
            clickedPointId = -1;  // Borra memoria
        }
    }
    
    /**
     * Funcion que dadas las coordenadas de dos puntos retorna la distancia entre ellos
     * @param x1 Coordenada x del primer punto
     * @param y1 Coordenada x del primer punto
     * @param x2 Coordenada x del segundo punto
     * @param y2 Coordenada y del segundo punto
     * @return Distancia entre los puntos con las coordenadas dadas.
     */
    public static double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.abs(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2)));
    }
    
}
