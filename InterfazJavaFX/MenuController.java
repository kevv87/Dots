/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazJavaFX;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import Matriz.ListaSimple;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author kevv87
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void play() throws Exception{
        ConnectWindow ventana = new ConnectWindow();
        ListaSimple lista = ventana.display();
        System.out.println("lista: " + lista);
    }
    
}
