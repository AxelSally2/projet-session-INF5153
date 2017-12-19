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

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Map;
import joueur.Joueur;
import menucontroleur.MenuPrincipalControleur;

/**
 *
 * @author jmppr
 */
public class Partie {

    private Table table;
    private Joueur joueur1;
    private Joueur joueur2;
    private Mouvement mouv;

    public Partie(Table table, Joueur joueur1, Joueur joueur2, Mouvement mouv) {
        this.table = table;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.mouv = mouv;
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

    public void jouerMouvementContreHumain(boolean idJoueur) {
        RpcClient client = new RpcClient();
        int idJoueurAjouer = (idJoueur) ? 1 : 2;
        Couleur couleur = (idJoueur) ? Couleur.BLANC : Couleur.NOIR;
        if (client.getJoueurAJouer() == idJoueurAjouer) {
            if (joueur1.effectueMouvement(table, mouv, couleur)) {
                client.postCoord(mouv, idJoueur);
                client.setJoueurAJouer();
            }
        }
    }

    public boolean getMouvementAdversaireHumain(boolean idJoueur) {
        Couleur couleur = (!idJoueur) ? Couleur.BLANC : Couleur.NOIR;
        RpcClient client = new RpcClient();
        Map coord = new Hashtable();
        coord = client.getCoord(!idJoueur, mouv);
        if (!coord.isEmpty()) {
            joueur2.effectueMouvement(table, mouv, couleur);
            client.clearMap(!idJoueur);
            return true;
        }
        return false;
    }

    public void initialiserPartie() throws FileNotFoundException {
        if (MenuPrincipalControleur.partieChoisie == 1) {
            table.initialiserNouvelleTable();
        } else if (MenuPrincipalControleur.partieChoisie == 2) {
            table.initialiserTableSauvegarder();
        }
    }
}
