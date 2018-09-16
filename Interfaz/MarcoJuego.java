package Interfaz;

import Eventos.EventosMouse;
import javax.swing.JFrame;

public class MarcoJuego extends JFrame{
    
    private final int xo;
    private final int yo;
    private final int width;
    private final int height;
    private static LaminaJuego lamina;
    
    /**
     * Constructor
     * @throws java.lang.Exception
     */
    public MarcoJuego() throws Exception{
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    
    
    
}
