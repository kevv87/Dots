/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Clases.ListaSimple;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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
    
    
   private static Punto[] puntos;
   
   private static Graphics g;
   private static Graphics2D g2;
    
    /**
     * Funcion encargada de dibujar sobre la lamina
     * @param g Objeto de tipo Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        
        this.g = g;
        
        super.paintComponent(g);
        
        g2 = (Graphics2D) g;
        drawCircles(Color.BLACK);
        
        LaminaJuego.drawLine(0, 1);
         
    }
    
    /**
    *Funcion encargada de la creacion de los circulos del juego
    * @param g Objeto de tipo Graphics2D
    * @param color Objeto Color
    */
    public void drawCircles(Color color){
        
        
        puntos = new Punto [64]; // Crea un array de circulos
        
        int xi = 80;
        int yi = 60;
        int radio = 5;
        int espacio = 50;
        int cont = 0;
        g2.setPaint(color);
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                puntos[cont] = new Punto(xi,yi,g2);
                cont+=1;
                xi+=radio+espacio;
            }
            xi = 80;
            yi += radio+espacio;
        }
     
    }
    
    /**
     * Funcion encargada de dibujar lineas entre puntos.
     * @param id1 Identificacion del primer punto
     * @param id2 Identificacion del segundo punto
     * @param g Grafico donde se dibujara el punto.
     */
    public static void drawLine(int id1, int id2){
        Punto p1 = puntos[id1];
        Punto p2 = puntos[id2];
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        
        g2.draw(new Line2D.Double(x1,y1, x2, y2));
        
    }
    
        
       
        
}
