/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.geom.Line2D;

/**
 *
 * @author kevv87
 */
public class Linea extends Line2D.Double{
    
    private Color color;
    
    /**
     *  Metodo constructor
     * @param color Color del que se quiere crear la linea.
     * @param x1 coordenada en x del primer punto
     * @param y1 coordenada en y del primer punto
     * @param x2 coordenada en x del segundo punto
     * @param y2 coordenada en y del primer punto
     */
    public Linea(double x1, double y1, double x2, double y2, Color color){
        super(x1,y1,x2,y2);
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }
    
}
