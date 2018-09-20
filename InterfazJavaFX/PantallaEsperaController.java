package InterfazJavaFX;

import Matriz.ListaSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PantallaEsperaController {

    ListaSimple userDataList;
    ListaSimple oponentDataList;


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

}
