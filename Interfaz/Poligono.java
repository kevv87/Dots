/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;

/**
 *
 * @author kevv87
 */
public class Poligono extends java.awt.Polygon{
    
    Color color;
    
    public Poligono(Color color){
        super();
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
