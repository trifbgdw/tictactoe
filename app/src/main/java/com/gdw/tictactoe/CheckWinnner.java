/*
 * Copyright 2017 The Android Open Source Project
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


package com.gdw.tictactoe;



public class CheckWinnner{



    /** check if there is a winner or not
     * 1. check diagonals
     * 2. check rows,
     * 3. check columns.
     */

    public boolean board3x3Win(String[][] theBoard) {
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] != "") {
            return true;
        }

        if (theBoard[0][2] == theBoard[1][1] && theBoard[0][2] == theBoard[2][0] && theBoard[0][2] != "") {
            return true;
        }

        for (int k = 0; k < 3; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[0][k] != "") {
                return true;
            }
            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][0] != "") {
                return true;
            }

        }
        //return false if there is no winner
        return false;
    }
    public boolean board5x5Win(String[][] theBoard) {
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] != "") {
            return true;
        }

        if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] != "") {
            return true;
        }

        for (int k = 0; k < 5; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] != "") {
                return true;
            }
            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] != "") {
                return true;
            }

        }
        //return false if there is no winner
        return false;
    }





}
