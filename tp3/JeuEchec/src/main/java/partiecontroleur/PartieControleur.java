/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partiecontroleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jmppr
 */
public class PartieControleur implements Initializable {

    @FXML
    AnchorPane panePartie;

    @FXML
    private void buttonEnregistrerPartie(ActionEvent event) throws IOException {

    }

    @FXML
    private void buttonVisualiserPartieTermine(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/VisualiserPartie.fxml"));
        panePartie.getChildren().setAll(pane);
    }

    @FXML
    private void buttonRetourMenu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/MenuPrincipal.fxml"));
        panePartie.getChildren().setAll(pane);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
