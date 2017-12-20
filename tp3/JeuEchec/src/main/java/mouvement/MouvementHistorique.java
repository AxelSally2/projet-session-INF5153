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

import java.util.ArrayList;
import jeu.Table;

/**
 *
 * @author jmppr
 */
public class MouvementHistorique {

    private ArrayList<Table> mouvHistorique;
    private Table table = null;

    public MouvementHistorique() {
        mouvHistorique = new ArrayList<>();
        Table tableDepart = new Table();
        tableDepart.initialiserNouvelleTable();
        mouvHistorique.add(tableDepart);
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
