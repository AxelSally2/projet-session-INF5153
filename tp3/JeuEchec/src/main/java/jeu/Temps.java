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
import donnee.ConvertirDonnees;

/**
 *
 * @author jmppr
 */
public class Temps {

    private long tempsDepart;
    private long tempsFin;
    private long tempsIADebutant;
    private long tempsIAAvance;
    private String nomIADebutant;
    private String nomIAAvance;

    public Temps() {
        chargerTemps();
    }

    public long getTempsDepart() {
        return tempsDepart;
    }

    public long getTempsFin() {
        return tempsFin;
    }

    public long getTempsIADebutant() {
        return tempsIADebutant;
    }

    public long getTempsIAAvance() {
        return tempsIAAvance;
    }

    public String getNomIADebutant() {
        return nomIADebutant;
    }

    public String getNomIAAvance() {
        return nomIAAvance;
    }

    public void setNom(int IADifficulte, String nom) {
        if (IADifficulte == 2) {
            nomIADebutant = nom;
        } else if (IADifficulte == 3) {
            nomIAAvance = nom;
        }
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

    public boolean estUnMeilleurTemps(int IADifficulte) {
        boolean resultat = false;
        if (IADifficulte == 2) {
            resultat = (tempsPartie() < tempsIADebutant);
        } else if (IADifficulte == 3) {
            resultat = (tempsPartie() < tempsIAAvance);
        }
        return resultat;
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
        nomIADebutant = meilleursTemps.getNomIADebutant();
        nomIAAvance = meilleursTemps.getNomIAAvance();
    }
}
