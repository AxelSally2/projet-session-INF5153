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

import joueur.Ennemi;
import joueur.Joueur;

/**
 *
 * @author jmppr
 */
public class Jeu<T> {

    private Table table;
    private Joueur joueur1;
    private Ennemi joueur2;

    public Jeu (Table table, Joueur joueur1, Ennemi joueur2) {
        this.table = table;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public Table getTable() {
        return table;
    }
    
    public Joueur getJoueur1() {
        return joueur1;
    }
    
    public Ennemi getJoueur2() {
        return joueur2;
    }
}
