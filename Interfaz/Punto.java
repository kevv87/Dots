/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Clase encargada de la creacion de un punto grafico.
 * @author kevv87
 */
public class Punto{  
    private final int x;
    private final int y;
    private final int radio = 5;
    
    /**
     * Constructor
     * @param x Posicion en pixeles de la coordenada x del punto
     * @param y Posicion en pixeles de la coordenada y del punto
     * @param g Objeto del tipo Graphics2D donde se va a dibujar el punto
     */
    public Punto(int x, int y){
        this.x = x;
        this.y = y;
        
        
    }

    public int getRadio() {
        return radio;
    }    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
