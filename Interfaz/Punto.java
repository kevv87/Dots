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
    private final Double x;
    private final Double y;
    private final int radio = 5;
    private final int id;
    private boolean bloqueado = false;
    
    /**
     * Constructor
     * @param x Posicion en pixeles de la coordenada x del punto
     * @param y Posicion en pixeles de la coordenada y del punto
     * @param id Id del punto
     */
    public Punto(Double x, Double y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        
    }
    
    /**
     * Dummy constuctor
    */
    public Punto(){
        this.x = 0.0;
        this.y = 0.0;
        this.id = -1;
    }
    

    /**
     * Retorna true si las coordenadas dadas estan dentro del circulo
     * @param x coordenada en x a verificar
     * @param y coordenada en y a verificar
     * @return True si el punto (x,y) se encuentra dentro de la figura
     */
    public boolean contiene(int x, int y){
        double distancia = Math.sqrt(Math.abs(Math.pow(this.x-x, 2)+Math.pow(this.y-y,2)));
        return distancia <= radio+3;
    }

    public int getRadio() {
        return radio;
    }    

    
    public int getId(){
        return id;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
    
        
    
}
