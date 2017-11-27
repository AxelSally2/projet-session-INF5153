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
package menucontroleur;

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
 */
public class ChoisirAdversaireControleur implements Initializable {

    @FXML
    AnchorPane paneChoisirAdversaire;

    public static int joueurChoisie;

    @FXML
    private void buttonJoueurHumain(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/Partie.fxml"));
        paneChoisirAdversaire.getChildren().setAll(pane);
        joueurChoisie = 1;
    }

    @FXML
    private void buttonJoueurIADebutant(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/Partie.fxml"));
        paneChoisirAdversaire.getChildren().setAll(pane);
        joueurChoisie = 2;
    }

    @FXML
    private void buttonJoueurIAAvance(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/Partie.fxml"));
        paneChoisirAdversaire.getChildren().setAll(pane);
        joueurChoisie = 3;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
