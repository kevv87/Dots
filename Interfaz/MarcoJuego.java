package Interfaz;

import Conectividad.Client;
import Matriz.ListaSimple;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.awt.BorderLayout;
import java.io.IOException;
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
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    Client.close();
                } catch (IOException ex) {
                    Logger.getLogger(MarcoJuego.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(MarcoJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.exit(0);
            }
        });
        
        
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
        
        LaminaJuego lamina = MarcoJuego.getLamina();
        
        for(Punto punto:lamina.getPuntos()){  // Para cada punto en los puntos de la lamina...
            if(punto.contiene(x,y)){  try {
                // si el punto contiene a la coordenada donde se clickeo
                
                ListaSimple puntos_a_enviar = Client.getPuntos_a_enviar();
                
                if(puntos_a_enviar.getTamanio() == 1){
                    int diferencia = Math.abs(((Punto)puntos_a_enviar.getValor(0)).getId() - punto.getId());
                    if(diferencia == 1 || (diferencia>=7 && diferencia <= 9)){
                        puntos_a_enviar.agregarAlInicio(punto);
                    }else{
                        puntos_a_enviar.eliminar();
                        puntos_a_enviar.agregarAlInicio(punto);
                    }
                    // Ahora convierte y lo envia
                    String id1 = toOct(((Punto)puntos_a_enviar.getValor(0)).getId());
                    String id2 = toOct(((Punto)puntos_a_enviar.getValor(1)).getId());
                    String msj;
                    msj = id1+","+id2;
                    Client.send_game(msj);
                    //Limpia
                    puntos_a_enviar.eliminar();
                }else{
                    puntos_a_enviar.agregarAlInicio(punto);
                }
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(MarcoJuego.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
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
    
    public static void main(String args[]) throws Exception{
        new MarcoJuego();
    }
    
    private String toOct(int num){
        int rem; //declaring variable to store remainder  
    String octal=""; //declareing variable to store octal  
    //declaring array of octal numbers  
    char octalchars[]={'0','1','2','3','4','5','6','7'};  
    //writing logic of decimal to octal conversion   
    while(num>0)  
    {  
       rem=num%8;   
       octal=octalchars[rem]+octal;   
       num=num/8;  
    }
    if(octal.length()==1){
        octal = "0"+octal;
    }else if(octal.length() == 0){
        octal = "00";
    }
    return octal;
    }
    
    }
