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
import jeu.SingletonPartie;

/**
 * FXML Controller class
 */
public class MeilleursTempsControleur implements Initializable {

    private final SingletonPartie partie = SingletonPartie.getInstance();

    @FXML
    private AnchorPane paneMeilleursTemps;

    @FXML
    private Label meilleurTempsDebutant;

    @FXML
    private Label meilleurTempsAvance;

    @FXML
    private Label nomIADebutant;

    @FXML
    private Label nomIAAvance;

    @FXML
    private void buttonRetourMenu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/MenuPrincipal.fxml"));
        paneMeilleursTemps.getChildren().setAll(pane);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partie.getFacade().initMeilleurTemps(meilleurTempsDebutant, meilleurTempsAvance, nomIADebutant, nomIAAvance);
    }

}
