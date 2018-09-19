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

public class ServerController {

    public ListaSimple lista  = new ListaSimple(); //Variable estatica
    public Button okButton;
    public ColorPicker colorButton;
    public Button backButton;
    public TextField usernameTextF;
    public TextField serverIDTextF;
    public boolean booli;

    public ListaSimple getInfo() throws Exception{

        String username = usernameTextF.getText();
        String serverID = serverIDTextF.getText();

        lista.agregarAlInicio(username);
        lista.agregarAlInicio(serverID);

        return lista;
    }
}
