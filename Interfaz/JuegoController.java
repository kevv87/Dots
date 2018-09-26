/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Conectividad.Client;
import InterfazJavaFX.AlertWindow;
import Matriz.ListaSimple;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevv87
 */
public class JuegoController {

    @FXML
    private Pane gamePane;
    
    private static Group lineGroup = new Group();
    private static Punto[] puntos = new Punto[64];
    private static boolean activo = false;
    private final ListaSimple puntos_a_enviar = new ListaSimple();
    
    private Stage window;

    public void addtoPane(Group group){
        gamePane.getChildren().add(group);
    }
    
    
    @FXML public void Handle(MouseEvent event) {
        if(!activo){
            return;
        }
        
        int x = (int) event.getX();  // Consigue la coordenada en x de donde se origina el evento
        int y = (int) event.getY();
        
        
        for(Punto punto:puntos){  // Para cada punto en los puntos de la lamina...
            if(punto.contiene(x,y)){  try {
                // si el punto contiene a la coordenada donde se clickeo
                System.out.println("Tocado");
                
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
                    System.out.println(msj);
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
    
    public void initialize(){
         //Dibujando circulos
        int x = 20;
        int y = 20;
        int radio = 5;
        int espacio = 50;
        int cont = 0;
        Group points = new Group();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Circle circle = new Circle();
                circle.setCenterX(x);
                circle.setCenterY(y);
                circle.setRadius(radio);
                puntos[cont] = new Punto(x,y,cont);
                
                x = x+radio+espacio;
                points.getChildren().add(circle);
                
                
                cont++;
            }
            x = 20;
            y = y + radio + espacio;
        }
        System.out.println(puntos[0].getX()+","+puntos[1].getY());
        addtoPane(points);
        addtoPane(lineGroup);
        
    }
    
    	
    
    public static void addLine(int id1, int id2, Color color){
        Platform.runLater(() -> {
            Punto punto1 = puntos[id1];
            Punto punto2 = puntos[id2];
            int x1 = punto1.getX();
            int y1 = punto1.getY();
            int x2 = punto2.getX();
            int y2 = punto2.getY();
            Line new_line = new Line(x1,y1,x2,y2);
            new_line.setFill(color);
            new_line.setStroke(color);
            lineGroup.getChildren().add(new_line);
        });
        
    }
    
    public static void setActivo(boolean valor){
        activo = valor;
    }
    
    public void setWindow(Stage window){
        this.window = window;
    }
    
    
    
}


