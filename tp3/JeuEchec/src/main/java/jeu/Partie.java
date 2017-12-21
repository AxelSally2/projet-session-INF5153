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

import mouvement.Mouvement;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;
import joueur.*;

/**
 *
 * @author jmppr
 */
public class Partie {

    private Table table;
    private Joueur joueur1;
    private Joueur joueur2;
    private Mouvement mouv;

    public Partie(Joueur joueur2) {
        this.table = new Table();
        this.joueur1 = new Humain();
        this.joueur2 = joueur2;
        this.mouv = new Mouvement();
    }

    public Table getTable() {
        return table;
    }

    public Table setTable(Table table) {
        return this.table = table;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public Mouvement getMouvement() {
        return mouv;
    }

    public void jouerUnTourContreIA() {
        if (joueur1.effectueMouvement(table, mouv, Couleur.BLANC)) {
            joueur2.effectueMouvement(table, mouv, Couleur.NOIR);
        }
    }

    public void jouerMouvementContreHumain(int idJoueur) {
        RpcClient client = new RpcClient();
        Couleur couleur = (idJoueur % 2 == 0) ? Couleur.BLANC : Couleur.NOIR;
        if (client.getJoueurAJouer(idJoueur) == idJoueur) {
            if (joueur1.effectueMouvement(table, mouv, couleur)) {
                client.postCoord(mouv, idJoueur);
                client.setJoueurAJouer(idJoueur);
            }
        }
    }

    public boolean getMouvementAdversaireHumain(int idJoueur) {
        int id = (idJoueur % 2 == 0) ? idJoueur + 1 : idJoueur - 1;
        Couleur couleur = (idJoueur % 2 == 0) ? Couleur.NOIR : Couleur.BLANC;
        RpcClient client = new RpcClient();
        Map coord = new Hashtable();
        coord = client.getCoord(id, mouv);
        if (!coord.isEmpty()) {
            joueur2.effectueMouvement(table, mouv, couleur);
            client.clearMap(id);
            return true;
        }
        return false;
    }

    public void initialiserPartie(int menuChoisie) throws FileNotFoundException {
        if (menuChoisie == 1) {
            table.initialiserNouvelleTable();
        } else if (menuChoisie == 2) {
            table.initialiserTableSauvegarder();
        }
    }
}
