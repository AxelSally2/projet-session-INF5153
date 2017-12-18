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

import com.thoughtworks.xstream.XStream;
import fichier.ChargerFichier;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import piece.*;

/**
 *
 * @author jmppr
 */
public class Table {

    private Piece tablePieces[][];

    public Table() {
        this.tablePieces = new Piece[8][8];
    }

    public Piece[][] tablePieces() {
        return tablePieces;
    }

    public Piece getPiece(int row, int col) {
        return tablePieces[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        tablePieces[row][col] = piece;
    }

    /**
     * Retourne une liste de tous les pieces pour débuter une partie d'échec
     *
     * @return Une liste de tous les pieces pour débuter une partie d'échec
     */
    private ArrayList<Piece> listeTousLesPieces() {
        ArrayList<Piece> piecesListe = new ArrayList<>();

        piecesListe.addAll(listePiecesCouleur(Couleur.NOIR));
        piecesListe.addAll(listePiecesCouleur(Couleur.BLANC));
        return piecesListe;
    }

    /**
     * Retourne une liste de tous les pieces pour débuter une partie pour la
     * couleur sélectionné en paramètre.
     *
     * @param couleur La couleur Blanc ou Noir
     * @return Une liste de tous les pieces pour une couleur
     */
    private ArrayList<Piece> listePiecesCouleur(Couleur couleur) {
        ArrayList<Piece> piecesListe = new ArrayList<>();
        int row = (Couleur.NOIR.equals(couleur)) ? 0 : 7;
        int rowPion = (Couleur.BLANC.equals(couleur)) ? 1 : 6;

        piecesListe.add(new Tour(couleur, row, 0));
        piecesListe.add(new Cavalier(couleur, row, 1));
        piecesListe.add(new Fou(couleur, row, 2));
        piecesListe.add(new Dame(couleur, row, 3));
        piecesListe.add(new Roi(couleur, row, 4));
        piecesListe.add(new Fou(couleur, row, 5));
        piecesListe.add(new Cavalier(couleur, row, 6));
        piecesListe.add(new Tour(couleur, row, 7));
        piecesListe.addAll(listePiecesPion(rowPion));
        return piecesListe;
    }

    /**
     * Retourne une liste de tous les pions pour la ligne passé en parametre
     *
     * @param row La ligne ou tous les pions vont être initialisé (Doit etre 1
     * ou 6)
     * @return Une liste de tous les pions d'une ligne
     */
    private ArrayList<Piece> listePiecesPion(int row) {
        ArrayList<Piece> pionsListe = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pionsListe.add(new Pion((row == 1) ? Couleur.NOIR : Couleur.BLANC, row, i));
        }
        return pionsListe;
    }

    /**
     * La table d'échec est initialisé avec les pieces en position pour débuter
     * une nouvelle partie
     */
    public void initialiserNouvelleTable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < listeTousLesPieces().size(); k++) {
                    if (i == listeTousLesPieces().get(k).getRow() && j == listeTousLesPieces().get(k).getCol()) {
                        tablePieces[i][j] = listeTousLesPieces().get(k);
                    }
                }
            }
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                tablePieces[i][j] = null;
            }
        }
    }

    /**
     * La table d'échec est initialisé avec la position des pieces contenu dans
     * le fichier de sauvegade
     *
     * @throws java.io.FileNotFoundException
     */
    public void initialiserTableSauvegarder() throws FileNotFoundException {
        ChargerFichier fichier = new ChargerFichier();
        tablePieces = XMLToTable(fichier.contenuFichier());
    }

    /**
     * Transforme le tableau de pièces en une chaine de caractères en format XML
     *
     * @return Une chaine de caractère en format XML
     */
    public String tableToXML() {
        XStream xstream = new XStream();
        String xml = xstream.toXML(tablePieces);
        return xml;
    }

    /**
     * Transforme la chaine de caractère en format XML dans un tableau de pièces
     *
     * @param tableXML
     * @return Un tableau de pièces
     */
    private Piece[][] XMLToTable(String tableXML) {
        XStream xstream = new XStream();
        Piece pieces[][];
        pieces = (Piece[][]) xstream.fromXML(tableXML);
        return pieces;
    }

    /**
     * Retourne vrai si un pion de la couleur passer en parametre peut faire un
     * mouvement diagonal (dans tous les directions) pour manger une piece de
     * l'adversaire.
     *
     * @param piece La piece à déplacer (doit être un pion)
     * @param row La ligne où le pion va se déplacer
     * @param col La colonne où le pion va se déplacer
     * @return Vrai si un pion de la couleur passer en parametre peut faire un
     * mouvement diagonal (dans tous les directions) pour manger une piece de
     * l'adversaire.
     */
    private boolean pionPeutManger(Piece piece, int row, int col) {
        return pionPeutMangerUneDirection(piece, row, col, 1, 1) // Droite
                || pionPeutMangerUneDirection(piece, row, col, 1, -1) // Gauhce
                || pionPeutMangerUneDirection(piece, row, col, -1, 1) // Droite
                || pionPeutMangerUneDirection(piece, row, col, -1, -1) // Gauhce
                || (tablePieces[row][col] == null) && piece.getCol() == col; 
    }

    /**
     * Retourne vrai si un pion de la couleur passer en parametre peut faire un
     * mouvement diagonal (dans une seul direction) pour manger une piece de
     * l'adversaire.
     *
     * @param piece La piece à déplacer (doit être un pion)
     * @param row La ligne où le pion va se déplacer
     * @param col La colonne où le pion va se déplacer
     * @param pionCouleur La couleur du pion
     * @param dirR Le nombre de case en Y pour faire le déplacement (1 ou -1)
     * (Si de couleur blanc -1, si de couleur noir +1)
     * @param dirC Le nombre de case en X pour faire le déplacement (1 à droite
     * ou -1 à gauche)
     * @return vrai si un pion de la couleur passer en parametre peut faire un
     * mouvement diagonal (dans une seul direction) pour manger une piece de
     * l'adversaire.
     */
    private boolean pionPeutMangerUneDirection(Piece piece, int row, int col, int dirR, int dirY) {
        Couleur couleurPieceEnnemi = (piece.getCouleur().equals(Couleur.BLANC)) ? Couleur.NOIR : Couleur.BLANC;
        return piece instanceof Pion
                && row == piece.getRow() + dirR && col == piece.getCol() + dirY
                && (tablePieces[piece.getRow() + dirR][piece.getCol() + dirY] != null
                && tablePieces[piece.getRow() + dirR][piece.getCol() + dirY].getCouleur().equals(couleurPieceEnnemi));
    }

    private boolean pionCheminDegage(Piece piece, int row, int col) {
        if (!(piece instanceof Pion) || col != piece.getCol()) {
            return true;
        }
        int mouv = (piece.getCouleur().equals(Couleur.BLANC)) ? -1 : 1;
        int mouvStart = (piece.getCouleur().equals(Couleur.BLANC)) ? -2 : 2;
        int pos = (piece.getCouleur().equals(Couleur.BLANC)) ? 6 : 1;
        return tablePieces[piece.getRow() + mouv][piece.getCol()] == null
                || (pos == piece.getRow() && tablePieces[piece.getRow() + mouvStart][piece.getCol()] == null 
                && tablePieces[piece.getRow() + mouv][piece.getCol()] == null);

    }

    private boolean fouACheminDegage(Piece piece, int row, int col) {
        if (!(piece instanceof Fou)) {
            return true;
        }
        boolean resultat;
        int dirX = col > piece.getCol() ? 1 : -1;
        int dirY = row > piece.getRow() ? 1 : -1;
        resultat = estPasObstrueDiagonalement(piece, row, dirY, dirX);
        return resultat;
    }
    
    private boolean tourACheminDegage(Piece piece, int row, int col) {
        if (!(piece instanceof Tour)) {
            return true;
        }
        boolean resultat = true;
        int dirX = col > piece.getCol() ? 1 : -1;
        int dirY = row > piece.getRow() ? 1 : -1;
        if (row == piece.getRow()) {
            resultat = estPasObstrueHorizontalement(piece, col, dirX);
        } else if (col == piece.getCol()) {
            resultat = estPasObstrueVerticalement(piece, row, dirY);
        }
        return resultat;
    }

    private boolean dameACheminDegage(Piece piece, int row, int col) {
        if (!(piece instanceof Dame)) {
            return true;
        }
        boolean resultat;
        int dirX = col > piece.getCol() ? 1 : -1;
        int dirY = row > piece.getRow() ? 1 : -1;
        if (row == piece.getRow()) {
            resultat = estPasObstrueHorizontalement(piece, col, dirX);
        } else if (col == piece.getCol()) {
            resultat = estPasObstrueVerticalement(piece, row, dirY);
        } else {
            resultat = estPasObstrueDiagonalement(piece, row, dirY, dirX);
        }
        return resultat;
    }

    private boolean estPasObstrueHorizontalement(Piece piece, int col, int dirX) {
        for (int i = 1; i < Math.abs(col - piece.getCol()); i++) {
            if (tablePieces[piece.getRow()][piece.getCol() + i * dirX] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean estPasObstrueVerticalement(Piece piece, int row, int dirY) {
        for (int i = 1; i < Math.abs(row - piece.getRow()); i++) {
            if (tablePieces[piece.getRow() + i * dirY][piece.getCol()] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean estPasObstrueDiagonalement(Piece piece, int row, int dirY, int dirX) {
        for (int i = 1; i < Math.abs(row - piece.getRow()); i++) {
            if (tablePieces[piece.getRow() + i * dirY][piece.getCol() + i * dirX] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean cheminEstDegage(Piece piece, int row, int col) {
        return dameACheminDegage(piece, row, col)
                && tourACheminDegage(piece, row, col)
                && fouACheminDegage(piece, row, col)
                && pionCheminDegage(piece, row, col);
    }

    private boolean piecePeutManger(Piece piece, int row, int col) {
        if (piece instanceof Pion) {
            return pionPeutManger(tablePieces[piece.getRow()][piece.getCol()], row, col);
        }
        return (tablePieces[row][col] == null
                || !(piece.getCouleur().equals(tablePieces[row][col].getCouleur())));
    }

    public boolean estValide(Mouvement mouv, Couleur couleur) {
        //System.out.println(tablePieces[row][col].getRow() + "---" + tablePieces[row][col].getCol());
        //System.out.println(rowDest + "-" + colDest);
        //System.out.println(tablePieces[row][col].estDeplacementValide(rowDest, colDest));
        //System.out.println(cheminEstDegage(tablePieces[row][col], rowDest, colDest));
        //System.out.println(piecePeutManger(tablePieces[row][col], rowDest, colDest));
        int row = mouv.getRow();
        int col = mouv.getCol();
        int rowDest = mouv.getRowDest();
        int colDest = mouv.getColDest();
        return tablePieces[row][col].estDeplacementValide(rowDest, colDest)
                && cheminEstDegage(tablePieces[row][col], rowDest, colDest)
                && piecePeutManger(tablePieces[row][col], rowDest, colDest)
                && tablePieces[row][col].getCouleur().equals(couleur);
    }

    public void remplacerPionParDame(Piece piece) {
        if (piece instanceof Pion) {
            if (piece.getCouleur().equals(Couleur.BLANC) && piece.getRow() == 0) {
                tablePieces[piece.getRow()][piece.getCol()] = new Dame(piece.getCouleur(), piece.getRow(), piece.getCol());
            } else if (piece.getCouleur().equals(Couleur.NOIR) && piece.getRow() == 7) {
                tablePieces[piece.getRow()][piece.getCol()] = new Dame(piece.getCouleur(), piece.getRow(), piece.getCol());
            }
        }
    }
}
