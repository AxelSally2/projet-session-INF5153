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
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;

public class RpcClient {

    static final int HTTP_PORT_NUMBER = 8081;

    public void postCoord(Mouvement mouv, int idJoueur) {
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            params.addElement(mouv.getRow());
            params.addElement(mouv.getCol());
            params.addElement(mouv.getRowDest());
            params.addElement(mouv.getColDest());
            params.addElement(idJoueur);
            server.execute("services.postCoord", params);
        } catch (Exception exception) {
            System.err.println(exception);
        }

    }

    public Map getCoord(int idJoueur, Mouvement mouv) {
        Map coord = new Hashtable();
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            params.addElement(idJoueur);
            Object result = server.execute("services.getCoord", params);
            coord = (Map) result;
        } catch (Exception exception) {
            System.err.println(exception);
        }
        if (!coord.isEmpty()) {
            mouv.setMouvement((int) coord.get("row"), (int) coord.get("col"), (int) coord.get("rowDest"), (int) coord.get("colDest"));
        }
        return coord;

    }

    public int getJoueurID() {
        int joueurId = 0;
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            Object result = server.execute("services.getJoueurID", params);
            joueurId = (int) result;
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return joueurId;
    }

    public void clearMap(int idJoueur) {
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            params.addElement(idJoueur);
            server.execute("services.clearMap", params);
        } catch (Exception exception) {
            System.err.println(exception);
        }

    }

    public void setJoueurAJouer(int idJoueur) {
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            params.addElement(idJoueur);
            server.execute("services.setJoueurAJouer", params);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    public int getJoueurAJouer(int idJoueur) {
        int joueurAjouer = 0;
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            params.addElement(idJoueur);
            Object result = server.execute("services.getJoueurAJouer", params);
            joueurAjouer = (int) result;
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return joueurAjouer;
    }

    public boolean serveurEnLigne() {
        boolean enLigne = false;
        try {
            XmlRpcClient server = new XmlRpcClient("localhost", HTTP_PORT_NUMBER);
            Vector params = new Vector();
            Object result = server.execute("services.serveurEnLigne", params);
            enLigne = (boolean) result;
        } catch (Exception exception) {
            System.err.println(exception);
            return enLigne;
        }
        return enLigne;
    }
}
