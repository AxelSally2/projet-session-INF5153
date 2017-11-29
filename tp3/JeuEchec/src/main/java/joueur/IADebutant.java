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
import java.util.Hashtable;
import java.util.Map;
import jeu.Table;
import piece.Piece;

/**
 *
 * @author jmppr
 */
public class IADebutant extends IA {

    public IADebutant() {
        super();
    }

    private Piece generePiece(Table table) {
        ArrayList<Piece> list = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (table.getPiece(row, col) != null && table.getPiece(row, col).getCouleur().equals("Noir")) {
                    list.add(table.getPiece(row, col));
                }
            }
        }
        int random = (int) (Math.random() * list.size() + 1);
        return list.get(random - 1);
    }

    private int genereRow() {
        int random = (int) (Math.random() * 8 + 1);
        return random - 1;
    }

    private int genereCol() {
        int random = (int) (Math.random() * 8 + 1);
        return random - 1;
    }

    private Map genereUnMouvement(Table table) {
        Piece piece = generePiece(table);
        Map mouv = new Hashtable();
        mouv.put("row", piece.getRow());
        mouv.put("col", piece.getCol());
        mouv.put("rowDest", genereRow());
        mouv.put("colDest", genereCol());
        return mouv;
    }

    @Override
    public void effectueMouvement(Table table) {
        Map mouv = genereUnMouvement(table);
        while (!(table.estValide((int) mouv.get("row"), (int) mouv.get("col"), (int) mouv.get("rowDest"), (int) mouv.get("colDest")))) {
            mouv = genereUnMouvement(table);
        }
        table.setPiece((int) mouv.get("rowDest"), (int) mouv.get("colDest"), table.getPiece((int) mouv.get("row"), (int) mouv.get("col")));
        table.getPiece((int) mouv.get("rowDest"), (int) mouv.get("colDest")).setRow((int) mouv.get("rowDest"));
        table.getPiece((int) mouv.get("rowDest"), (int) mouv.get("colDest")).setCol((int) mouv.get("colDest"));
        table.setPiece((int) mouv.get("row"), (int) mouv.get("col"), null);
        table.remplacerPionParDame(table.getPiece((int) mouv.get("rowDest"), (int) mouv.get("colDest")));
    }
}
