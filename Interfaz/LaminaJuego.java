package Interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

class LaminaJuego extends JPanel{
    
    
   private static Punto[] puntos;
   private int [][] id_figuras;
   private int [][] id_lineas;
   
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
        
        
        
    }
    
    /**
    *Funcion encargada de la creacion de los circulos del juego
    * @param g Objeto de tipo Graphics2D
    * @param color Objeto Color
    */
    public static void drawCircles(Color color){
        
        
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
    public static void drawLine(int id1, int id2, Color color){
        Punto p1 = puntos[id1];
        Punto p2 = puntos[id2];
        int x1 = p1.getX();
        int x2 = p2.getX();
        int y1 = p1.getY();
        int y2 = p2.getY();
        
        g2.setPaint(color);
        g2.draw(new Line2D.Double(x1,y1, x2, y2));
        
    }
    
    /**
     * Funcion encargada de rellenar las figuras que generan los poligonos.
     * @param id_arr Array con los ids de los puntos que componen el poligono
     */
    public static void fillPolygon(int [] id_arr, Color color){
        Polygon poligono = new Polygon();
        for(int i = 0; i < id_arr.length;i++){
            poligono.addPoint(puntos[id_arr[i]].getX(),puntos[id_arr[i]].getY());
        }
        g2.setPaint(color);
        g2.draw(poligono);
        g2.fill(poligono);
    }
    
        
       
        
}