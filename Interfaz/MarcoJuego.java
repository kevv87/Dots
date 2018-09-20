package Interfaz;

import Conectividad.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarcoJuego extends JFrame{
    
    private final int xo;
    private final int yo;
    private final int width;
    private final int height;
    private boolean activo;
    private static LaminaJuego lamina;
    
    /**
     * Constructor
     * @throws java.lang.Exception
     */
    public MarcoJuego() throws Exception{
        
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        xo = 300;
        yo = 150;
        width = 600;
        height = 550;
        
        setTitle("Dots - Playing!");
        setBounds(xo,yo,width,height);
        
        lamina = new LaminaJuego();
        add(lamina);
        
       EventosMouse evento = new EventosMouse();
        addMouseListener(evento);
        
        
    }
    
    public class EventosMouse implements MouseListener{
    
    @Override
    public void mouseClicked(MouseEvent e) {
        ObjectMapper mapper = new ObjectMapper();
        
        if(!activo){
            return;
        }
        int x = e.getX();  // Consigue la coordenada en x de donde se origina el evento
        int y = e.getY();
        
        Interfaz.LaminaJuego lamina = MarcoJuego.getLamina();
        
        for(Punto punto:lamina.getPuntos()){  // Para cada punto en los puntos de la lamina...
            if(punto.contiene(x,y)){  try {
                // si el punto contiene a la coordenada donde se clickeo
                System.out.println(punto);
                
                Client.send(mapper.writeValueAsString(punto));
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(MarcoJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    public int getXo() {
        return xo;

    }

    public int getYo() {
        return yo;
    }

    @Override
    public int getWidth() {
        return width;

    }

    @Override
    public int getHeight() {
        return height;
    }

    public static LaminaJuego getLamina() {
        return lamina;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
