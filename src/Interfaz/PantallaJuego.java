/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kevv87
 */
public class PantallaJuego {
    
    public PantallaJuego(){
    
        MarcoJuego pantalla = new MarcoJuego();
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.setVisible(true);
    
    }
    
}

class MarcoJuego extends JFrame{
    
    public MarcoJuego(){
        setBounds(300,300,600,450);
        add(new LaminaJuego());
    }
    
}

class LaminaJuego extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
