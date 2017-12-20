/*
 * Copyright 2017 Jean-Michel Poirier et Audrey Eugene.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 */
public class MenuPrincipalControleur implements Initializable {

    @FXML
    private Label label;

    @FXML
    private AnchorPane paneMenu;

    public static int partieChoisie;

    @FXML
    private void buttonNouvellePartie(ActionEvent event) throws IOException {
        partieChoisie = 1;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/ChoisirAdversaire.fxml"));
        paneMenu.getChildren().setAll(pane);
    }

    @FXML
    private void buttonChargerPartie(ActionEvent event) throws IOException {
        partieChoisie = 2;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/Partie.fxml"));
        paneMenu.getChildren().setAll(pane);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
