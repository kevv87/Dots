package InterfazJavaFX;

import Matriz.ListaSimple;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu implements EventHandler<ActionEvent> {

    Button button1;
    Button button2;
    Button button3;
    VBox layout = new VBox(30);
    Scene scene = new Scene(layout,300,300);

    public Menu () {

        button1 = new Button();
        button1.setText("PLAY");

        button2 = new Button();
        button2.setText("HALL OF FAME");

        button3 = new Button();
        button3.setText("HELP");

    }

    public void start(){

        button1.setOnAction(e -> System.out.println("heynow"));
        button2.setOnAction(e -> {
            ConnectWindow ventana = new ConnectWindow();
            ListaSimple lista = ventana.display();
            System.out.println("lista: " + lista);
        });
        button3.setOnAction(this);

        layout.getChildren().addAll(button1,button2,button3);

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource()==button1){
            System.out.println("JUEGO");
        } else if (event.getSource()==button2){
            System.out.println("SALON DE FAMA");
        } else {
            System.out.println("HELP");
        }
    }
}
