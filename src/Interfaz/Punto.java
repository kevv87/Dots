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
public class Punto {
    
    private final int id;
    private final int x;
    private final int y;
    private final int radio = 5;
    
    /**
     * Constructor
     * @param id Identificacion del punto
     * @param x Posicion en pixeles de la coordenada x del punto
     * @param y Posicion en pixeles de la coordenada y del punto
     * @param g Objeto del tipo Graphics2D donde se va a dibujar el punto
     */
    public Punto(int id, int x, int y, Graphics2D g){
        this.id = id;
        this.x = x;
        this.y = y;
        
        Ellipse2D circulo = new Ellipse2D.Double(); //Se quiere crear un circulo con precision double
        circulo.setFrameFromCenter(x,y,x+radio,y+radio);  // Coordenadas y dimensiones del circulo      
        g.fill(circulo); //Rellena el circulo
        g.draw(circulo); //Dibuja el circulo
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadio() {
        return radio;
    }
    
    
    
}
