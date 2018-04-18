package com.gdw.tictactoe;

/**
 * Created by c3media on 18-Apr-18.
 */

public class BrainBox {
    public static final String DEBUGTAG = "GDW";
    //private String[][] theBoard;
    //private Board boardClass = new Board();

    // find the position of opponent and decide to defend or attack
    // if return is true then attack if return is false then defend
    public boolean brain3x3Board(String[][] theBoard, String oppSymbol){
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == oppSymbol && theBoard[2][2] == "") {
            return true;
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            return true;
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == oppSymbol && theBoard[0][0] == "") {
            return true;
        }
        else if (theBoard[0][2] == theBoard[1][1] && theBoard[0][2] == oppSymbol && theBoard[2][0] == "") {
            return true;
        }
        else if (theBoard[0][2] == theBoard[2][0] && theBoard[0][2] == oppSymbol && theBoard[1][1] == "") {
            return true;
        }
        else if (theBoard[1][1] == theBoard[2][0] && theBoard[1][1] == oppSymbol && theBoard[0][2] == "") {
            return true;
        }

        for (int k = 0; k < 3; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[0][k] == oppSymbol && theBoard[2][k] == "") {
                return true;
            }
            else if (theBoard[0][k] == theBoard[2][k] && theBoard[0][k] == oppSymbol && theBoard[1][k] == "") {
                return true;
            }
            else if (theBoard[1][k] == theBoard[2][k] && theBoard[1][k] == oppSymbol && theBoard[0][k] == "") {
                return true;
            }

            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][0] == oppSymbol && theBoard[k][2] == "") {
                return true;
            }
            else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][0] == oppSymbol && theBoard[k][1] == "") {
                return true;
            }
            else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][1] == oppSymbol && theBoard[k][0] == "") {
                return true;
            }

        }

        return false;

    }





    //find the position of opponent and pick a defending position to avoid loss on 5x5 board
    public boolean brain5x5Board(String[][] theBoard, String oppSymbol){
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == oppSymbol && theBoard[4][4] == "") {
            return true;
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == oppSymbol && theBoard[3][3] == ""){
            return true;
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == oppSymbol && theBoard[2][2] == "") {
            return true;
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            return true;
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == theBoard[3][3] && theBoard[1][1] == theBoard[4][4] && theBoard[1][1] == oppSymbol && theBoard[0][0] == "") {
            return true;
        }

        if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == oppSymbol && theBoard[4][0] == "") {
            return true;
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == oppSymbol && theBoard[3][1] == "") {
            return true;
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == oppSymbol && theBoard[2][2] == "") {
            return true;
        }
        else if (theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == oppSymbol && theBoard[1][3] == "") {
            return true;
        }
        else if (theBoard[1][3] == theBoard[2][2] && theBoard[1][3] == theBoard[3][1] && theBoard[1][3] == theBoard[4][0] && theBoard[1][3] == oppSymbol && theBoard[0][4] == "") {
            return true;
        }

        for (int k = 0; k < 5; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[0][k] == oppSymbol && theBoard[4][k] == "") {
                return true;
            }
            else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[4][k] && theBoard[0][k] == oppSymbol && theBoard[3][k] == "") {
                return true;
            }
            else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == oppSymbol && theBoard[2][k] == "") {
                return true;
            }
            else if (theBoard[0][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == oppSymbol && theBoard[1][k] == "") {
                return true;
            }
            else if (theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[1][k] == oppSymbol && theBoard[0][k] == "") {
                return true;
            }


            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][0] == oppSymbol && theBoard[k][4] == "") {
                return true;
            }
            else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][4] && theBoard[k][0] == oppSymbol && theBoard[k][3] == "") {
                return true;
            }
            else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == oppSymbol && theBoard[k][2] == "") {
                return true;
            }
            else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == oppSymbol && theBoard[k][1] == "") {
                return true;
            }
            else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][1] == oppSymbol && theBoard[k][0] == "") {
                return true;
            }

        }

        return false;
    }
    

}

