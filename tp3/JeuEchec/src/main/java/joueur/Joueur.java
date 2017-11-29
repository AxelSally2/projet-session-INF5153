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

import jeu.Table;

/**
 *
 * @author jmppr
 */
public class Joueur {

    private String nom;

    public Joueur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean effectueMouvement(Table table, int row, int col, int rowDest, int colDest) {
        if (table.estValide(row, col, rowDest, colDest)) {
            table.setPiece(rowDest, colDest, table.getPiece(row, col));
            table.getPiece(rowDest, colDest).setRow(rowDest);
            table.getPiece(rowDest, colDest).setCol(colDest);
            table.setPiece(row, col, null);
            table.remplacerPionParDame(table.getPiece(rowDest, colDest));
            return true;
        }
        return false;
    }

}
