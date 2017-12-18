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

import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import jeu.Couleur;
import jeu.Jeu;
import jeu.Mouvement;
import jeu.Table;
import joueur.Humain;
import joueur.IAAvance;
import joueur.IADebutant;
import menucontroleur.ChoisirAdversaireControleur;
import menucontroleur.MenuPrincipalControleur;
import piece.*;

/**
 *
 * @author jmppr
 */
public class Singleton {

    static private Singleton instance = null;

    private Jeu partie;
    private int row;
    private int col;
    private int cpt;

    private Singleton() {
        cpt = 0;
    }

    static public Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public Jeu getModel() {
        return partie;
    }

    public Jeu getPartie() {
        return partie;
    }

    public void creerPartie() {
        if (ChoisirAdversaireControleur.joueurChoisie == 1) {
            partie = new Jeu(new Table(), new Humain(), new Humain(), new Mouvement());
        } else if (ChoisirAdversaireControleur.joueurChoisie == 2) {
            partie = new Jeu(new Table(), new Humain(), new IADebutant(), new Mouvement());
        } else if (ChoisirAdversaireControleur.joueurChoisie == 3) {
            partie = new Jeu(new Table(), new Humain(), new IAAvance(), new Mouvement());
        } else {
            partie = new Jeu(new Table(), new Humain(), new IADebutant(), new Mouvement());
        }
    }

    public void initialiserPartie() throws FileNotFoundException {
        if (MenuPrincipalControleur.partieChoisie == 1) {
            partie.getTable().initialiserNouvelleTable();
        } else if (MenuPrincipalControleur.partieChoisie == 2) {
            partie.getTable().initialiserTableSauvegarder();
        }
    }

    public void afficherPieces(Button[][] table) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (partie.getTable().getPiece(row, col) instanceof Roi && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.BLANC)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/roi_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Dame && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.BLANC)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/dame_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Fou && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.BLANC)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/fou_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Cavalier && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.BLANC)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/cavalier_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Tour && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.BLANC)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/tour_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Pion && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.BLANC)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/pion_blanc.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Roi && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.NOIR)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/roi_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Dame && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.NOIR)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/dame_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Fou && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.NOIR)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/fou_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Cavalier && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.NOIR)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/cavalier_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Tour && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.NOIR)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/tour_noir.png');");
                } else if (partie.getTable().getPiece(row, col) instanceof Pion && partie.getTable().getPiece(row, col).getCouleur().equals(Couleur.NOIR)) {
                    table[row][col].setStyle("-fx-background-image: url('/images/pion_noir.png');");
                } else {
                    table[row][col].setStyle("");
                }
            }
        }
    }

    public void deplacerPiece(int rowDest, int colDest, Button[][] table, boolean idJoueur) {
        cpt++;
        if (cpt == 1) {
            if (partie.getTable().getPiece(rowDest, colDest) == null) {
                cpt = 0;
            } else {
                row = rowDest;
                col = colDest;
            }
        } else if (cpt == 2) {
            partie.getMouvement().setMouvement(row, col, rowDest, colDest);
            jouerUneTour(idJoueur);
            afficherPieces(table);
            cpt = 0;
        }
    }

    private void jouerUneTour(boolean idJoueur) {
        if (ChoisirAdversaireControleur.joueurChoisie == 1) {
            partie.jouerMouvementContreHumain(idJoueur);
        } else {
            partie.jouerUnTourContreIA();
        }
    }

    public void actualiserPartieContreHumain(Button[][] table, Boolean idJoueur) {
        if (partie.getMouvementAdversaireHumain(idJoueur)) {
            afficherPieces(table);
        }
    }
}
