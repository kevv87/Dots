package Interfaz;

import java.awt.Color;
import javax.swing.JFrame;

class MarcoJuego extends JFrame{
    
    private final int xo;
    private final int yo;
    private final int width;
    private final int height;
    private LaminaJuego lamina;
    
    /**
     * Constructor
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
        
        int [] id_arr = new int [8];
        id_arr[0] = 0;
        id_arr[1] = 1;
        id_arr[2] = 8;
        id_arr[3] = 9;
        id_arr[4] = 9;
        id_arr[5] = 10;
        id_arr[6] = 3;
        id_arr[7] = 2;
        
        lamina.addLine(0,1, Color.yellow);
        lamina.addPolygon(id_arr, Color.RED);
    }
    
}
