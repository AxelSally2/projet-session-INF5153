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
import java.util.Hashtable;
import java.util.Map;
import partiecontroleur.PartieControleur;

/**
 *
 * @author jmppr
 */
public class Mouvement {

    private ArrayList<Map> mouvHistorique;

    public Mouvement() {
        mouvHistorique = new ArrayList<>();
    }

    public ArrayList<Map> getMouvHistorique() {
        return mouvHistorique;
    }

    public int mouvementSuivant(Table table, int pos) {
        if (-1 == pos) {
            pos++;
        }
        if (mouvHistorique.size() > pos) {
            Map mouv = mouvHistorique.get(pos);
            mouvementPiece(table,
                    (int) mouv.get("row"), (int) mouv.get("col"),
                    (int) mouv.get("rowDest"), (int) mouv.get("colDest"));
            pos++;
        }
        return pos;
    }

    public int mouvementPrecedent(Table table, int pos) {
        if (mouvHistorique.size() == pos) {
            pos--;
        }
        if (0 <= pos) {
            Map mouv = mouvHistorique.get(pos);
            mouvementPiece(table,
                    (int) mouv.get("rowDest"), (int) mouv.get("colDest"),
                    (int) mouv.get("row"), (int) mouv.get("col"));
            pos--;
        } else {
            table.initialiserNouvelleTable();
        }
        return pos;
    }

    public void mouvementPiece(Table table, int row, int col, int rowDest, int colDest) {
        table.setPiece(rowDest, colDest, table.getPiece(row, col));
        table.getPiece(rowDest, colDest).setRow(rowDest);
        table.getPiece(rowDest, colDest).setCol(colDest);
        table.setPiece(row, col, null);
        table.remplacerPionParDame(PartieControleur.partie.getTable().getPiece(rowDest, colDest));
    }

    public void enregistrerMouvement(int row, int col, int rowDest, int colDest) {
        Map mouv = new Hashtable();
        mouv.put("row", row);
        mouv.put("col", col);
        mouv.put("rowDest", rowDest);
        mouv.put("colDest", colDest);
        mouvHistorique.add(mouv);
    }

}
