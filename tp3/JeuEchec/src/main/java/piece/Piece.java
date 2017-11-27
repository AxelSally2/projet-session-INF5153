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
package piece;

/**
 *
 * @author jmppr
 */
public abstract class Piece {

    protected String couleur;
    protected int row;
    protected int col;

    public Piece(String couleur, int row, int col) {
        this.couleur = couleur;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Retourne vrai si la colonne et la ligne de la piece sélection et la
     * destination de la colonne et la ligne de cette piece se situe bien dans
     * la table d'échec.
     *
     * @param row La ligne de destionation d'une piece
     * @param col La colonee de destionation d'une piece
     * @return Vrai si la colonne et la ligne de la piece sélection et la
     * destination de la colonne et la ligne de cette piece se situe bien dans
     * la table d'échec
     */
    protected boolean estDansLaTable(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7
                && this.row >= 0 && this.row <= 7 && this.col >= 0 && this.col <= 7;
    }

    /**
     * Retourne vrai si la piece sélectionne effectue un mouvement valide.
     *
     * @param row La ligne de destionation d'une piece
     * @param col La colonee de destionation d'une piece
     * @return Vrai si la piece sélectionne effectue un mouvement valide
     */
    public abstract boolean estDeplacementValide(int row, int col);
}
