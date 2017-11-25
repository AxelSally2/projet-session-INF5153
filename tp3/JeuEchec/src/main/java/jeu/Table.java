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

import piece.Piece;

/**
 *
 * @author jmppr
 */
public class Table {

    Piece tablePieces[][];

    public Table(Piece tablePices[][]) {
        this.tablePieces = new Piece[8][8];
    }

    public Piece getPiece(int x, int y) {
        return tablePieces[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        tablePieces[x][y] = piece;
    }

}
