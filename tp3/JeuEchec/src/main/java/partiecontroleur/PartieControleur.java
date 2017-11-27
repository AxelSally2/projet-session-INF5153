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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import jeu.Jeu;
import menucontroleur.MenuPrincipalControleur;
import piece.Cavalier;
import piece.Dame;
import piece.Fou;
import piece.Piece;
import piece.Pion;
import piece.Roi;
import piece.Tour;
import fichier.EnregistrerFichier;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import menucontroleur.ChoisirAdversaireControleur;

/**
 * FXML Controller class
 */
public class PartieControleur implements Initializable {

    private int row;
    private int col;
    private int cpt = 0;

    @FXML
    private Button case00, case01, case02, case03, case04, case05, case06, case07,
            case10, case11, case12, case13, case14, case15, case16, case17,
            case20, case21, case22, case23, case24, case25, case26, case27,
            case30, case31, case32, case33, case34, case35, case36, case37,
            case40, case41, case42, case43, case44, case45, case46, case47,
            case50, case51, case52, case53, case54, case55, case56, case57,
            case60, case61, case62, case63, case64, case65, case66, case67,
            case70, case71, case72, case73, case74, case75, case76, case77;

    @FXML
    AnchorPane panePartie;

    private Jeu partie;

    private Jeu initialiserPartie() throws FileNotFoundException {
        partie = new Jeu();
        if (MenuPrincipalControleur.partieChoisie == 1) {
            partie.getTable().initialiserNouvelleTable();
        } else if (MenuPrincipalControleur.partieChoisie == 2) {
            partie.getTable().initialiserTableSauvegarder();
        }
        return partie;
    }

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

    private void afficherPieces() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (partie.getTable().getPiece(row, col) instanceof Roi && partie.getTable().getPiece(row, col).getCouleur().equals("Blanc")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/roi_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Dame && partie.getTable().getPiece(row, col).getCouleur().equals("Blanc")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/dame_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Fou && partie.getTable().getPiece(row, col).getCouleur().equals("Blanc")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/fou_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Cavalier && partie.getTable().getPiece(row, col).getCouleur().equals("Blanc")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/cavalier_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Tour && partie.getTable().getPiece(row, col).getCouleur().equals("Blanc")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/tour_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Pion && partie.getTable().getPiece(row, col).getCouleur().equals("Blanc")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/pion_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Roi && partie.getTable().getPiece(row, col).getCouleur().equals("Noir")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/roi_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Dame && partie.getTable().getPiece(row, col).getCouleur().equals("Noir")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/dame_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Fou && partie.getTable().getPiece(row, col).getCouleur().equals("Noir")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/fou_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Cavalier && partie.getTable().getPiece(row, col).getCouleur().equals("Noir")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/cavalier_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Tour && partie.getTable().getPiece(row, col).getCouleur().equals("Noir")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/tour_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Pion && partie.getTable().getPiece(row, col).getCouleur().equals("Noir")) {
                    table()[row][col].setStyle("-fx-background-image: url('/images/pion_noir.png');");
                } else {
                    table()[row][col].setStyle("");
                }
            }
        }
    }

    private void deplacerPiece(int rowValue, int colValue) {
        cpt++;
        if (cpt == 1) {
            if (partie.getTable().getPiece(rowValue, colValue) == null) {
                cpt = 0;
            } else {
                row = rowValue;
                col = colValue;
            }
        } else if (cpt == 2) {
            if (partie.getTable().getPiece(row, col).estDeplacementValide(rowValue, colValue)) {
                partie.getTable().setPiece(rowValue, colValue, partie.getTable().getPiece(row, col));
                partie.getTable().getPiece(rowValue, colValue).setRow(rowValue);
                partie.getTable().getPiece(rowValue, colValue).setCol(colValue);
                partie.getTable().setPiece(row, col, null);
                remplacerPionParDame(partie.getTable().getPiece(rowValue, colValue));
            }
            afficherPieces();
            cpt = 0;
        }
    }

    private void remplacerPionParDame(Piece piece) {
        if (piece instanceof Pion) {
            if (piece.getCouleur().equals("Blanc") && piece.getRow() == 0) {
                partie.getTable().setPiece(piece.getRow(), piece.getCol(), new Dame(piece.getCouleur(), piece.getRow(), piece.getCol()));
            } else if (piece.getCouleur().equals("Noir") && piece.getRow() == 7) {
                partie.getTable().setPiece(piece.getRow(), piece.getCol(), new Dame(piece.getCouleur(), piece.getRow(), piece.getCol()));
            }
        }
    }

    @FXML
    private void case00Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 0);
    }

    @FXML
    private void case01Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 1);
    }

    @FXML
    private void case02Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 2);
    }

    @FXML
    private void case03Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 3);
    }

    @FXML
    private void case04Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 4);
    }

    @FXML
    private void case05Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 5);
    }

    @FXML
    private void case06Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 6);
    }

    @FXML
    private void case07Action(ActionEvent event) throws IOException {
        deplacerPiece(0, 7);
    }

    @FXML
    private void case10Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 0);
    }

    @FXML
    private void case11Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 1);
    }

    @FXML
    private void case12Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 2);
    }

    @FXML
    private void case13Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 3);
    }

    @FXML
    private void case14Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 4);
    }

    @FXML
    private void case15Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 5);
    }

    @FXML
    private void case16Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 6);
    }

    @FXML
    private void case17Action(ActionEvent event) throws IOException {
        deplacerPiece(1, 7);
    }

    @FXML
    private void case20Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 0);
    }

    @FXML
    private void case21Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 1);
    }

    @FXML
    private void case22Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 2);
    }

    @FXML
    private void case23Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 3);
    }

    @FXML
    private void case24Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 4);
    }

    @FXML
    private void case25Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 5);
    }

    @FXML
    private void case26Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 6);
    }

    @FXML
    private void case27Action(ActionEvent event) throws IOException {
        deplacerPiece(2, 7);
    }

    @FXML
    private void case30Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 0);
    }

    @FXML
    private void case31Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 1);
    }

    @FXML
    private void case32Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 2);
    }

    @FXML
    private void case33Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 3);
    }

    @FXML
    private void case34Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 4);
    }

    @FXML
    private void case35Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 5);
    }

    @FXML
    private void case36Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 6);
    }

    @FXML
    private void case37Action(ActionEvent event) throws IOException {
        deplacerPiece(3, 7);
    }

    @FXML
    private void case40Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 0);
    }

    @FXML
    private void case41Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 1);
    }

    @FXML
    private void case42Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 2);
    }

    @FXML
    private void case43Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 3);
    }

    @FXML
    private void case44Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 4);
    }

    @FXML
    private void case45Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 5);
    }

    @FXML
    private void case46Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 6);
    }

    @FXML
    private void case47Action(ActionEvent event) throws IOException {
        deplacerPiece(4, 7);
    }

    @FXML
    private void case50Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 0);
    }

    @FXML
    private void case51Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 1);
    }

    @FXML
    private void case52Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 2);
    }

    @FXML
    private void case53Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 3);
    }

    @FXML
    private void case54Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 4);
    }

    @FXML
    private void case55Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 5);
    }

    @FXML
    private void case56Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 6);
    }

    @FXML
    private void case57Action(ActionEvent event) throws IOException {
        deplacerPiece(5, 7);
    }

    @FXML
    private void case60Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 0);
    }

    @FXML
    private void case61Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 1);
    }

    @FXML
    private void case62Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 2);
    }

    @FXML
    private void case63Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 3);
    }

    @FXML
    private void case64Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 4);
    }

    @FXML
    private void case65Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 5);
    }

    @FXML
    private void case66Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 6);
    }

    @FXML
    private void case67Action(ActionEvent event) throws IOException {
        deplacerPiece(6, 7);
    }

    @FXML
    private void case70Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 0);
    }

    @FXML
    private void case71Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 1);
    }

    @FXML
    private void case72Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 2);
    }

    @FXML
    private void case73Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 3);
    }

    @FXML
    private void case74Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 4);
    }

    @FXML
    private void case75Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 5);
    }

    @FXML
    private void case76Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 6);
    }

    @FXML
    private void case77Action(ActionEvent event) throws IOException {
        deplacerPiece(7, 7);
    }

    @FXML
    private void buttonEnregistrerPartie(ActionEvent event) throws IOException {
        EnregistrerFichier fichier = new EnregistrerFichier();
        fichier.sauvegarderDansFichier(partie.getTable().tableToXML(), "XML files (*.xml)", "*.xml");


    }

    @FXML
    private void buttonVisualiserPartieTermine(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/VisualiserPartie.fxml"));
        panePartie.getChildren().setAll(pane);
    }

    @FXML
    private void buttonRetourMenu(ActionEvent event) throws IOException {
        MenuPrincipalControleur.partieChoisie = 0;
        ChoisirAdversaireControleur.joueurChoisie = 0;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fenetre/MenuPrincipal.fxml"));
        panePartie.getChildren().setAll(pane);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initialiserPartie();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PartieControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherPieces();
    }

}
