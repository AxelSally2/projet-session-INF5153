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

    private Piece tablePieces[][];

    public Table() {
        this.tablePieces = new Piece[NB_ROW][NB_COL];
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
        int row = (COULEUR1.equals(couleur)) ? 0 : 7;
        int rowPion = (COULEUR2.equals(couleur)) ? 1 : 6;

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
        for (int i = 0; i < NB_COL; i++) {
            pionsListe.add(new Pion((row == 1) ? COULEUR1 : COULEUR2, row, i));
        }
        return pionsListe;
    }

    /**
     * La table d'échec est initialisé avec les pieces en position pour débuter
     * une nouvelle partie
     */
    public void initialiserNouvelleTable() {
        for (int i = 0; i < NB_ROW; i++) {
            for (int j = 0; j < NB_COL; j++) {
                for (int k = 0; k < listeTousLesPieces().size(); k++) {
                    if (i == listeTousLesPieces().get(k).getRow() && j == listeTousLesPieces().get(k).getCol()) {
                        tablePieces[i][j] = listeTousLesPieces().get(k);
                    }
                }
            }
        }
    }

    /**
     * La table d'échec est initialisé avec la position des pieces contenu dans
     * le fichier de sauvegade
     * @throws java.io.FileNotFoundException
     */
    public void initialiserTableSauvegarder() throws FileNotFoundException {
        ChargerFichier fichier = new ChargerFichier();
        tablePieces = XMLToTable(fichier.contenuFichier());
    }
    
    /**
     * Transforme le tableau de pièces en une chaine de caractères en format XML
     * @return Une chaine de caractère en format XML
     */
    public String tableToXML() {
        XStream xstream = new XStream();
        String xml = xstream.toXML(tablePieces);   
        return xml;
    }
    
    /**
     * Transforme la chaine de caractère en format XML dans un tableau de pièces
     * @param tableXML
     * @return Un tableau de pièces
     */
    private Piece [][] XMLToTable(String tableXML){
        XStream xstream = new XStream();
        Piece pieces[][];
        pieces = (Piece [][]) xstream.fromXML(tableXML);
        return pieces;
    }
    
    
//    public void estDeplacementValide(Piece piece, int row, int col) {
//        piece.estDeplacementValide(row, col);
//    }

}
