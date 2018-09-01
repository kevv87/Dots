/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Clase encargada de crear la pantalla del juego
 * @version 1.0.0
 * @author kevv87
 * @since 01/07/2000
 */
public class PantallaJuego {
    
    public PantallaJuego(){
    
        MarcoJuego pantalla = new MarcoJuego();
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.setVisible(true);
    
    }
    
}

class MarcoJuego extends JFrame{
    
    private final int xo;
    private final int yo;
    private final int width;
    private final int height;
    
    public MarcoJuego(){
        xo = 300;
        yo = 150;
        width = 600;
        height = 600;
        
        setTitle("Dots - Playing!");
        setBounds(xo,yo,width,height);
        add(new LaminaJuego());
    }
    
}

class LaminaJuego extends JPanel{
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        drawCircles(g2);
         
    }
    
    public void drawCircles(Graphics2D g){
        
        
        Ellipse2D[] circulo;
        circulo = new Ellipse2D [64];
        
        int xi = 50;
        int yi = 50;
        int radio = 5;
        int espacio = 50;
        g.setPaint(Color.BLACK);
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                circulo[i*j] = new Ellipse2D.Double();
                circulo[i*j].setFrameFromCenter(xi,yi,xi+radio, yi+radio);
                xi += radio+espacio;
                g.fill(circulo[i*j]);
                g.draw(circulo[i*j]);
                circulo[0].setFrameFromCenter(xi,yi,xi+radio,yi+radio);
                g.fill(circulo[0]);
                g.draw(circulo[0]);
            }
            xi = 50;
            yi += radio+espacio;
        }
    }
        
       
        
    }
