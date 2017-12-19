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

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author jmppr
 */
public class ConvertirDonnees {

    private final String allowTypes[]  = new String[]{
            "jeu.**",
            "piece.**"
        };

    public ConvertirDonnees() {
    }

    /**
     * Transforme un objet en une chaine de caractères en format XML
     *
     * @param objet Objet à transformer en string XML
     * @return Une chaine de caractère en format XML
     */
    public String objetToXML(Object objet) {
        XStream xstream = new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(allowTypes);
        String xml = xstream.toXML(objet);
        return xml;
    }

    /**
     * Transforme la chaine de caractère en format XML en objet
     *
     * @param str La chaine de caractère en format XML à transformé en objet
     * @return Un tableau de pièces
     */
    public Object XMLToObjet(String str) {
        XStream xstream = new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(allowTypes);
        Object objet;
        objet = xstream.fromXML(str);
        return objet;
    }
}
