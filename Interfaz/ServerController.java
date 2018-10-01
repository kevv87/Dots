package Interfaz;

import Conectividad.Client;
import Interfaz.JuegoController;
import Matriz.ListaSimple;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ServerController {

    public ListaSimple lista  = new ListaSimple(); //Variable estatica

    public TextField usernameTextF;
    public TextField serverIDTextF;
    public boolean booli;
    private String image_url;
    
    @FXML
    private MenuButton menuButton;
    @FXML
    private ImageView image_label;
    @FXML
    private MenuItem Default;
    @FXML
    private MenuItem redSamurai;
    @FXML
    private MenuItem stereoHead;
    @FXML
    private MenuItem samus;
    @FXML
    private MenuItem powerPig;
    @FXML
    private MenuItem pinkGum;
    @FXML
    private MenuItem obama;
    @FXML
    private MenuItem dTickle;
    @FXML
    private MenuItem potato;
    @FXML
    private MenuItem beero;
    @FXML
    private MenuItem me;
    
    private Image image;
    
    public void initialize(){
        
        serverIDTextF.setText("localhost");
        menuButton.setText("Default");
        image_url = "../img/characters/default.jpg";
        image_label.setImage(new Image(getClass().getResource(image_url).toExternalForm()));
        EventHandler<ActionEvent> select_image = (ActionEvent e) -> {
            String seleccion = ((MenuItem)e.getSource()).getText();
            menuButton.setText(seleccion); 
            switch(seleccion){
                case("Default"):
                    image_url = "../img/characters/default.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Power Pig"):
                    image_url = "../img/characters/powerpig.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Obama"):
                    image_url = "../img/characters/obama.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Doctor Tickles"):
                    image_url = "../img/characters/doctorTickles.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Stereo Head"):
                    image_url = "../img/characters/stereoHead.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Pink Gum"):
                    image_url = "../img/characters/pinkgum.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Samus"):
                    image_url = "../img/characters/samus.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Potato"):
                    image_url = "../img/characters/potato.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("me.jpg"):
                    image_url = "../img/characters/me.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Beer-o-saurus"):
                    image_url = "../img/characters/beerosaurus.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Red Samurai"):
                    image_url = "../img/characters/redSamurai.jpg";
                    image = new Image(getClass().getResource(image_url).toExternalForm());
                    image_label.setImage(image);
                    break; 
            }
        };
        Default.setOnAction(select_image);
        redSamurai.setOnAction(select_image);
        stereoHead.setOnAction(select_image);
        samus.setOnAction(select_image);
        obama.setOnAction(select_image);
        dTickle.setOnAction(select_image);
        potato.setOnAction(select_image);
        beero.setOnAction(select_image);
        me.setOnAction(select_image);
        powerPig.setOnAction(select_image);
        pinkGum.setOnAction(select_image);
        
    }
    
    public void getInfo(ActionEvent event) throws Exception{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Interfaz/Juego.fxml"));
        Parent tableViewParent = loader.load();
        JuegoController controller = loader.getController();

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        String username = usernameTextF.getText();
        String serverID = serverIDTextF.getText();

        lista.agregarAlInicio(username);
        lista.agregarAlInicio(serverID);
        lista.insertarPorPosicion(2, image);
        lista.insertarPorPosicion(3, image_url);

        controller.setUserDataList(lista);
        
        ((JuegoController)loader.getController()).setUserDataList(lista);
        ((JuegoController)loader.getController()).createMyImage();
        ((JuegoController)loader.getController()).setMyName((String)lista.getValor(1));
        ((JuegoController)loader.getController()).setMyPoints(0);
        ((JuegoController)loader.getController()).setFoePoints(0);
        ((JuegoController)loader.getController()).setFoeName("Esperando\n Jugador");
      
        Thread juego = new Thread(){
            public void run(){
                try {
                    new Client((String)lista.getValor(0), loader.getController());
                } catch (Exception ex) {
                    Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        juego.start();
        
        window.setOnCloseRequest(e -> {
            e.consume();
            try {
                closeRequest(window);
            } catch (Exception ex) {
                Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        window.setScene(tableViewScene);
        window.setTitle("DOTS - Playing");
        window.show();
        
    }



    public void changeToMainScreenButtonPushed(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent menuParent = loader.load();

        Scene menuScene = new Scene(menuParent);

        //access the controller and call a method
        MenuController controller = loader.getController();
        controller.userDataList = lista;
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(menuScene);
        window.show();
    }
    
    public void closeRequest(Stage window) throws Exception{

        boolean bool = AlertWindow.display();
        if (bool == true){
            window.close();
            Client.close();
            
        }

    }
    
    

}
