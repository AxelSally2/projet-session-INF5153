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
package piece;

import jeu.Couleur;

/**
 *
 * @author jmppr
 */
public class Pion extends Piece {

    public Pion(Couleur couleur, int row, int col) {
        super(couleur, row, col);
    }

    @Override
    public boolean estDeplacementValide(int row, int col) {
        boolean resultat;
        int dirR = (super.getCouleur().equals(Couleur.NOIR)) ? 1 : -1;
        int dirRStart = (super.getCouleur().equals(Couleur.NOIR)) ? 2 : -2;
        int startRow = (super.getCouleur().equals(Couleur.NOIR)) ? 1 : 6;
        if (super.getRow() == startRow) {
            resultat = (super.getCol() == col && (super.getRow() + dirR == row || super.getRow() + dirRStart == row))
                    || deplacementDiagoValide(row, col, dirR);
        } else {
            resultat = (super.getCol() == col && super.getRow() + dirR == row)
                    || deplacementDiagoValide(row, col, dirR);
        }
        return resultat;
    }

    private boolean deplacementDiagoValide(int row, int col, int dirR) {
        return super.getCol() + 1 == col && super.getRow() + dirR == row || super.getCol() - 1 == col && super.getRow() + dirR == row;
    }
}
