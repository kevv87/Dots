/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;


/**
 * Clase encargada de la creacion de un punto grafico.
 * @author kevv87
 */
public class Punto{  
    private final int x;
    private final int y;
    private final int radio = 5;
    private final int id;
    
    /**
     * Constructor
     * @param x Posicion en pixeles de la coordenada x del punto
     * @param y Posicion en pixeles de la coordenada y del punto
     * @param id Id del punto
     */
    public Punto(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        
    }
    


    /**
     * Retorna true si las coordenadas dadas estan dentro del circulo
     * @param x coordenada en x a verificar
     * @param y coordenada en y a verificar
     * @return True si el punto (x,y) se encuentra dentro de la figura
     */
    public boolean contiene(int x, int y){
        double distancia = Math.sqrt(Math.abs(Math.pow(this.x-x, 2)+Math.pow(this.y-y,2)));
        return distancia <= radio;
    }

    public int getRadio() {
        return radio;
    }    

    
    public int getId(){
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
