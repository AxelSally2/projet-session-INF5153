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

    public Mouvement() {
        mouvHistorique = new ArrayList<>();
    }

    public Table getTable() {
        return table;
    }

    public int mouvementSuivant(int pos) {
        if (-1 == pos) {
            pos++;
        }
        if (mouvHistorique.size() > pos) {
            this.table = mouvHistorique.get(pos);
            pos++;
        }
        return pos;
    }

    public int mouvementPrecedent(int pos) {
        if (mouvHistorique.size() == pos) {
            pos--;
        }
        if (0 <= pos) {
            this.table = mouvHistorique.get(pos);
            pos--;
        } else {
            this.table.initialiserNouvelleTable();
        }
        return pos;
    }

    public void mouvementPiece(Table table, int row, int col, int rowDest, int colDest) {
        table.setPiece(rowDest, colDest, table.getPiece(row, col));
        table.getPiece(rowDest, colDest).setRow(rowDest);
        table.getPiece(rowDest, colDest).setCol(colDest);
        table.setPiece(row, col, null);
        table.remplacerPionParDame(table.getPiece(rowDest, colDest));
        enregistrerMouvement(table);
    }

    public void enregistrerMouvement(Table table) {
        Table tableCpy = new Table();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tableCpy.setPiece(i, j, table.getPiece(i, j));
            }
        }
        mouvHistorique.add(tableCpy);
    }

}
