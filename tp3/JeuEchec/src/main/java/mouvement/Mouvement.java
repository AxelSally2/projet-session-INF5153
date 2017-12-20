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
package mouvement;

import jeu.Couleur;
import jeu.Table;

/**
 *
 * @author jmppr
 */
public class Mouvement {

    private int row;
    private int col;
    private int rowDest;
    private int colDest;
    final private MouvementHistorique mouvHist;

    public Mouvement() {
        mouvHist = new MouvementHistorique();
    }

    public void setMouvement(int row, int col, int rowDest, int colDest) {
        this.row = row;
        this.col = col;
        this.rowDest = rowDest;
        this.colDest = colDest;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getRowDest() {
        return rowDest;
    }

    public int getColDest() {
        return colDest;
    }
    
    public MouvementHistorique getMouvHist() {
        return mouvHist;
    }
    
    public boolean estValide(Table table, Couleur couleur) {
        return table.getPiece(row, col).estDeplacementValide(rowDest, colDest)
                && table.cheminEstDegage(table.getPiece(row, col), rowDest, colDest)
                && table.piecePeutManger(table.getPiece(row, col), rowDest, colDest)
                && table.getPiece(row, col).getCouleur().equals(couleur);
    }

    /**
     * La position de la piece est changer pour ça nouvelle position. La valeur
     * null est setter à l'ancienne position.
     *
     * @param table Echechier contenant les pieces des 2 adversaires
     */
    public void mouvementPiece(Table table) {
        table.setPiece(rowDest, colDest, table.getPiece(row, col));
        table.getPiece(rowDest, colDest).setRow(rowDest);
        table.getPiece(rowDest, colDest).setCol(colDest);
        table.setPiece(row, col, null);
        table.remplacerPionParDame(table.getPiece(rowDest, colDest));
        mouvHist.enregistrerMouvement(table);
    }

}
