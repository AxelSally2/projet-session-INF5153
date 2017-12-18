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

/**
 *
 * @author jmppr
 */
public class Mouvement {

    private ArrayList<Table> mouvHistorique;
    private Table table = null;
    private int row;
    private int col;
    private int rowDest;
    private int colDest;

    public Mouvement() {
        mouvHistorique = new ArrayList<>();
        Table tableDepart = new Table();
        tableDepart.initialiserNouvelleTable();
        mouvHistorique.add(tableDepart);
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

    public Table getTable() {
        return table;
    }

    public int mouvementSuivant(int pos) {
        if (-1 == pos) {
            pos += 2;
        }
        if (mouvHistorique.size() > pos) {
            this.table = mouvHistorique.get(pos);
            pos++;
        }
        return pos;
    }

    public int mouvementPrecedent(int pos) {
        if (mouvHistorique.size() == pos) {
            pos -= 2;
        }
        if (0 <= pos) {
            this.table = mouvHistorique.get(pos);
            pos--;
        }
        return pos;
    }

    /**
     * La position de la piece est changer pour ça nouvelle position. La valeur
     * null est setter à l'ancienne position.
     *
     * @param table Echechier contenant les pieces des 2 adversaires
     * @param row La position original de la ligne
     * @param col La position original de la colonne
     * @param rowDest La position de destination de la ligne
     * @param colDest La position de destination de la colonne
     */
    public void mouvementPiece(Table table) {
        table.setPiece(rowDest, colDest, table.getPiece(row, col));
        table.getPiece(rowDest, colDest).setRow(rowDest);
        table.getPiece(rowDest, colDest).setCol(colDest);
        table.setPiece(row, col, null);
        table.remplacerPionParDame(table.getPiece(rowDest, colDest));
        enregistrerMouvement(table);
    }

    /**
     * Chaque table d'echec passé en parametre est enregistrer.
     *
     * @param table Echechier contenant les pieces des 2 adversaires
     */
    public void enregistrerMouvement(Table table) {
        Table tableCpy = new Table();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                tableCpy.setPiece(row, col, table.getPiece(row, col));
            }
        }
        mouvHistorique.add(tableCpy);
    }

}
