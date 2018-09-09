/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerProvisional;

import Interfaz.LaminaJuego;
import Interfaz.MarcoJuego;
import java.awt.Color;

/**
 *
 * @author kevv87
 */
public class Server {
    
    private static int clickedPointId=-1;
    
    public static void setClickedPointId(int id) throws Exception{
        if(clickedPointId == -1){
            clickedPointId = id;
        }else{
            LaminaJuego lamina = MarcoJuego.getLamina();
            lamina.addLine(id, clickedPointId, Color.yellow);
            clickedPointId = -1;
        }
    }
    
}
