package Interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import Clases.ListaLineas;
import java.util.logging.Level;
import java.util.logging.Logger;

class LaminaJuego extends JPanel{
    
    
   private Punto[] puntos = new Punto[64];
   private ListaLineas lineas = new ListaLineas();
   
   public LaminaJuego(){
       puntos = new Punto [64]; // Crea un array de circulos
        int xi = 80;
        int yi = 60;
        int radio = 5;
        int espacio = 50;
        int cont = 0;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                puntos[cont] = new Punto(xi,yi);
                cont+=1;
                xi+=radio+espacio;
            }
            xi = 80;
            yi += radio+espacio;
        }
   }
   
    /**
     * Funcion encargada de dibujar sobre la lamina
     * @param g Objeto de tipo Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        System.out.println("Hey");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Dibujando circulos
        int x;
        int y;
        int radio;
        g2.setPaint(Color.BLACK);
            for(int j = 0;j<puntos.length;j++){
                Punto actual = puntos[j];
                x = actual.getX();
                y = actual.getY();
                radio = actual.getRadio();
                
                Ellipse2D circulo = new Ellipse2D.Double(); //Se quiere crear un circulo con precision double
                circulo.setFrameFromCenter(x,y,x+radio,y+radio);  // Coordenadas y dimensiones del circulo      
                g2.fill(circulo); //Rellena el circulo
                g2.draw(circulo); //Dibuja el circulo
            }
        
        // Dibujando lineas
        for(int i = 0;i<lineas.getTamanio();i++){
            try {
                g2.draw(lineas.getValor(i));
            } catch (Exception ex) {
                Logger.getLogger(LaminaJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /**
     * Funcion encargada de dibujar lineas entre puntos.
     * @param id1 Identificacion del primer punto
     * @param id2 Identificacion del segundo punto
     */
    public void drawLine(int id1, int id2, Color color) throws Exception{
        
        Punto punto1 = puntos[id1];
        Punto punto2 = puntos[id2];
        int x1 = punto1.getX();
        int y1 = punto1.getY();
        int x2 = punto2.getX();
        int y2 = punto2.getY();
        
        if(!lineas.isIn(x1, y1, x2, y2)){
            lineas.agregarAlInicio(new Line2D.Double(x1,y1,x2,y2));
        }
        
    }
    
    /**
     * Funcion encargada de rellenar las figuras que generan los poligonos.
     * @param id_arr Array con los ids de los puntos que componen el poligono
     *//*
    public static void fillPolygon(int [] id_arr, Color color){
        Polygon poligono = new Polygon();
        for(int i = 0; i < id_arr.length;i++){
            poligono.addPoint(puntos[id_arr[i]].getX(),puntos[id_arr[i]].getY());
        }
        g2.setPaint(color);
        g2.draw(poligono);
        g2.fill(poligono);
    }*/
    
        
       
        
}