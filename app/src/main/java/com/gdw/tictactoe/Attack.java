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

public class Attack{
    private int row = -1, col = -1;
    private int arrayDefend[] = new int[3];
    public static final String DEBUGTAG = "GDW";


    public void assignIndex(int row, int col, int[][] boxId){
        arrayDefend[0] = row;
        arrayDefend[1] = col;
        arrayDefend[2] = boxId[row][col];
    }
    //board row 3x3
    public int[] row3x3Attack(String[][] theBoard, int[][] box3x3Id, String oppSymbol){
        //Toast.makeText(getApplicationContext(), "ATTACK", Toast.LENGTH_LONG).show();
        for(int m = 0; m < 3; m++) {
            if (theBoard[m][0] == oppSymbol && theBoard[m][1] == oppSymbol && theBoard[m][2] == "") {
                //insert O
                assignIndex(m, 2, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][0] == oppSymbol && theBoard[m][1] == "" && theBoard[m][2] == oppSymbol) {
                //insert O
                assignIndex(m, 1, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][0] == "" && theBoard[m][1] == oppSymbol && theBoard[m][2] == oppSymbol) {
                //insert O
                assignIndex(m, 0, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[0][m] == oppSymbol && theBoard[1][m] == oppSymbol && theBoard[2][m] == "") {
                //insert O
                assignIndex(2, m, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[0][m] == oppSymbol && theBoard[1][m] == "" && theBoard[2][m] == oppSymbol) {
                //insert O
                assignIndex(1, m, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[0][m] == "" && theBoard[1][m] == oppSymbol && theBoard[2][m] == oppSymbol) {
                //insert O
                assignIndex(0, m, box3x3Id);
                return arrayDefend;
                //return
            }
        }

        return col3x3Attack(theBoard, box3x3Id, oppSymbol);
    }


    //board col 3x3
    public int[] col3x3Attack(String[][] theBoard, int[][] box3x3Id, String oppSymbol){
        for(int m = 0; m < 3; m++) {

            // row
            if (theBoard[m][0] == oppSymbol && theBoard[m][1] == "") {
                //insert O
                assignIndex(m, 1, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][0] == oppSymbol && theBoard[m][2] == "") {
                //insert O
                assignIndex(m, 2, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][1] == oppSymbol && theBoard[m][0] == "") {
                //insert O
                assignIndex(m, 0, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][1] == oppSymbol && theBoard[m][2] == "") {
                //insert O
                assignIndex(m, 2, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][2] == oppSymbol && theBoard[m][0] == "") {
                //insert O
                assignIndex(m, 0, box3x3Id);
                return arrayDefend;
            }
            else if (theBoard[m][2] == oppSymbol && theBoard[m][1] == "") {
                //insert O
                assignIndex(m, 1, box3x3Id);
                return arrayDefend;
            }
            // column
            else if (theBoard[0][m] == oppSymbol && theBoard[1][m] == "") {
                //insert O
                assignIndex(1, m, box3x3Id);
                return arrayDefend;
                //return
            }
            if (theBoard[0][m] == oppSymbol && theBoard[2][m] == "") {
                //insert O
                assignIndex(2, m, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[1][m] == oppSymbol && theBoard[0][m] == "") {
                //insert O
                assignIndex(0, m, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[1][m] == oppSymbol && theBoard[2][m] == "") {
                //insert O
                assignIndex(2, m, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[2][m] == oppSymbol && theBoard[0][m] == "") {
                //insert O
                assignIndex(0, m, box3x3Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[2][m] == oppSymbol && theBoard[1][m] == "") {
                //insert O
                assignIndex(1, m, box3x3Id);
                return arrayDefend;
                //return
            }
        }

        return diagonal03x3Attack(theBoard, box3x3Id, oppSymbol);
    }


    //board diagonal 0
    public int[] diagonal03x3Attack(String[][] theBoard, int[][] box3x3Id, String oppSymbol){
        if(theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            assignIndex(1, 1, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[0][0] == oppSymbol && theBoard[2][2] == "") {
            //insert O
            assignIndex(2, 2, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[0][0] == "") {
            //insert O
            assignIndex(0, 0, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[2][2] == "") {
            //insert O
            assignIndex(2, 2, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[2][2] == oppSymbol && theBoard[0][0] == "") {
            //insert O
            assignIndex(0, 0, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[2][2] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            assignIndex(1, 1, box3x3Id);
            return arrayDefend;
            //return
        }

        return diagonal13x3Attack(theBoard, box3x3Id, oppSymbol);
    }

    //board diagonal 1
    public int[] diagonal13x3Attack(String[][] theBoard, int[][] box3x3Id, String oppSymbol){
        if(theBoard[0][2] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            assignIndex(1, 1, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[0][2] == oppSymbol && theBoard[2][0] == "") {
            //insert O
            assignIndex(2, 0, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[0][2] == "") {
            //insert O
            assignIndex(0, 2, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[2][0] == "") {
            //insert O
            assignIndex(2, 0, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[2][0] == oppSymbol && theBoard[0][2] == "") {
            //insert O
            assignIndex(0, 2, box3x3Id);
            return arrayDefend;
            //return
        }
        else if(theBoard[2][0] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            assignIndex(1, 1, box3x3Id);
            return arrayDefend;
            //return
        }
        arrayDefend[2] = -1;
        return arrayDefend;
    }



    // board 5x5

    //board row 5x5
    public int[] row5x5Attack(String[][] theBoard, int[][] box5x5Id, String oppSymbol) {
        // game optimization, place symbol in row 0 col 0 if the opponent move starts at row 2 col 2
        for (int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                if (theBoard[m][n] == oppSymbol && theBoard[m][0] == "") {
                    //insert O
                    assignIndex(m, 0, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[m][1] == "") {
                    //insert O
                    assignIndex(m, 1, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[m][2] == "") {
                    //insert O
                    assignIndex(m, 2, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[m][3] == "") {
                    //insert O
                    assignIndex(m, 3, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[m][4] == "") {
                    //insert O
                    assignIndex(m, 4, box5x5Id);
                    return arrayDefend;
                }
            }
        }

        return col5x5Attack(theBoard, box5x5Id, oppSymbol);
    }



    //board col 5x5
    public int[] col5x5Attack(String[][] theBoard, int[][] box5x5Id, String oppSymbol){
        for(int m = 0; m < 5; m++) {
            for (int n = 0; n < 5; n++) {
                if (theBoard[n][m] == oppSymbol && theBoard[0][m] == "") {
                    //insert O
                    assignIndex(0, m, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[n][m] == oppSymbol && theBoard[1][m] == "") {
                    //insert O
                    assignIndex(1, m, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[n][m] == oppSymbol && theBoard[2][m] == "") {
                    //insert O
                    assignIndex(2, m, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[n][m] == oppSymbol && theBoard[3][m] == "") {
                    //insert O
                    assignIndex(3, m, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[n][m] == oppSymbol && theBoard[4][m] == "") {
                    //insert O
                    assignIndex(4, m, box5x5Id);
                    return arrayDefend;
                }
            }
        }

        return diagonal05x5Attack(theBoard, box5x5Id, oppSymbol);
    }


    //board diagonal 0 5x5
    public int[] diagonal05x5Attack(String[][] theBoard, int[][] box5x5Id, String oppSymbol){
        for(int m = 0; m < 5; m++) {
            if (theBoard[m][m] == oppSymbol && theBoard[0][0] == "") {
                //insert O
                assignIndex(0, 0, box5x5Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][m] == oppSymbol && theBoard[1][1] == "") {
                //insert O
                assignIndex(1, 1, box5x5Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][m] == oppSymbol && theBoard[2][2] == "") {
                //insert O
                assignIndex(2, 2, box5x5Id);
                return arrayDefend;
                //return
            }
            else if (theBoard[m][m] == oppSymbol && theBoard[3][3] == "") {
                //insert
                assignIndex(3, 3, box5x5Id);
                return arrayDefend;
            }
            else if (theBoard[m][m] == oppSymbol && theBoard[4][4] == "") {
                //insert
                assignIndex(4, 4, box5x5Id);
                return arrayDefend;
            }
        }

        return diagonal15x5Attack(theBoard, box5x5Id, oppSymbol);
    }


    //board diagonal 1 5x5
    public int[] diagonal15x5Attack(String[][] theBoard, int[][] box5x5Id, String oppSymbol){
        for(int m = 0; m < 5; m++) {
            for(int n = 5; n < 0; --n) {
                if (theBoard[m][n] == oppSymbol && theBoard[0][4] == "") {
                    //insert O
                    assignIndex(0, 4, box5x5Id);
                    return arrayDefend;
                    //return
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[1][3] == "") {
                    //insert O
                    assignIndex(1, 3, box5x5Id);
                    return arrayDefend;
                    //return
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[2][2] == "") {
                    //insert O
                    assignIndex(2, 2, box5x5Id);
                    return arrayDefend;
                    //return
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[3][1] == "") {
                    //insert
                    assignIndex(3, 1, box5x5Id);
                    return arrayDefend;
                }
                else if (theBoard[m][n] == oppSymbol && theBoard[4][0] == "") {
                    //insert O
                    assignIndex(4, 0, box5x5Id);
                    return arrayDefend;
                }
            }
        }

        arrayDefend[2] = -1;
        return arrayDefend;
    }



}
