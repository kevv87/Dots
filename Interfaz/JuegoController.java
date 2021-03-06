/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Conectividad.Client;
import Figuras.LinkedList;
import Matriz.ListaSimple;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javafx.scene.paint.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kevv87
 */
public class JuegoController {
    // Elementos del FXML necesario
    @FXML
    private Pane gamePane;
    @FXML
    private ImageView my_image;
    @FXML
    private Label my_name;
    @FXML
    private Label my_points;
    @FXML
    private Label foePoints;
    @FXML
    private Label foeName;
    @FXML
    private ImageView foeImage;
    private Stage myStage;
    
    
    private Group lineGroup = new Group();  //Grupo de lineas para representar 
    private static Group circles = new Group(); //Grupo de circulos para representar
    private static Group areas = new Group();
    private static Punto[] puntos = new Punto[64];  //Grupo de puntos para logica
    private static boolean activo = false; 
    private final ListaSimple puntos_a_enviar = new ListaSimple();
    private ListaSimple userDataList;
    
    /**
     * Agregar un nuevo grupo al Pane del juego
     * @param group Grupo a agregar
     */
    public void addtoPane(Group group){
        gamePane.getChildren().add(group);
    }

    
    /**
     * Manejador de los eventos del click
     * @param event Evento de click
     */
    @FXML public void Handle(MouseEvent event) {
        if(!activo){
            return;
        }
        
        int x = (int) event.getX();  // Consigue la coordenada en x de donde se origina el evento
        int y = (int) event.getY();  // Consigue la coordenada en y de donde se origina el evento
        
        
        for(Punto punto:puntos){  // Para cada punto en los puntos de la pantalla
            if(punto.contiene(x,y) /**&& !punto.isBloqueado()*/){  try {
                Boolean valido = false;  // Guarda si el nuevo punto es valido de conectar con el actual
                // si el punto contiene a la coordenada donde se clickea
                if(puntos_a_enviar.getTamanio() == 1){  // Si ya se tiene un punto preparado para enviar, entonces...
                    int diferencia = Math.abs(((Punto)puntos_a_enviar.getValor(0)).getId() - punto.getId());  // Valor absoluto de la diferencia de los ids de los puntos
                    if(diferencia == 1 || (diferencia>=7 && diferencia <= 9)){  // Si la diferencia entre los puntos es uno o esta entre 7 y 9
                        
                        // Casos especiales al borde de la pantalla
                        if(((Punto)puntos_a_enviar.getValor(0)).getId()%8==0){  // Borde izquierdo
                            switch(((Punto)puntos_a_enviar.getValor(0)).getId()-punto.getId()){
                                        case(1):
                                            valido = false;
                                            break;
                                        case(-7):
                                            valido = false;
                                            break;
                                        case(9):
                                            valido = false;
                                            break;
                                        default:
                                            valido = true;
                                            break;
                            }
                        }else if((((Punto)puntos_a_enviar.getValor(0)).getId()+1)%8==0){  // Borde derecho
                            switch(((Punto)puntos_a_enviar.getValor(0)).getId()-punto.getId()){
                                        case(-1):
                                            valido = false;
                                            break;
                                        case(7):
                                            valido = false;
                                            break;
                                        case(-9):
                                            valido = false;
                                            break;
                                        default:
                                            valido = true;
                                            break;
                            }
                        }else{
                            valido = true;
                        }
                    }else{
                        valido = false;
                    }
                    if(valido){  // Si es valido dibujar una recta, entonces...
                            puntos_a_enviar.agregarAlInicio(punto);
                        
                            // Ahora convierte y lo envia
                            String id1 = toOct(((Punto)puntos_a_enviar.getValor(0)).getId());
                            String id2 = toOct(((Punto)puntos_a_enviar.getValor(1)).getId());
                            String msj = id1+","+id2;
                            Client.send_game(msj);
                            //Limpia
                            puntos_a_enviar.eliminar();
                            for(Punto punto2:puntos){  // Quita los halos de los puntos
                                ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                            }
                    }else{ // Si no es valido
                        for(Punto punto2:puntos){
                            if(Math.abs(punto2.getId()-punto.getId())==1 || (Math.abs(punto2.getId()-punto.getId())<=9 && Math.abs(punto2.getId()-punto.getId())>=7)){
                                if(punto.getId()%8==0){  // Casos borde de la pantalla
                                    switch(punto.getId()-punto2.getId()){
                                        case(1):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);  // Quita halo
                                            break;
                                        case(-7):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(9):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        default:
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.WHITE);  // Agrega halo
                                            break;
                                    }
                                } else if((punto.getId()+1)%8==0){
                                    switch(punto.getId()-punto2.getId()){
                                        case(-1):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(7):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(-9):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        default:
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.WHITE);
                                            break;
                                    }
                                }else{  // Caso normal
                                    ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.WHITE);
                                }
                                
                            }else{
                                ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                            }
                        }
                        puntos_a_enviar.eliminar();
                        puntos_a_enviar.agregarAlInicio(punto);
                        }
                    
                    
                    
                }else{  // En caso de no tener un punto preparado para enviar
                    for(Punto punto2:puntos){
                            if(Math.abs(punto2.getId()-punto.getId())==1 || (Math.abs(punto2.getId()-punto.getId())<=9 && Math.abs(punto2.getId()-punto.getId())>=7)){
                                if(punto.getId()%8==0){
                                    switch(punto.getId()-punto2.getId()){
                                        case(1):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(-7):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(9):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        default:
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.WHITE);
                                            break;
                                    }
                                } else if((punto.getId()+1)%8==0){
                                    switch(punto.getId()-punto2.getId()){
                                        case(-1):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(7):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        case(-9):
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                                            break;
                                        default:
                                            ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.WHITE);
                                            break;
                                    }
                                }else{
                                    ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.WHITE);
                                }
                                
                            }else{
                                ((Circle)circles.getChildren().get(punto2.getId())).setStroke(Color.BLACK);
                            }
                        }
                    puntos_a_enviar.agregarAlInicio(punto);
                }
                    
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Convierte un decimal a octario para poder enviarlo al server sin complicaciones
     */
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
    
    /**
     *  Metodo que se ejecuta al crear la ventana
     * @throws java.lang.Exception
     */
    public void initialize() throws Exception{
         //Dibujando circulos
        Platform.setImplicitExit(false);
        double x = 20;
        double y = 20;
        int radio = 5;
        int espacio = 50;
        int cont = 0;
        
        //Dibuja circulos y los agrega al array
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Circle circle = new Circle();
                circle.setCenterX(x);
                circle.setCenterY(y);
                circle.setRadius(radio);
                puntos[cont] = new Punto(x,y,cont);
                
                x = x+radio+espacio;
                circles.getChildren().add(circle);
                
                
                cont++;
            }
            x = 20;
            y = y + radio + espacio;
        }
        addtoPane(circles);
        addtoPane(lineGroup);
        addtoPane(areas);

        setFoePoints(0);
        
    }
    
    /**
     * Metodo que agrega una linea a la interfaz
     * 
     * @param id1 Id del primer punto
     * @param id2 Id del segundo punto
     * @param color Color de la linea
     */
    public void addLine(int id1, int id2, Color color){
        Platform.runLater(() -> {
            Punto punto1 = puntos[id1];
            Punto punto2 = puntos[id2];
            Double x1 = punto1.getX();
            Double y1 = punto1.getY();
            Double x2 = punto2.getX();
            Double y2 = punto2.getY();
            Line new_line = new Line(x1,y1,x2,y2);
            new_line.setFill(color);
            new_line.setStroke(color);
            lineGroup.getChildren().add(new_line);
        });
        
    }
    
    /**
     * Crear la imagen del jugador en la interfaz
     * @throws java.lang.Exception
     */
    public void createMyImage() throws Exception{
        System.out.println(userDataList.getTamanio());
        my_image.setImage((Image)userDataList.getValor(2));
    }
    
    
    // Setters & Getters
    public static void setActivo(boolean valor){
        activo = valor;
    }
    
    public void setUserDataList(ListaSimple userDataList){
        this.userDataList = userDataList;
    }
    
    public void setMyName(String text){
        my_name.setText(text);
    }
    
    public void setMyPoints(int points){
        Platform.runLater(() -> {
            my_points.setText(Integer.toString(points));
        });
        
    }
    
    public void setFoePoints(int points){
        Platform.runLater(() -> {
            foePoints.setText(Integer.toString(points));
        });
        
    }
    
    public void setFoeName(String name) {

        Platform.runLater(() -> {
            foeName.setText(name);
        });
        
    }
    
    public void setFoeImage(String url){
        System.out.println(url);
        Platform.runLater(() -> {
            foeImage.setImage(new Image((getClass().getResource(url)).toExternalForm()));
        });
    }

    public ListaSimple getUserDataList() {
        return userDataList;
    }
    
    
    /**
     * Hace un nuevo poligono a partir de los puntos en la lista de ids que se le pasa
     * @param ids Lista de ids de los puntos que forman el poligono, acomodados en sentido horario o antihorario 
     * @return  Lista de puntos dentro del perimetro
     * @throws java.lang.Exception 
     */
    public LinkedList<Punto> addPolygon(ListaSimple ids) throws Exception{
        LinkedList<Punto> puntos_dentro = new LinkedList<>();
        Platform.runLater(() -> {
            Polygon new_polygon = new Polygon();
        while(ids.getTamanio() != 0){
                    Punto punto;
                    try {
                        punto = puntos[(int)ids.getValor(0)];
                        
                        new_polygon.getPoints().addAll(punto.getX(), punto.getY());
                        ids.removerPorPosicion(0);
                    } catch (Exception ex) {
                        Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
            System.out.println("Drawing");
            new_polygon.setFill(Color.BLACK);
            new_polygon.setStroke(Color.BLACK);
            areas.getChildren().add(new_polygon);
            System.out.println(areas.getChildren().get(0));
            
            ids.listar();
            for(Punto punto:puntos){  //Verifica los puntos que estan dentro del Poligono
                if(new_polygon.contains(punto.getX(), punto.getY())){
                    boolean alternador = true;
                    boolean agregarx = true;
                    boolean agregary = true;
                    boolean agregar = true;
                    for(Double coordenada:new_polygon.getPoints()){
                        if(alternador){
                            if(punto.getX() == coordenada){
                                agregarx = false;
                            }
                        }else{
                            if(punto.getY()==coordenada){
                                agregary = false;
                            }
                            if(!(agregarx && agregary)){
                                agregar = false;
                            }else{
                                agregar = true;
                            }
                        }
                        alternador = !alternador;
                    }
                    
                    if(agregar){
                        System.out.println(punto.getId());
                        puntos_dentro.anadirInicio(punto);
                        punto.setBloqueado(true);  //Bloquea los puntos que va agregando
                    }
                }
            }
        });
        
        return puntos_dentro;
        
        
        
    }
    
    public int getMyPoints(){
        return Integer.parseInt(my_points.getText());
    }
    public int getFoePoints(){
        return Integer.parseInt(foePoints.getText());
    }
    
    public void openWin(){
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Win.fxml"));
            Parent menuParent;
            try {
                menuParent = loader.load();
                Scene menuScene = new Scene(menuParent);
                //This line gets the Stage information
                Stage window = this.myStage;

                window.setScene(menuScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void openLose(){
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Lose.fxml"));
            Parent menuParent;
            try {
                menuParent = loader.load();
                Scene menuScene = new Scene(menuParent);
                //This line gets the Stage information
                Stage window = this.myStage;

                window.setScene(menuScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
   
    
    public void setStage(Stage stage){
        this.myStage = stage;
    }
    
    
}


