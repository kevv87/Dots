package InterfazJavaFX;

import Conectividad.Client;
import Matriz.ListaSimple;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PantallaEsperaController {

    private ListaSimple userDataList;
    private ListaSimple oponentDataList;
    private Stage thiswindow;


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
    
    @SuppressWarnings("empty-statement")
    public void ready(ActionEvent event) throws Exception {
        Thread juego = new Thread(){
            public void run(){
                try {
                    new Client((String)userDataList.getValor(0));
                } catch (Exception ex) {
                    Logger.getLogger(PantallaEsperaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        juego.start();
        thiswindow.close();
    }

    public void setUserDataList(ListaSimple userDataList) {
        this.userDataList = userDataList;
    }

    public void setOponentDataList(ListaSimple oponentDataList) {
        this.oponentDataList = oponentDataList;
    }

    public void setThiswindow(Stage thiswindow) {
        this.thiswindow = thiswindow;
    }

    public ListaSimple getUserDataList() {
        return userDataList;
    }

    public ListaSimple getOponentDataList() {
        return oponentDataList;
    }

    public Stage getThiswindow() {
        return thiswindow;
    }
    
    

}
