package InterfazJavaFX;

import Matriz.ListaSimple;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectWindow {

    static ListaSimple datos;

    public static ListaSimple display(){

        //CREO VENTANA
        Stage window = new Stage();
        window.setTitle("DATOS DE CONECCIÓN");
        window.initModality(Modality.APPLICATION_MODAL);//ALERT MESSAGE, HASTA QUE NO CIERRE VENTANA NO PUEDE UTILIZAR OTRAS VENTANAS

        //CREO LAYOUT
        VBox layout = new VBox(20);

        //CREO SCENE
        Scene scene = new Scene(layout,300,300);

        //CREO ELEMENTOS DE VENTANA
        Button button1 = new Button("Regresar");
        button1.setOnAction(e -> window.close());
        //AÑADO AL LAYOUT
        layout.getChildren().add(button1);

        //ASIGNO VENTANA QUE MUESTRE SCENE
        window.setScene(scene);
        window.showAndWait();

        return datos;

    }


}
