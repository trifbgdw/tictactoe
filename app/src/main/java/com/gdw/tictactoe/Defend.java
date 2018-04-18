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


import android.util.Log;
import android.widget.Toast;

public class Defend {
    private int row = -1, col = -1;
    private int arrayDefend[] = new int[3];
    public static final String DEBUGTAG = "GDW";
    //private String[][] theBoard;
    //private Board boardClass = new Board();

    //find the position of opponent and pick a defending position to avoid loss on 3x3 board
    public int[] board3x3Defend(String[][] theBoard, int[][] box3x3Id, String symbol){
        //Toast.makeText(getApplicationContext(), "DEFEND", Toast.LENGTH_LONG).show();
        //this.theBoard = theBoard;
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == symbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box3x3Id[2][2];
            return arrayDefend;
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == symbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box3x3Id[1][1];
            return arrayDefend;
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == symbol && theBoard[0][0] == "") {
            row = 0; col = 0;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box3x3Id[0][0];
            return arrayDefend;
        }
        else if (theBoard[0][2] == theBoard[1][1] && theBoard[0][2] == symbol && theBoard[2][0] == "") {
            row = 2; col = 0;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box3x3Id[2][0];
            return arrayDefend;
        }
        else if (theBoard[0][2] == theBoard[2][0] && theBoard[0][2] == symbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box3x3Id[1][1];
            return arrayDefend;
        }
        else if (theBoard[1][1] == theBoard[2][0] && theBoard[1][1] == symbol && theBoard[0][2] == "") {
            row = 0; col = 2;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box3x3Id[0][2];
            return arrayDefend;
        }
        else {
            for (int k = 0; k < 3; k++) {
                // check the columns
                if (theBoard[0][k] == theBoard[1][k] && theBoard[0][k] == symbol && theBoard[2][k] == "") {
                    row = 2;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box3x3Id[2][k];
                    return arrayDefend;
                } else if (theBoard[0][k] == theBoard[2][k] && theBoard[0][k] == symbol && theBoard[1][k] == "") {
                    row = 1;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box3x3Id[1][k];
                    return arrayDefend;
                } else if (theBoard[1][k] == theBoard[2][k] && theBoard[1][k] == symbol && theBoard[0][k] == "") {
                    row = 0;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box3x3Id[0][k];
                    return arrayDefend;
                }

                // check the rows
                if (theBoard[k][0] == theBoard[k][1] && theBoard[k][0] == symbol && theBoard[k][2] == "") {
                    row = k;
                    col = 2;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box3x3Id[k][2];
                    return arrayDefend;
                } else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][0] == symbol && theBoard[k][1] == "") {
                    row = k;
                    col = 1;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box3x3Id[k][1];
                    return arrayDefend;
                } else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][1] == symbol && theBoard[k][0] == "") {
                    row = k;
                    col = 0;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box3x3Id[k][0];
                    return arrayDefend;
                }

            }
        }
        arrayDefend[2] = -1;
        return arrayDefend;
    }

    //find the position of opponent and pick a defending position to avoid loss on 5x5 board
    public int[] board5x5Defend(String[][] theBoard, int[][] box5x5Id, String symbol){
        // Check the diagonals - from left
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == symbol && theBoard[4][4] == "") {
            row = 4; col = 4;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[4][4];
            return arrayDefend;
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == symbol && theBoard[3][3] == "") {
            row = 3; col = 3;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[3][3];
            return arrayDefend;
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == symbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[2][2];
            return arrayDefend;
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == symbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[1][1];
            return arrayDefend;
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == theBoard[3][3] && theBoard[1][1] == theBoard[4][4] && theBoard[1][1] == symbol && theBoard[0][0] == "") {
            row = 0; col = 0;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[0][0];
            return arrayDefend;
        }
        // Check the diagonals - from right
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == symbol && theBoard[4][0] == "") {
            row = 4; col = 0;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[4][0];
            return arrayDefend;
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == symbol && theBoard[3][1] == "") {
            row = 3; col = 1;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[3][1];
            return arrayDefend;
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == symbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[2][2];
            return arrayDefend;
        }
        else if (theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == symbol && theBoard[1][3] == "") {
            row = 1; col = 3;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[1][3];
            return arrayDefend;
        }
        else if (theBoard[1][3] == theBoard[2][2] && theBoard[1][3] == theBoard[3][1] && theBoard[1][3] == theBoard[4][0] && theBoard[1][3] == symbol && theBoard[0][4] == "") {
            row = 0; col = 4;
            arrayDefend[0] = row; arrayDefend[1] = col; arrayDefend[2] = box5x5Id[0][4];
            return arrayDefend;
        }
        else {
            for (int k = 0; k < 5; k++) {
                // check the columns
                if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[0][k] == symbol && theBoard[4][k] == "") {
                    row = 4;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[4][k];
                    return arrayDefend;
                } else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[4][k] && theBoard[0][k] == symbol && theBoard[3][k] == "") {
                    row = 3;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[3][k];
                    return arrayDefend;
                } else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == symbol && theBoard[2][k] == "") {
                    row = 2;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[2][k];
                    return arrayDefend;
                } else if (theBoard[0][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == symbol && theBoard[1][k] == "") {
                    row = 1;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[1][k];
                    return arrayDefend;
                } else if (theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[1][k] == symbol && theBoard[0][k] == "") {
                    row = 0;
                    col = k;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[0][k];
                    return arrayDefend;
                }


                // check the rows
                if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][0] == symbol && theBoard[k][4] == "") {
                    row = k;
                    col = 4;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[k][4];
                    return arrayDefend;
                } else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][4] && theBoard[k][0] == symbol && theBoard[k][3] == "") {
                    row = k;
                    col = 3;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[k][3];
                    return arrayDefend;
                } else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == symbol && theBoard[k][2] == "") {
                    row = k;
                    col = 2;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[k][2];
                    return arrayDefend;
                } else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == symbol && theBoard[k][1] == "") {
                    row = k;
                    col = 1;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[k][1];
                    return arrayDefend;
                } else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][1] == symbol && theBoard[k][0] == "") {
                    row = k;
                    col = 0;
                    arrayDefend[0] = row;
                    arrayDefend[1] = col;
                    arrayDefend[2] = box5x5Id[k][0];
                    return arrayDefend;
                }

            }
        }

        arrayDefend[2] = -1;
        return arrayDefend;
    }

}
