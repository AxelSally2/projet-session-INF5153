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
import jeu.Couleur;
import jeu.Mouvement;
import jeu.Table;
import piece.Piece;

/**
 *
 * @author jmppr
 */
public abstract class IA implements Joueur {

    public IA() {
    }

    private Piece generePiece(Table table, Couleur couleur) {
        ArrayList<Piece> listePieces = toutesLesPiecesDeIA(table, couleur);
        int random = (int) (Math.random() * listePieces.size() + 1);
        return listePieces.get(random - 1);
    }

    private int genereRow() {
        int random = (int) (Math.random() * 8 + 1);
        return random - 1;
    }

    private int genereCol() {
        int random = (int) (Math.random() * 8 + 1);
        return random - 1;
    }

    protected ArrayList<Piece> toutesLesPiecesDeIA(Table table, Couleur couleur) {
        ArrayList<Piece> listePieces = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (table.getPiece(row, col) != null && table.getPiece(row, col).getCouleur().equals(couleur)) {
                    listePieces.add(table.getPiece(row, col));
                }
            }
        }
        return listePieces;
    }

    private void genereUnMouvement(Table table, Mouvement mouv, Couleur couleur) {
        Piece piece = generePiece(table, couleur);
        mouv.setMouvement(piece.getRow(), piece.getCol(), genereRow(), genereCol());
    }

    protected void effectueMouvementAleatoire(Table table, Mouvement mouv, Couleur couleur) {
        genereUnMouvement(table, mouv, couleur);
        while (!(table.estValide(mouv, couleur))) {
            genereUnMouvement(table, mouv, couleur);
        }
        if (!table.estEchecEtMath(Couleur.BLANC) && !table.estEchecEtMath(Couleur.NOIR)) {
            mouv.mouvementPiece(table);
        }
    }

    @Override
    public abstract boolean effectueMouvement(Table table, Mouvement mouv, Couleur color);
}
