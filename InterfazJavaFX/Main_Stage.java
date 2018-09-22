package InterfazJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Main_Stage extends Application implements EventHandler<ActionEvent>{

    public static void iniciar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Stage window;

    public static void main(String[] args){
        launch(args);
    }
    
    public void Main_Stage(){
        launch();
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
