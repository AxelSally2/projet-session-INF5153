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

    ArrayList<String> couleur = new ArrayList<String>();
    Map joueur1 = new Hashtable();
    Map joueur2 = new Hashtable();
    int joueur = 1;

    public boolean getColor(String couleur) {
        if (!this.couleur.contains(couleur)) {
            return this.couleur.add(couleur);
        }
        return false;
    }

    private void ajouterCoordToMap(int row, int col, int rowDest, int colDest, Map map) {
        map.put("row", row);
        map.put("col", col);
        map.put("rowDest", rowDest);
        map.put("colDest", colDest);
    }

    public boolean postCoord(int row, int col, int rowDest, int colDest, boolean couleur) {
        if (couleur) {
            ajouterCoordToMap(row, col, rowDest, colDest, joueur1);
            return true;
        } else if (!couleur) {
            ajouterCoordToMap(row, col, rowDest, colDest, joueur2);
            return true;
        }
        return false;
    }

    public Map getCoord(boolean couleur) {
        return (couleur) ? joueur1 : joueur2;
    }

    public boolean clearMap(boolean couleur) {
        if (couleur) {
            joueur1.clear();
            return true;
        } else if (!couleur) {
            joueur2.clear();
            return true;
        }
        return false;
    }

    public boolean setTourAJouer() {
        if (joueur == 1) {
            joueur = 2;
            return true;
        } else if (joueur == 2) {
            joueur = 1;
            return true;
        }
        return false;
    }

    public int getTourAJouer() {
        return joueur;
    }

}
