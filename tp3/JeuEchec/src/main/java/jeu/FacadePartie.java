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
package jeu;

import donnee.ConvertirDonnees;
import fichier.ChargerFichier;
import fichier.EnregistrerFichier;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import joueur.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import piece.*;

/**
 *
 * @author jmppr
 */
public class FacadePartie {

    private Partie partie;
    private Temps temps;
    private int row;
    private int col;
    private int cpt;
    private int partieChoisie;
    private int joueurChoisie;

    public FacadePartie() {
        cpt = 0;
        temps = new Temps();
    }

    public void setPartieChoisie(int partieChoisie) {
        this.partieChoisie = partieChoisie;
    }

    public void setJoueurChoisie(int joueurChoisie) {
        this.joueurChoisie = joueurChoisie;
    }

    public int getPartieChoisie() {
        return partieChoisie;
    }

    public int getJoueurChoisie() {
        return joueurChoisie;
    }

    private void creerPartie(int adversaireChoisie) {
        if (adversaireChoisie == 1) {
            partie = new Partie(new Humain());
        } else if (adversaireChoisie == 2) {
            partie = new Partie(new IADebutant());
        } else if (adversaireChoisie == 3) {
            partie = new Partie(new IAAvance());
        } else {
            partie = new Partie(new IADebutant());
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

    public void deplacerPiece(int rowDest, int colDest, Button[][] table, int idJoueur, int adversaireChoisie) {
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
            jouerUnTour(idJoueur, adversaireChoisie);
            afficherPieces(table);
            cpt = 0;
        }
    }

    private void jouerUnTour(int idJoueur, int adversaireChoisie) {
        if (adversaireChoisie == 1) {
            partie.jouerMouvementContreHumain(idJoueur);
        } else {
            partie.jouerUnTourContreIA();
        }
    }

    public void actualiserPartieContreHumain(Button[][] table, int idJoueur) {
        if (partie.getMouvementAdversaireHumain(idJoueur)) {
            afficherPieces(table);
        }
    }

    public String msgVainceur(int idJoueur) {
        Couleur couleur = (idJoueur % 2 == 0) ? Couleur.BLANC : Couleur.NOIR;
        Couleur couleurEnn = (idJoueur % 2 != 0) ? Couleur.BLANC : Couleur.NOIR;
        if (partie.getTable().estEchecEtMath(couleurEnn)) {
            return "Vous avez gagné!";
        } else if (partie.getTable().estEchecEtMath(couleur)) {
            return "Vous avez perdu!";
        }
        return "";
    }

    public boolean estEchecEtMath() {
        return partie.getTable().estEchecEtMath(Couleur.NOIR)
                || partie.getTable().estEchecEtMath(Couleur.BLANC);
    }

    public String msgJoueurAJouer(int idJoueur) {
        RpcClient client = new RpcClient();
        if (client.getJoueurAJouer(idJoueur) == idJoueur) {
            return "C'est votre tour à jouer!";
        } else {
            return "Ce n'est pas votre tour à jouer!";
        }
    }

    public void remplacerMeilleurTemps(TextField textNom, Label msgFelicitation, Label msgNom, Button saveTemps, int adversaireChoisie) {
        temps.setTempsFin();
        if (partie.getTable().estEchecEtMath(Couleur.NOIR)
                && temps.estUnMeilleurTemps(adversaireChoisie)) {
            msgFelicitation.setVisible(true);
            msgNom.setVisible(true);
            textNom.setVisible(true);
            saveTemps.setVisible(true);
            temps.setMeilleurTemps(adversaireChoisie);
        }
    }

    public void sauvegarderMeilleurTemps(String textNom, int adversaireChoisie) {
        temps.setNom(adversaireChoisie, (textNom.isEmpty()) ? "Anonyme" : textNom);
        ConvertirDonnees conv = new ConvertirDonnees();
        EnregistrerFichier fichier = new EnregistrerFichier("temps.xml");
        fichier.sauvegarderDansFichier(conv.objetToXML(temps));
    }

    public void creerInitAffichePartie(Button[][] table, int menuChoisie, int adversaireChoisie) {
        cpt = 0;
        temps.setTempsDepart();
        creerPartie(adversaireChoisie);
        try {
            partie.initialiserPartie(menuChoisie);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacadePartie.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherPieces(table);
    }

    public void enregistrerPartie() {
        EnregistrerFichier fichier = new EnregistrerFichier("");
        ConvertirDonnees conv = new ConvertirDonnees();
        fichier.sauvegarderDansFichier(conv.objetToXML(partie.getTable().tablePieces()), "XML files (*.xml)", "*.xml");
    }

    public int mouvementPrecedent(int pos, Button[][] table) {
        int newPos;
        newPos = partie.getMouvement().getMouvHist().mouvementPrecedent(pos);
        partie.setTable(partie.getMouvement().getMouvHist().getTable());
        afficherPieces(table);
        return newPos;
    }

    public int mouvementSuivant(int pos, Button[][] table) {
        int newPos;
        newPos = partie.getMouvement().getMouvHist().mouvementSuivant(pos);
        partie.setTable(partie.getMouvement().getMouvHist().getTable());
        afficherPieces(table);
        return newPos;
    }

    public void initialiserVisualiserPartie(Button[][] table) {
        partie.getTable().initialiserNouvelleTable();
        afficherPieces(table);
    }

    public int setIdJoueur() {
        RpcClient client = new RpcClient();
        if (joueurChoisie != 1) {
            return 0;
        } else {
            return client.getJoueurID();
        }
    }

    public void initMeilleurTemps(Label meilleurTempsDebutant, Label meilleurTempsAvance, Label nomIADebutant, Label nomIAAvance) {
        ConvertirDonnees conv = new ConvertirDonnees();
        ChargerFichier fichier = new ChargerFichier("temps.xml");
        Temps meilleurTemps = (Temps) conv.XMLToObjet(fichier.contenuFichierPredefinie());
        double tempsDebutant = meilleurTemps.getTempsIADebutant() / 1000.0;
        double tempsAvance = meilleurTemps.getTempsIAAvance() / 1000.0;
        meilleurTempsDebutant.setText(String.valueOf(String.format("%.2f", tempsDebutant) + " Secondes"));
        meilleurTempsAvance.setText(String.valueOf(String.format("%.2f", tempsAvance) + " Secondes"));
        nomIADebutant.setText(meilleurTemps.getNomIADebutant());
        nomIAAvance.setText(meilleurTemps.getNomIAAvance());
    }

}
