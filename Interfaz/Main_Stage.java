package Interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Main_Stage extends Application{

    Stage window;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        window = primaryStage;
        window.setTitle("DOTS");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeRequest();
        });
        window.setScene(new Scene(root, 500,400));
        window.show();
    }


    public void closeRequest(){
        boolean booli = AlertWindow.display();
        if (booli == true){
            window.close();
        }

    }

}
