package InterfazJavaFX;

import Matriz.ListaSimple;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectWindow {

    public ListaSimple datos;

    public ListaSimple display() throws Exception{

        //CREO VENTANA
        Stage window = new Stage();
        window.setTitle("DATOS DE CONECCIÓN");
        window.initModality(Modality.APPLICATION_MODAL);//ALERT MESSAGE, HASTA QUE NO CIERRE VENTANA NO PUEDE UTILIZAR OTRAS VENTANAS
        Parent root = FXMLLoader.load(getClass().getResource("ServerPanel.fxml"));

        //CREO SCENE
        Scene scene = new Scene(root,250,250);

        /**
*/
        //ASIGNO VENTANA QUE MUESTRE SCENE

        window.setScene(scene);
        window.showAndWait();



       // Parent root = loader.load();

        //controller.lista.listar();


        return datos;

    }

}
