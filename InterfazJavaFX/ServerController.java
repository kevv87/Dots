package InterfazJavaFX;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import Matriz.ListaSimple;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class ServerController {

    public ListaSimple lista  = new ListaSimple(); //Variable estatica

    public TextField usernameTextF;
    public TextField serverIDTextF;
    public boolean booli;

    public void getInfo(ActionEvent event) throws Exception{

        String username = usernameTextF.getText();
        String serverID = serverIDTextF.getText();

        lista.agregarAlInicio(username);
        lista.agregarAlInicio(serverID);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PantallaEspera.fxml"));
        Parent pantallaEsperaParent = loader.load();

        Scene pantallaEsperaScene = new Scene(pantallaEsperaParent);

        //access the controller and call a method
        PantallaEsperaController controller = loader.getController();

        controller.userDataList = lista;
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

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
