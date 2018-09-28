package InterfazJavaFX;

import Matriz.ListaSimple;
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
        EventHandler<ActionEvent> select_image = (ActionEvent e) -> {
            String seleccion = ((MenuItem)e.getSource()).getText();
            menuButton.setText(seleccion); 
            switch(seleccion){
                case("Default"):
                    image = new Image(getClass().getResource("../img/characters/default.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Power Pig"):
                    image = new Image(getClass().getResource("../img/characters/powerPig.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Obama"):
                    image = new Image(getClass().getResource("../img/characters/obama.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Doctor Tickles"):
                    image = new Image(getClass().getResource("../img/characters/doctorTickles.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Stereo Head"):
                    image = new Image(getClass().getResource("../img/characters/stereoHead.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Pink Gum"):
                    image = new Image(getClass().getResource("../img/characters/pinkgum.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Samus"):
                    image = new Image(getClass().getResource("../img/characters/samus.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Potato"):
                    image = new Image(getClass().getResource("../img/characters/potato.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("me.jpg"):
                    image = new Image(getClass().getResource("../img/characters/me.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Beer-o-saurus"):
                    image = new Image(getClass().getResource("../img/characters/beerosaurus.jpg").toExternalForm());
                    image_label.setImage(image);
                    break;
                case("Red Samurai"):
                    image = new Image(getClass().getResource("../img/characters/redSamurai.jpg").toExternalForm());
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
        
        String username = usernameTextF.getText();
        String serverID = serverIDTextF.getText();

        lista.agregarAlInicio(username);
        lista.agregarAlInicio(serverID);
        lista.insertarPorPosicion(2, image);
        System.out.println(((Image)lista.getValor(2)).getWidth());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PantallaEspera.fxml"));
        Parent pantallaEsperaParent = loader.load();

        Scene pantallaEsperaScene = new Scene(pantallaEsperaParent);

        //access the controller and call a method
        PantallaEsperaController controller = loader.getController();

        controller.setUserDataList(lista);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        controller.setThiswindow(window);

        window.setScene(pantallaEsperaScene);
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
    
    

}