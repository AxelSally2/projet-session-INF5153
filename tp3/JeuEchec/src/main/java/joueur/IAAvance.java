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
package joueur;

import java.util.ArrayList;
import jeu.Mouvement;
import jeu.Table;
import piece.Piece;

/**
 *
 * @author jmppr
 */
public class IAAvance extends IA {

    public IAAvance() {
        super();
    }

    /**
     * Fonction qui retourne une liste d'une catégorie de pièces en fontion du
     * poid passer en paramètre. (Ex: Le poid est égal à 1, on retourne tous les
     * pieces qui sont des pions).
     *
     * @param table La table de jeu contenant tous les pieces
     * @param poid Le poid représente une catégorie de pièces : ----------------
     * Plus le poid est élevé, plus la pièce est importante --------------------
     * 6 = Roi -----------------------------------------------------------------
     * 5 = Dame ----------------------------------------------------------------
     * 4 = Fou -----------------------------------------------------------------
     * 3 = Cavalier ------------------------------------------------------------
     * 2 = Tour ----------------------------------------------------------------
     * 1 = Pion ----------------------------------------------------------------
     * @return Une liste d'une catégorie de pièces en fontion du poid passé en
     * paramètre
     */
    private ArrayList<Piece> groupeDePiecesDuJoueur(Table table, int poid) {
        ArrayList<Piece> list = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (table.getPiece(row, col) != null && table.getPiece(row, col).getPoid() == poid
                        && table.getPiece(row, col).getCouleur().equals("Blanc")) {
                    list.add(table.getPiece(row, col));
                }
            }
        }
        return list;
    }

    /**
     * Fonction qui retourne tous les pièces vivantes du joueur trier par les
     * pièces les plus importantes aux moins importantes (Roi en premier, puis
     * Dame, Fou, Cavalier, Tour et Pion).
     *
     * @param table La table de jeu contenant tous les pieces
     * @return Tous les pièces vivantes du joueur trier par leurs catégories en
     * ordre des pièces les plus importantes aux moins importantes
     */
    private ArrayList<Piece> toutesLesPiecesDuJoueurTrie(Table table) {
        ArrayList<Piece> list = new ArrayList<>();
        list.addAll(groupeDePiecesDuJoueur(table, 6)); // Roi
        list.addAll(groupeDePiecesDuJoueur(table, 5)); // Dame
        list.addAll(groupeDePiecesDuJoueur(table, 4)); // Fou
        list.addAll(groupeDePiecesDuJoueur(table, 3)); // Cavalier
        list.addAll(groupeDePiecesDuJoueur(table, 2)); // Tour
        list.addAll(groupeDePiecesDuJoueur(table, 1)); // Pion
        return list;
    }

    /**
     * Fonction qui permet à l'IA de prioriser ses mouvements de manière à
     * manger la pièce du joueur la plus importante qui peut être manger en
     * premier. Si aucune pièce du joueur peut être mangé, l'IA effectue un
     * mouvement aléatoire.
     *
     * @param table La table de jeu contenant tous les pieces
     * @param mouv Pour effectuer un mouvement sur la table
     */
    @Override
    public void effectueMouvement(Table table, Mouvement mouv) {
        ArrayList<Piece> piecesJoueur = toutesLesPiecesDuJoueurTrie(table);
        ArrayList<Piece> piecesIA = toutesLesPiecesDeIA(table);
        boolean pieceKill = false;
        for (int i = 0; i < piecesJoueur.size(); i++) {
            for (int j = 0; j < piecesIA.size(); j++) {
                if (table.estValide(piecesIA.get(j).getRow(), piecesIA.get(j).getCol(),
                        piecesJoueur.get(i).getRow(), piecesJoueur.get(i).getCol())) {
                    mouv.mouvementPiece(table, piecesIA.get(j).getRow(), piecesIA.get(j).getCol(),
                            piecesJoueur.get(i).getRow(), piecesJoueur.get(i).getCol());
                    i = piecesJoueur.size();
                    j = piecesIA.size();
                    pieceKill = true;
                }
            }
        }
        if (pieceKill == false) {
            effectueMouvementAleatoire(table, mouv);
        }
    }

}
