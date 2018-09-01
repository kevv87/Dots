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
    
    /**
     * Constructor
     */
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
    
    /**
     * Constructor
     */
    public MarcoJuego(){
        xo = 300;
        yo = 150;
        width = 600;
        height = 550;
        
        setTitle("Dots - Playing!");
        setBounds(xo,yo,width,height);
        add(new LaminaJuego());
    }
    
}

class LaminaJuego extends JPanel{
    
    @Override
    /**
     * Funcion encargada de dibujar sobre la lamina
     * @param g Objeto de tipo Graphics
     */
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        drawCircles(g2, Color.BLACK);
         
    }
    
    /**
    *Funcion encargada de la creacion de los circulos del juego
    * @param g Objeto de tipo Graphics2D
    * @param color Objeto Color
    */
    public void drawCircles(Graphics2D g, Color color){
        
        
        Ellipse2D[] circulo;
        circulo = new Ellipse2D [64]; // Crea un array de circulos
        
        int xi = 80;
        int yi = 60;
        int radio = 5;
        int espacio = 50;
        g.setPaint(color);
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                circulo[i*j] = new Ellipse2D.Double(); //Se quiere crear un circulo con precision double
                circulo[i*j].setFrameFromCenter(xi,yi,xi+radio, yi+radio);  // Coordenadaas y dimensionesdel circulo
                
                g.fill(circulo[i*j]); //Rellena el circulo
                g.draw(circulo[i*j]); //Dibuja el circulo
                
                xi += radio+espacio;
            }
            xi = 80;
            yi += radio+espacio;
        }
    }
        
       
        
    }
