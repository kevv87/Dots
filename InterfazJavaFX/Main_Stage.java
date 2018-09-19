package InterfazJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main_Stage extends Application implements EventHandler<ActionEvent>{

    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("DOTS");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeRequest();
        });


        Menu menu = new Menu();
        menu.start();

        window.setScene(menu.scene);
        window.show();

    }

    @Override
    public void handle(ActionEvent event) {

    }

    public void closeRequest(){

        boolean booli = AlertWindow.display();
        if (booli == true){
            window.close();
        }

    }

}
