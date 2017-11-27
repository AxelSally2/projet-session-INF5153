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
package fichier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author jmppr
 */
public class ChargerFichier {

    private File fichier;

    public ChargerFichier() {
        fichier = new File("");

    }

    /**
     * Retourne le contenu du fichier dans une chaine de caractere
     *
     * @return Le contenu du fichier
     * @throws FileNotFoundException
     */
    public String contenuFichier() throws FileNotFoundException {
        FileChooser fichierChoisie = new FileChooser();
        fichierChoisie.setTitle("Ouvrir fichier");
        fichier = fichierChoisie.showOpenDialog(new Stage());
        Scanner scan = new Scanner(fichier);
        scan.useDelimiter("\\Z");
        return scan.next();
    }
}
