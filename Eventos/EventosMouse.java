/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Interfaz.MarcoJuego;
import Interfaz.Punto;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author kevv87
 */
public class EventosMouse implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Rectangle mouse = new Rectangle(e.getX(), e.getY(), 0,0);
        System.out.println("Se clickeo en:"+e.getX()+", "+e.getY());
        Interfaz.LaminaJuego lamina = MarcoJuego.getLamina();
        
        for(Punto punto:lamina.getPuntos()){
            if(punto.contiene(x,y)){
                Punto punto_click = punto;
                System.out.println("FUCK");
                System.out.println("Se clickeo en id: "+punto_click.getId());
            }
        }
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
         
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }
    
    
    
}
