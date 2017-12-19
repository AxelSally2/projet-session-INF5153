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

import fichier.ChargerFichier;
import fichier.ConvertirDonnees;

/**
 *
 * @author jmppr
 */
public class Temps {

    private long tempsDepart;
    private long tempsFin;
    private long tempsIADebutant;
    private long tempsIAAvance;

    public Temps() {
        chargerTemps();
    }
   
    public long getTempsDepart() {
        return tempsDepart;
    }

    public long getTempsFin() {
        return tempsDepart;
    }

    public long getTempsIADebutant() {
        return tempsIADebutant;
    }

    public long getTempsIAAvance() {
        return tempsIAAvance;
    }

    public void setTempsDepart() {
        this.tempsDepart = System.currentTimeMillis();
    }

    public void setTempsFin() {
        this.tempsFin = System.currentTimeMillis();
    }

    public void setMeilleurTemps(int IADifficulte) {
        if (IADifficulte == 2) {
            tempsIADebutant = (tempsPartie() < tempsIADebutant) ? tempsPartie() : tempsIADebutant;
        } else if (IADifficulte == 3) {
            tempsIAAvance = (tempsPartie() < tempsIAAvance) ? tempsPartie() : tempsIAAvance;
        }
    }

    private long tempsPartie() {
        return tempsFin - tempsDepart;
    }

    private void chargerTemps() {
        ConvertirDonnees conv = new ConvertirDonnees();
        ChargerFichier charger = new ChargerFichier("temps.xml");
        Temps meilleursTemps = (Temps) conv.XMLToObjet(charger.contenuFichierPredefinie());
        tempsIADebutant = meilleursTemps.getTempsIADebutant();
        tempsIAAvance = meilleursTemps.getTempsIAAvance();
    }
}
