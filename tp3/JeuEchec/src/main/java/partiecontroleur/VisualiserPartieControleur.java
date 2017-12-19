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
package partiecontroleur;

import jeu.FacadePartie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import jeu.SingletonPartie;

/**
 * FXML Controller class
 */
public class VisualiserPartieControleur implements Initializable {

    private int pos = 1;

    @FXML
    AnchorPane paneVisualiserPartie;
    
    private final SingletonPartie partie = SingletonPartie.getInstance();

    @FXML
    private Button case00, case01, case02, case03, case04, case05, case06, case07,
            case10, case11, case12, case13, case14, case15, case16, case17,
            case20, case21, case22, case23, case24, case25, case26, case27,
            case30, case31, case32, case33, case34, case35, case36, case37,
            case40, case41, case42, case43, case44, case45, case46, case47,
            case50, case51, case52, case53, case54, case55, case56, case57,
            case60, case61, case62, case63, case64, case65, case66, case67,
            case70, case71, case72, case73, case74, case75, case76, case77;

    private Button[][] table() {
        Button table[][] = {{case00, case01, case02, case03, case04, case05, case06, case07},
        {case10, case11, case12, case13, case14, case15, case16, case17},
        {case20, case21, case22, case23, case24, case25, case26, case27},
        {case30, case31, case32, case33, case34, case35, case36, case37},
        {case40, case41, case42, case43, case44, case45, case46, case47},
        {case50, case51, case52, case53, case54, case55, case56, case57},
        {case60, case61, case62, case63, case64, case65, case66, case67},
        {case70, case71, case72, case73, case74, case75, case76, case77}};
        return table;
    }

    @FXML
    private void buttonNextMouv(ActionEvent event) throws IOException {
        pos = partie.getFacade().getPartie().getMouvement().mouvementSuivant(pos);
        partie.getFacade().getPartie().setTable(partie.getFacade().getPartie().getMouvement().getTable());
        partie.getFacade().afficherPieces(table());
    }

    @FXML
    private void buttonPreviousMouv(ActionEvent event) throws IOException {
        pos = partie.getFacade().getPartie().getMouvement().mouvementPrecedent(pos);
        partie.getFacade().getPartie().setTable(partie.getFacade().getPartie().getMouvement().getTable());
        partie.getFacade().afficherPieces(table());
    }

    @FXML
    private void buttonRetourMenu(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/MenuPrincipal.fxml"));
        paneVisualiserPartie.getChildren().setAll(pane);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partie.getFacade().getPartie().getTable().initialiserNouvelleTable();
        partie.getFacade().afficherPieces(table());
    }

}
