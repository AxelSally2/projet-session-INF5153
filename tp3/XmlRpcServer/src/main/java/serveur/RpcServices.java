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
package serveur;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class RpcServices {

    ArrayList<Map> mouvementListe = new ArrayList<Map>();
    ArrayList<Integer> tour = new ArrayList<Integer>();
    int id = -1;

    public int getJoueurID() {
        id++;
        if (id % 2 == 0) {
            tour.add(id);
        }
        return id;
    }

    private Map ajouterCoordToMap(int row, int col, int rowDest, int colDest) {
        Map map = new Hashtable();
        map.put("row", row);
        map.put("col", col);
        map.put("rowDest", rowDest);
        map.put("colDest", colDest);
        return map;
    }

    public boolean postCoord(int row, int col, int rowDest, int colDest, int idJoueur) {
        mouvementListe.add(idJoueur, ajouterCoordToMap(row, col, rowDest, colDest));
        return true;
    }

    public Map getCoord(int idJoueur) {
        try {
            Map map = mouvementListe.get(idJoueur);
            return map;
        } catch (IndexOutOfBoundsException e) {
            return new Hashtable();
        }
    }

    public boolean clearMap(int idJoueur) {
        mouvementListe.get(idJoueur).clear();
        return true;
    }

    public boolean setJoueurAJouer(int idJoueur) {
        int idJoueurAJouer = (idJoueur % 2 == 0) ? idJoueur + 1 : idJoueur - 1;
        tour.add(idJoueurAJouer);
        tour.remove(new Integer(idJoueur));
        return true;
    }

    public int getJoueurAJouer(int idJoueur) {
        int joueurAJouer = -1;
        int idAutreJoueur;
        if (idJoueur % 2 == 0) {
            idAutreJoueur = idJoueur + 1;
        } else {
            idAutreJoueur = idJoueur - 1;
        }
        if (tour.contains(idJoueur)) {
            joueurAJouer = idJoueur;
        }
        if (tour.contains(idAutreJoueur)) {
            joueurAJouer = idAutreJoueur;
        }
        return joueurAJouer;
    }

    public boolean serveurEnLigne() {
        return true;
    }
}
