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

import java.util.ArrayList;
import piece.Cavalier;
import piece.Dame;
import piece.Fou;
import piece.Piece;
import piece.Pion;
import piece.Roi;
import piece.Tour;

/**
 *
 * @author jmppr
 */
public class Table {

    final String COULEUR1 = "Noir";
    final String COULEUR2 = "Blanc";
    final int NB_COL = 8;
    final int NB_ROW = 8;

    Piece tablePieces[][];

    public Table() {
        this.tablePieces = new Piece[NB_COL][NB_ROW];
    }

    public Piece getPiece(int x, int y) {
        return tablePieces[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        tablePieces[x][y] = piece;
    }

    /**
     * Retourne une liste de tous les pieces pour débuter une partie d'échec
     *
     * @return Une liste de tous les pieces pour débuter une partie d'échec
     */
    private ArrayList<Piece> listeTousLesPieces() {
        ArrayList<Piece> piecesListe = new ArrayList<>();

        piecesListe.addAll(listePiecesCouleur(COULEUR1));
        piecesListe.addAll(listePiecesCouleur(COULEUR2));
        return piecesListe;
    }

    /**
     * Retourne une liste de tous les pieces pour débuter une partie pour la
     * couleur sélectionné en paramètre.
     *
     * @param couleur La couleur Blanc ou Noir
     * @return Une liste de tous les pieces pour une couleur
     */
    private ArrayList<Piece> listePiecesCouleur(String couleur) {
        ArrayList<Piece> piecesListe = new ArrayList<>();
        int col = (COULEUR1.equals(couleur)) ? 0 : 7;
        int colPion = (COULEUR2.equals(couleur)) ? 1 : 6;

        piecesListe.add(new Tour(couleur, 0, col));
        piecesListe.add(new Cavalier(couleur, 1, col));
        piecesListe.add(new Fou(couleur, 2, col));
        piecesListe.add(new Dame(couleur, 3, col));
        piecesListe.add(new Roi(couleur, 4, col));
        piecesListe.add(new Fou(couleur, 5, col));
        piecesListe.add(new Cavalier(couleur, 6, col));
        piecesListe.add(new Tour(couleur, 7, col));
        piecesListe.addAll(listePiecesPion(colPion));
        return piecesListe;
    }

    /**
     * Retourne une liste de tous les pions pour la colonne passé en parametre
     *
     * @param col La colonne ou tous les pions vont être initialisé (Doit etre 1
     * ou 6)
     * @return Une liste de tous les pions d'une colonne
     */
    private ArrayList<Piece> listePiecesPion(int col) {
        ArrayList<Piece> pionsListe = new ArrayList<>();
        for (int i = 0; i < NB_COL; i++) {
            pionsListe.add(new Pion((col == 1) ? COULEUR1 : COULEUR2, i, col));
        }
        return pionsListe;
    }

    /**
     * La table d'échec est initialisé avec les pieces en position pour débuter
     * une nouvelle partie
     */
    public void initialiserNouvelleTable() {
        for (int i = 0; i < NB_COL; i++) {
            for (int j = 0; j < NB_ROW; j++) {
                for (int k = 0; k < listeTousLesPieces().size(); k++) {
                    if (i == listeTousLesPieces().get(k).getPosX() && j == listeTousLesPieces().get(k).getPosY()) {
                        tablePieces[i][j] = listeTousLesPieces().get(k);
                    }
                }
            }
        }
    }

    /**
     * La table d'échec est initialisé avec la position des pieces contenu dans
     * le fichier de sauvegade
     */
    public void initialiserTableSauvegarder() {
        //TODO
    }

}
