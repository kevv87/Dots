package Interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaminaJuego extends JPanel{
    
    
   private Punto[] puntos = new Punto[64];
   private ListaLineas lineas = new ListaLineas();
   private ListaPoligonos poligonos = new ListaPoligonos();
   
   /**
    * Constructor
    */
   public LaminaJuego(){
       puntos = new Punto [64]; // Crea un array de circulos
        int xi = 80;
        int yi = 60;
        int radio = 5;
        int espacio = 50;
        int cont = 0;
        
        // Dibujando puntos
        for(int i = 0;i<8;i++){
            System.out.println("fila: " + i );
            for(int j = 0;j<8;j++){
                puntos[cont] = new Punto(xi,yi, cont);
                cont+=1;
                System.out.println("\n columna: " + j);
                System.out.println("\n pos X: " + xi+ ",  pos Y: " + yi);
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
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Dibujando puntos
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
                Linea linea = lineas.getValor(i);
                g2.setPaint(linea.getColor());
                g2.draw(linea);
            } catch (Exception ex) {
                Logger.getLogger(LaminaJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        //Dibujando poligonos
        for(int i = 0;i<poligonos.getTamanio();i++){
            try {
                Poligono poligono = poligonos.getValor(i);
                g2.setPaint(poligono.getColor());
                g2.fill(poligono);
            } catch (Exception ex) {
                Logger.getLogger(LaminaJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /**
     * Funcion encargada de dibujar lineas entre puntos.
     * @param id1 Identificacion del primer punto
     * @param id2 Identificacion del segundo punto
     * @param color color del que se quiere hacer la linea
     * @throws java.lang.Exception
     */
    public void addLine(int id1, int id2, Color color) throws Exception{
        
        Punto punto1 = puntos[id1];
        Punto punto2 = puntos[id2];
        int x1 = punto1.getX();
        int y1 = punto1.getY();
        int x2 = punto2.getX();
        int y2 = punto2.getY();

        double distancia = Math.pow((Math.pow(y2-y1,2) + Math.pow(x2-x1,2)),0.5); // hipotenusa entre puntos/distancia entre puntos
        int limite = 80; //distancia máxima entre puntos

        if(!lineas.isIn(x1, y1, x2, y2)){
            if(distancia < limite){
                lineas.agregarAlInicio(new Linea(x1,y1,x2,y2,color));
            }

            else{
                System.out.println("no es posible conectar puntos que no sean vecinos");
            }

        }
        repaint();
    }

    public Punto[] getPuntos() {
        return puntos;
    }

    public ListaLineas getLineas() {
        return lineas;
    }

    public ListaPoligonos getPoligonos() {
        return poligonos;
    }
    
    /**
     * Funcion encargada de .

     * @param ids Array de ids de los puntos que forman el poligono
     * @param color Color que se desea que tenga el poligono
     */
    public void addPolygon(int [] ids, Color color){
        Poligono poligono = new Poligono(color);
        for(int id:ids){
            Punto punto = puntos[id];
            poligono.addPoint(punto.getX(), punto.getY());
        }
        poligonos.agregarAlInicio(poligono);
        repaint();
    }
}
