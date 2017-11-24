package menucontroleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipalControleur implements Initializable {

    @FXML
    private Label label;
    
    @FXML 
    AnchorPane paneMenu;

    @FXML
    private void buttonNouvellePartie(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/ChoisirAdversaire.fxml"));
        paneMenu.getChildren().setAll(pane);
    }

    @FXML
    private void buttonChargerPartie(ActionEvent event) {
        System.out.println("TODO!");
        label.setText("TODO!");
    }

    @FXML
    private void buttonConsulterTemps(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/MeilleursTemps.fxml"));
        paneMenu.getChildren().setAll(pane);
    }

    @FXML
    private void buttonQuitter(ActionEvent event) {
        System.exit(0);
    }

//    @FXML
//    private void buttonConsulterTemps(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
