package InterfazJavaFX;

import Conectividad.Client;
import Interfaz.JuegoController;
import Interfaz.Punto;
import Matriz.ListaSimple;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PantallaEsperaController {

    private ListaSimple userDataList;
    private Stage thiswindow;
    private static Punto[] puntos = new Punto[64];
    private boolean activo = false;
    private JuegoController controlador;


    public void changeToMainScreenButtonPushed(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Menu.fxml"));
        Parent menuParent = loader.load();

        Scene menuScene = new Scene(menuParent);

        //access the controller and call a method
        MenuController controller = loader.getController();

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(menuScene);
        window.show();
    }
    
    @SuppressWarnings("empty-statement")
    public void ready(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Interfaz/Juego.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        ((JuegoController)loader.getController()).setUserDataList(userDataList);
        ((JuegoController)loader.getController()).createMyImage();
        ((JuegoController)loader.getController()).setMyName((String)userDataList.getValor(1));
        ((JuegoController)loader.getController()).setMyPoints("0");
        ((JuegoController)loader.getController()).setFoePoints("0");
        ((JuegoController)loader.getController()).setFoeName("Esperando\n Jugador");
                

        window.setScene(tableViewScene);
        window.setTitle("DOTS - Playing");
        window.show();
        
        
        
        Thread juego = new Thread(){
            public void run(){
                try {
                    new Client((String)userDataList.getValor(0), loader.getController());
                } catch (Exception ex) {
                    Logger.getLogger(PantallaEsperaController.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void setUserDataList(ListaSimple userDataList) {
        this.userDataList = userDataList;
    }

    public void setThiswindow(Stage thiswindow) {
        this.thiswindow = thiswindow;
    }

    public ListaSimple getUserDataList() {
        return userDataList;
    }


    public Stage getThiswindow() {
        return thiswindow;
    }
    
    public void closeRequest(Stage window) throws Exception{

        boolean booli = AlertWindow.display();
        if (booli == true){
            window.close();
            Client.close();
            
        }

    }
    
    

}
