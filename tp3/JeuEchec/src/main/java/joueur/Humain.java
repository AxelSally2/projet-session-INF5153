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

import jeu.Couleur;
import mouvement.Mouvement;
import jeu.Table;

/**
 *
 * @author jmppr
 */
public class Humain implements Joueur {

    private String nom;

    public Humain() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean effectueMouvement(Table table, Mouvement mouv, Couleur couleur) {
        if (mouv.estValide(table, couleur) && !table.estEchecEtMath(Couleur.BLANC) && !table.estEchecEtMath(Couleur.NOIR)) {
            mouv.mouvementPiece(table);
            return true;
        }
        return false;
    }
}
