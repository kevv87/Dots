package Interfaz;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {

    static boolean booli; //Variable estatica

    public static boolean display(){

        //CREO VENTANA
        Stage window = new Stage();
        window.setTitle("DATOS DE CONECCIÓN");
        window.initModality(Modality.APPLICATION_MODAL);//ALERT MESSAGE, HASTA QUE NO CIERRE VENTANA NO PUEDE UTILIZAR OTRAS VENTANAS

        //CREO LAYOUT
        VBox layout = new VBox(20);

        //CREO SCENE
        Scene scene = new Scene(layout,300,300);

        //CREO ELEMENTOS DE VENTANA
        Label label1 = new Label();
        label1.setText("Seguro que desea salir?");
        Button button1 = new Button("YES");
        Button button2 = new Button("NO");

        //UNION DE BOTON CON FUNCIONES/VALORES DE RETORNO
        button1.setOnAction(e -> {
            booli = true;
            window.close();
        });

        button2.setOnAction(e -> {
            booli = false;
            window.close();
        });

        //AÑADO AL LAYOUT
        layout.getChildren().addAll(label1,button1,button2);

        //ASIGNO VENTANA QUE MUESTRE SCENE
        window.setScene(scene);
        window.showAndWait();

        return booli;
    }



}
