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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Board extends AppCompatActivity {
    public static final String DEBUGTAG = "GDW";
    FragmentTransaction fragmentTransaction;
    private int board;
    private String[][] theBoard;
    private int posx = 0, posy = 0, rowx, coly;
    private int row = -1, col = -1;
    private String symbol, oppSymbol;
    private boolean gameOver = false;
    private int moveCount = 0, oppMoveCount = 0;
    private String opponent;
    private int[] arrayDefend;
    int[] listOfBoxId3x3 = {R.id.box00, R.id.box01, R.id.box02, R.id.box10, R.id.box11, R.id.box12,
            R.id.box20, R.id.box21, R.id.box22};
    int[] listOfBoxId5x5 = {R.id.box00, R.id.box01, R.id.box02, R.id.box03, R.id.box04, R.id.box10, R.id.box11, R.id.box12,
            R.id.box13, R.id.box14, R.id.box20, R.id.box21, R.id.box22, R.id.box23, R.id.box24, R.id.box30, R.id.box31,
            R.id.box32, R.id.box33, R.id.box34, R.id.box40, R.id.box41, R.id.box42, R.id.box43, R.id.box44};
    int[][] box3x3Id = {{R.id.box00, R.id.box01, R.id.box02}, {R.id.box10, R.id.box11, R.id.box12},
            {R.id.box20, R.id.box21, R.id.box22}};
    int box5x5Id[][] = {{R.id.box00, R.id.box01, R.id.box02, R.id.box03, R.id.box04}, {R.id.box10, R.id.box11, R.id.box12,
            R.id.box13, R.id.box14}, {R.id.box20, R.id.box21, R.id.box22, R.id.box23, R.id.box24}, {R.id.box30, R.id.box31,
            R.id.box32, R.id.box33, R.id.box34}, {R.id.box40, R.id.box41, R.id.box42, R.id.box43, R.id.box44}};
    int boxId;
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    Defend defend;
    CheckWinnner checkWinnner;
    Attack attack;
    BrainBox brainBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        //get value of option selected by user and load the appropriate board with opponent
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String[] boardOptions = extras.getStringArray(MainActivity.BOARD_OPTIONS);
            //Toast.makeText(getApplicationContext(), boardOptions[1], Toast.LENGTH_LONG).show();

            opponent = boardOptions[0];
            if(Arrays.asList(boardOptions).contains("3x3")){
                //Toast.makeText(getApplicationContext(), boardOptions[1], Toast.LENGTH_LONG).show();
                //get board dimension
                board = 3;
                theBoard = new String[board][board];
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Board3x3());
                fragmentTransaction.commit();
                //getSupportActionBar().setTitle("tic tac toe");
            }
            else if(Arrays.asList(boardOptions).contains("5x5")){
                //get board dimension
                board = 5;
                theBoard = new String[board][board];
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new Board5x5());
                fragmentTransaction.commit();
                //getSupportActionBar().setTitle("tic tac toe");
            }

            //assign symbols to players
            if(Arrays.asList(boardOptions).contains("X")){
                symbol = "X"; oppSymbol = "O";
            }
            else{
                symbol = "O"; oppSymbol = "X";
            }
        }
        initBoard();
        defend = new Defend();
        checkWinnner = new CheckWinnner();
        attack = new Attack();
        brainBox = new BrainBox();
    }

    public void sort(){

    }

    public void initBoard(){
        for (int i = 0; i < board; i++) {
            for (int j = 0; j < board; j++) {
                //Initialize the board with empty spaces
                theBoard[i][j] = "";
            }
        }
    }

    public void boxClicked(View view){
        //check whose turn it is
        if(moveCount < oppMoveCount || moveCount == oppMoveCount) {
            //Get the id of the box clicked
            TextView box = (TextView) findViewById(view.getId());
            //Check if the box is empty and game is not over
            String boxContent = (String) box.getText();
            if (boxContent == "" && !gameOver) {
                /**Find the location of the box clicked, set text and
                 * lock position in theBoard. this will be used by single player to determine where to play
                 */

                switch (box.getId()) {
                    case R.id.box00:
                        posx = 0;
                        posy = 0;
                        break;
                    case R.id.box01:
                        posx = 0;
                        posy = 1;
                        break;
                    case R.id.box02:
                        posx = 0;
                        posy = 2;
                        break;
                    case R.id.box03:
                        posx = 0;
                        posy = 3;
                        break;
                    case R.id.box04:
                        posx = 0;
                        posy = 4;
                        break;
                    case R.id.box10:
                        posx = 1;
                        posy = 0;
                        break;
                    case R.id.box11:
                        posx = 1;
                        posy = 1;
                        break;
                    case R.id.box12:
                        posx = 1;
                        posy = 2;
                        break;
                    case R.id.box13:
                        posx = 1;
                        posy = 3;
                        break;
                    case R.id.box14:
                        posx = 2;
                        posy = 2;
                        break;
                    case R.id.box20:
                        posx = 2;
                        posy = 0;
                        break;
                    case R.id.box21:
                        posx = 2;
                        posy = 1;
                        break;
                    case R.id.box22:
                        posx = 2;
                        posy = 2;
                        break;
                    case R.id.box23:
                        posx = 2;
                        posy = 3;
                        break;
                    case R.id.box24:
                        posx = 2;
                        posy = 4;
                        break;
                    case R.id.box30:
                        posx = 3;
                        posy = 0;
                        break;
                    case R.id.box31:
                        posx = 3;
                        posy = 1;
                        break;
                    case R.id.box32:
                        posx = 3;
                        posy = 2;
                        break;
                    case R.id.box33:
                        posx = 3;
                        posy = 3;
                        break;
                    case R.id.box34:
                        posx = 3;
                        posy = 4;
                        break;
                    case R.id.box40:
                        posx = 4;
                        posy = 0;
                        break;
                    case R.id.box41:
                        posx = 4;
                        posy = 1;
                        break;
                    case R.id.box42:
                        posx = 4;
                        posy = 2;
                        break;
                    case R.id.box43:
                        posx = 4;
                        posy = 3;
                        break;
                    case R.id.box44:
                        posx = 4;
                        posy = 4;
                        break;
                }

                placeSymbol(posx, posy, symbol);
                box.setText(symbol);

                // check for winner
                checkForWinner(symbol);


                moveCount++;

                //gameOver = checkEnd(symbol);

                if (!gameOver) {
                    //Toast.makeText(getApplicationContext(), opponent, Toast.LENGTH_LONG).show();
                    //check to confirm that game is played by a single person
                    if (opponent.contains("single")) {
                        //delay for some seconds to make move
                        //executorService.scheduleAtFixedRate(new Runnable() {
                            //@Override
                            //public void run() {
                                //getSingleOpponentMove();
                            //}
                        //}, 0, 3, TimeUnit.SECONDS);
                        try {
                            /**new Timer().schedule(
                                    new TimerTask() {
                                        @Override
                                        public void run() {
                                            //try {
                                            getSingleOpponentMove();
                                            //}catch (NullPointerException e) {
                                                //Log.e(DEBUGTAG, "" + e);
                                            //}
                                        }
                                    }, 2000
                            );*/
                            getSingleOpponentMove();
                        }catch (NullPointerException e) {
                            Log.e(DEBUGTAG, e.toString());
                            //Log.getStackTraceString(e);
                            //Log.e(e.getClass().getName(), e.getMessage(), e.getCause());
                        }


                    }
                }

            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Not your turn", Toast.LENGTH_LONG).show();
        }
    }

    //a single game move
    public void getSingleOpponentMove(){
        //Toast.makeText(getApplicationContext(), board3x3Defend(), Toast.LENGTH_LONG).show();
        //Log.e(DEBUGTAG, ""+board3x3Defend());

        //pick a random position at start of game
        if(moveCount == 1){
            boxId = startPosition();
        }
        else {
            //defend against loss to either win or draw
            if (board == 3) {
                // find the position of opponent and decide to defend or attack
                // if return is true then attack if return is false then defend
                if(brainBox.brain3x3Board(theBoard, oppSymbol)) {
                    arrayDefend = attack.row3x3Attack(theBoard, box3x3Id, oppSymbol);
                }
                else{
                    arrayDefend = defend.board3x3Defend(theBoard, box3x3Id, symbol);
                }

                boxId = arrayDefend[2];
                //boxId = attackToWinBoard3x3();
                rowx = arrayDefend[0];
                coly = arrayDefend[1];
                //Log.e(DEBUGTAG, ""+boxId);
                if (boxId == -1) {
                    // ATTACK OPPONENT
                    arrayDefend = attack.row3x3Attack(theBoard, box3x3Id, oppSymbol);
                    boxId = arrayDefend[2];
                    rowx = arrayDefend[0];
                    coly = arrayDefend[1];


                    if (boxId == -1) {
                        Toast.makeText(getApplicationContext(), "Game over! It's a draw.", Toast.LENGTH_LONG).show();
                        gameOver = true;
                    }
                }
            }
            if(board == 5) {
                // find the position of opponent and decide to defend or attack
                // if return is true then attack if return is false then defend
                if(brainBox.brain5x5Board(theBoard, oppSymbol)) {
                    arrayDefend = attack.row5x5Attack(theBoard, box5x5Id, oppSymbol);
                }
                else{
                    arrayDefend = defend.board5x5Defend(theBoard, box5x5Id, symbol);
                }

                boxId = arrayDefend[2];
                //boxId = attackToWinBoard3x3();
                rowx = arrayDefend[0];
                coly = arrayDefend[1];
                //Toast.makeText(getApplicationContext(), "boxId="+boxId, Toast.LENGTH_LONG).show();
                if (boxId == -1) {
                    // ATTACK OPPONENT
                    arrayDefend = attack.row5x5Attack(theBoard, box5x5Id, oppSymbol);
                    boxId = arrayDefend[2];
                    rowx = arrayDefend[0];
                    coly = arrayDefend[1];


                    if (boxId == -1) {
                        Toast.makeText(getApplicationContext(), "Game over! It's a draw.", Toast.LENGTH_LONG).show();
                        gameOver = true;
                    }
                }
            }
        }

        TextView box = (TextView) findViewById(boxId);

        //Toast.makeText(getApplicationContext(), "boxId="+boxId, Toast.LENGTH_LONG).show();
        String boxContent = (String) box.getText();
        //Toast.makeText(getApplicationContext(), "boxContent="+boxContent+"row="+rowx+"col="+coly, Toast.LENGTH_LONG).show();
        if (boxContent == "" && !gameOver) {
            placeSymbol(rowx, coly, oppSymbol);
            box.setText(oppSymbol);

            // check for winner
            checkForWinner(oppSymbol);

            oppMoveCount++;
        }
        //Log.e(DEBUGTAG, "" + theBoard[0][0]+" | "+theBoard[0][1]+" | "+theBoard[0][2]);
        //Log.e(DEBUGTAG, "" + theBoard[1][0]+" | "+theBoard[1][1]+" | "+theBoard[1][2]);
        //Log.e(DEBUGTAG, "" + theBoard[2][0]+" | "+theBoard[2][1]+" | "+theBoard[2][2]);

    }

    public int startPosition(){

        if(board == 3){

            if(theBoard[1][1] == ""){
                rowx = 1; coly = 1;
                return box3x3Id[1][1];
            }
            else if(theBoard[0][0] == ""){
                rowx = 0; coly = 0;
                return box3x3Id[0][0];
            }
            else if(theBoard[0][board-1] == ""){
                rowx = 0; coly = board-1;
                return box3x3Id[0][board-1];
            }
        }
        else if(board == 5){

            if(theBoard[2][2] == ""){
                rowx = 2; coly = 2;
                return box5x5Id[2][2];
            }
            else if(theBoard[0][0] == ""){
                rowx = 0; coly = 0;
                return box5x5Id[0][0];
            }
            else if(theBoard[0][board-1] == ""){
                rowx = 0; coly = board-1;
                return box5x5Id[0][board-1];
            }
        }
        return -1;
    }

    public int row(){
        Random r = new Random();
        int row = r.nextInt(board);
        return row;

    }

    public int col(){
        Random c = new Random();
        int col = c.nextInt(board);
        return col;
    }

    public void checkForWinner(String sym){

        try {
            if (checkWinnner.board3x3Win(theBoard) && board == 3) {
                gameOver = true;
                Toast.makeText(getApplicationContext(), "Game Over " + sym + " won", Toast.LENGTH_LONG).show();
            } else if (checkWinnner.board5x5Win(theBoard) && board == 5) {
                gameOver = true;
                Toast.makeText(getApplicationContext(), "Game Over " + sym + " won", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){ // catches both ArrayIndexOutOfBoundsException and NullPointerException
            Log.e(DEBUGTAG, "" + e);
        }
    }

    //defense mechanism against human opponent in a single game


    //start new game and reset board
    public void newGame(View view){
        //Toast.makeText(getApplicationContext(), "board="+board, Toast.LENGTH_LONG).show();
        //initialize move counter

        TextView boxes;
        for (int i = 0; i < board; i++) {
            for (int j = 0; j < board; j++) {
                //Initiate the board with empty spaces
                theBoard[i][j] = "";
            }
        }

        //Get the list of all the Textviews

        if(board == 3) {

            for (int ids : listOfBoxId3x3) {
                boxes = (TextView) findViewById(ids);
                boxes.setText("");
            }
        }
        else if(board == 5){

            for (int ids : listOfBoxId5x5) {
                boxes = (TextView) findViewById(ids);
                boxes.setText("");
            }
        }

        gameOver = false;
        moveCount = 0;
        oppMoveCount = 0;

    }

    public void settings(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public String[][] getBoard() {
        return theBoard;
    }

    //add X or O symbol to the board at the position clicked by player
    public void placeSymbol(int posx, int posy, String symbol) {
        if (theBoard[posx][posy] == "") {
            theBoard[posx][posy] = symbol;
        }
    }

/**

    // ----------------------- DEFEND -------------------------------

    //find the position of opponent and pick a defending position to avoid loss on 3x3 board
    public int board3x3Defend(){
        //Toast.makeText(getApplicationContext(), "DEFEND", Toast.LENGTH_LONG).show();
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == symbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            return box3x3Id[2][2];
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == symbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            return box3x3Id[1][1];
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == symbol && theBoard[0][0] == "") {
            row = 0; col = 0;
            return box3x3Id[0][0];
        }
        else if (theBoard[0][2] == theBoard[1][1] && theBoard[0][2] == symbol && theBoard[2][0] == "") {
            row = 2; col = 0;
            return box3x3Id[2][0];
        }
        else if (theBoard[0][2] == theBoard[2][0] && theBoard[0][2] == symbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            return box3x3Id[1][1];
        }
        else if (theBoard[1][1] == theBoard[2][0] && theBoard[1][1] == symbol && theBoard[0][2] == "") {
            row = 0; col = 2;
            return box3x3Id[0][2];
        }

        for (int k = 0; k < 3; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[0][k] == symbol && theBoard[2][k] == "") {
                row = 2; col = k;
                return box3x3Id[2][k];
            }
            else if (theBoard[0][k] == theBoard[2][k] && theBoard[0][k] == symbol && theBoard[1][k] == "") {
                row = 1; col = k;
                return box3x3Id[1][k];
            }
            else if (theBoard[1][k] == theBoard[2][k] && theBoard[1][k] == symbol && theBoard[0][k] == "") {
                row = 0; col = k;
                return box3x3Id[0][k];
            }

            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][0] == symbol && theBoard[k][2] == "") {
                row = k; col = 2;
                return box3x3Id[k][2];
            }
            else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][0] == symbol && theBoard[k][1] == "") {
                row = k; col = 1;
                return box3x3Id[k][1];
            }
            else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][1] == symbol && theBoard[k][0] == "") {
                row = k; col = 0;
                return box3x3Id[k][0];
            }

        }

        return -1;
    }

    //find the position of opponent and pick a defending position to avoid loss on 5x5 board
    public int board5x5Defend(){
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == symbol && theBoard[4][4] == "") {
            row = 4; col = 4;
            return box5x5Id[4][4];
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == symbol && theBoard[3][3] == "") {
            row = 3; col = 3;
            return box5x5Id[3][3];
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == symbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            return box5x5Id[2][2];
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == symbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            return box5x5Id[1][1];
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == theBoard[3][3] && theBoard[1][1] == theBoard[4][4] && theBoard[1][1] == symbol && theBoard[0][0] == "") {
            row = 0; col = 0;
            return box5x5Id[0][0];
        }

        if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == symbol && theBoard[4][0] == "") {
            row = 4; col = 0;
            return box5x5Id[4][0];
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == symbol && theBoard[3][1] == "") {
            row = 3; col = 1;
            return box5x5Id[3][1];
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == symbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            return box5x5Id[2][2];
        }
        else if (theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == symbol && theBoard[1][3] == "") {
            row = 1; col = 3;
            return box5x5Id[1][3];
        }
        else if (theBoard[1][3] == theBoard[2][2] && theBoard[1][3] == theBoard[3][1] && theBoard[1][3] == theBoard[4][0] && theBoard[1][3] == symbol && theBoard[0][4] == "") {
            row = 0; col = 4;
            return box5x5Id[0][4];
        }

        for (int k = 0; k < 5; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[0][k] == symbol && theBoard[4][k] == "") {
                row = 4; col = k;
                return box5x5Id[4][k];
            }
            else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[4][k] && theBoard[0][k] == symbol && theBoard[3][k] == "") {
                row = 3; col = k;
                return box5x5Id[3][k];
            }
            else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == symbol && theBoard[2][k] == "") {
                row = 2; col = k;
                return box5x5Id[2][k];
            }
            else if (theBoard[0][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == symbol && theBoard[1][k] == "") {
                row = 1; col = k;
                return box5x5Id[1][k];
            }
            else if (theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[1][k] == symbol && theBoard[0][k] == "") {
                row = 0; col = k;
                return box5x5Id[0][k];
            }


            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][0] == symbol && theBoard[k][4] == "") {
                row = k; col = 4;
                return box5x5Id[k][4];
            }
            else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][4] && theBoard[k][0] == symbol && theBoard[k][3] == "") {
                row = k; col = 3;
                return box5x5Id[k][3];
            }
            else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == symbol && theBoard[k][2] == "") {
                row = k; col = 2;
                return box5x5Id[k][2];
            }
            else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == symbol && theBoard[k][1] == "") {
                row = k; col = 1;
                return box5x5Id[k][1];
            }
            else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][1] == symbol && theBoard[k][0] == "") {
                row = k; col = 0;
                return box5x5Id[k][0];
            }

        }

        return -1;
    }



// --------------------- ATTACK --------------

    //board row 3x3
    public int row3x3Board(){
        //Toast.makeText(getApplicationContext(), "ATTACK", Toast.LENGTH_LONG).show();
        for(int m = 0; m < 3; m++) {
            if (theBoard[m][0] == oppSymbol && theBoard[m][2] == "") {
                //insert O
                row = m;
                col = 2;
                return box3x3Id[m][2];
                //return
            }
            else if (theBoard[m][2] == oppSymbol && theBoard[m][1] == "") {
                //insert O
                row = m;
                col = 1;
                return box3x3Id[m][1];
            }
        }
        col3x3Board();
        return -1;
    }


    //board col 3x3
    public int col3x3Board(){
        for(int m = 0; m < 3; m++) {
            if (theBoard[0][m] == oppSymbol && theBoard[1][m] == "") {
                //insert O
                row = 1;
                col = m;
                return box3x3Id[1][m];
                //return
            }
            else if (theBoard[1][m] == oppSymbol && theBoard[2][m] == "") {
                //insert O
                row = 2;
                col = m;
                return box3x3Id[2][m];
                //return
            }
        }
        diagonal03x3Board();
        return -1;
    }


    //board diagonal 0
    public int diagonal03x3Board(){
        if(theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            row = 1;
            col = 1;
            return box3x3Id[1][1];
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[2][2] == "") {
            //insert O
            row = 2;
            col = 2;
            return box3x3Id[2][2];
            //return
        }
        else{
            //call diagonal 1
            diagonal13x3Board();
        }
        return -1;
    }

    //board diagonal 1
    public int diagonal13x3Board(){
        if(theBoard[0][2] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            row = 1;
            col = 1;
            return box3x3Id[1][1];
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[2][0] == "") {
            //insert O
            row = 2;
            col = 0;
            return box3x3Id[2][0];
            //return
        }
        else{
            Toast.makeText(getApplicationContext(), "It's a draw", Toast.LENGTH_LONG).show();
        }
        return -1;
    }



    // board 5x5

    //board row 5x5
    public int row5x5Board(){
        // game optimization, place symbol in row 0 col 0 if the opponent move starts at row 2 col 2
        if (theBoard[2][2] == oppSymbol && theBoard[0][0] == "") {
            //insert O
            row = 0; col = 0;
            return box5x5Id[0][0];
        }else {
            for (int m = 0; m < 3; m++) {
                if (theBoard[m][0] == oppSymbol && theBoard[m][4] == "") {
                    //insert O
                    row = m;
                    col = 4;
                    return box5x5Id[m][4];
                } else if (theBoard[m][4] == oppSymbol && theBoard[m][1] == "") {
                    //insert O
                    row = m;
                    col = 1;
                    return box5x5Id[m][1];
                } else if (theBoard[m][1] == oppSymbol && theBoard[m][2] == "") {
                    //insert O
                    row = m;
                    col = 2;
                    return box5x5Id[m][2];
                } else if (theBoard[m][2] == oppSymbol && theBoard[m][3] == "") {
                    //insert O
                    row = m;
                    col = 3;
                    return box5x5Id[m][3];
                }
            }
        }
        col5x5Board();
        return -1;
    }



    //board col 5x5
    public int col5x5Board(){
        for(int m = 0; m < 3; m++) {
            if (theBoard[0][m] == oppSymbol && theBoard[1][m] == "") {
                //insert O
                row = 1;
                col = m;
                return box5x5Id[1][m];
            }
            else if (theBoard[1][m] == oppSymbol && theBoard[2][m] == "") {
                //insert O
                row = 2;
                col = m;
                return box5x5Id[2][m];
            }
            else if (theBoard[2][m] == oppSymbol && theBoard[3][m] == "") {
                //insert O
                row = 3;
                col = m;
                return box5x5Id[3][m];
            }
            else if (theBoard[3][m] == oppSymbol && theBoard[4][m] == "") {
                //insert O
                row = 4;
                col = m;
                return box5x5Id[4][m];
            }
        }
        diagonal05x5Board();
        return -1;
    }


    //board diagonal 0 5x5
    public int diagonal05x5Board(){
        if(theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            //insert O
            row = 1;
            col = 1;
            return box5x5Id[1][1];
            //return
        }
        else if(theBoard[1][1] == oppSymbol && theBoard[2][2] == "") {
            //insert O
            row = 2;
            col = 2;
            return box5x5Id[2][2];
            //return
        }
        else if(theBoard[2][2] == oppSymbol && theBoard[3][3] == "") {
            //insert
            row = 3;
            col = 3;
            return box5x5Id[3][3];
        }
        else if(theBoard[3][3] == oppSymbol && theBoard[4][4] == "") {
            //insert
            row = 4;
            col = 4;
            return box5x5Id[4][4];
        }
        else {
            diagonal15x5Board();
        }
        return -1;
    }


    //board diagonal 1 5x5
    public int diagonal15x5Board(){
        if(theBoard[0][4] == oppSymbol && theBoard[1][3] == "") {
            //insert O
            row = 1;
            col = 3;
            return box5x5Id[1][3];
            //return
        }
        else if(theBoard[1][3] == oppSymbol && theBoard[2][2] == "") {
            //insert O
            row = 2;
            col = 2;
            return box5x5Id[2][2];
            //return
        }
        else if(theBoard[2][2] == oppSymbol && theBoard[3][1] == "") {
            //insert
            row = 3;
            col = 1;
            return box5x5Id[3][1];
        }
        else if(theBoard[3][1] == oppSymbol && theBoard[4][0] == "") {
            //insert O
            row = 4;
            col = 0;
            return box5x5Id[4][0];
        }
        else{
            Toast.makeText(getApplicationContext(), "It's a draw", Toast.LENGTH_LONG).show();
        }
        return -1;
    }
*/



    // ---------------------------------- CHECK FOR WINNER ---------------------------------------



    /** check if there is a winner or not
     * 1. check diagonals
     * 2. check rows,
     * 3. check columns.
     */
    /**
    public boolean board3x3Win() {
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
    public boolean board5x5Win() {
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
    }*/





/**
    //----------------------------------- ATTACK TO WIN --------------------------
    // this method, instead of defending, attacks to win

    public int attackToWinBoard3x3(){
        //Toast.makeText(getApplicationContext(), "ATTACK TO WIN", Toast.LENGTH_LONG).show();
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == oppSymbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            return box3x3Id[2][2];
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            return box3x3Id[1][1];
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == oppSymbol && theBoard[0][0] == "") {
            row = 0; col = 0;
            return box3x3Id[0][0];
        }
        else if (theBoard[0][2] == theBoard[1][1] && theBoard[0][2] == oppSymbol && theBoard[2][0] == "") {
            row = 2; col = 0;
            return box3x3Id[2][0];
        }
        else if (theBoard[0][2] == theBoard[2][0] && theBoard[0][2] == oppSymbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            return box3x3Id[1][1];
        }
        else if (theBoard[1][1] == theBoard[2][0] && theBoard[1][1] == oppSymbol && theBoard[0][2] == "") {
            row = 0; col = 2;
            return box3x3Id[0][2];
        }

        for (int k = 0; k < 3; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[0][k] == oppSymbol && theBoard[2][k] == "") {
                row = 2; col = k;
                return box3x3Id[2][k];
            }
            else if (theBoard[0][k] == theBoard[2][k] && theBoard[0][k] == oppSymbol && theBoard[1][k] == "") {
                row = 1; col = k;
                return box3x3Id[1][k];
            }
            else if (theBoard[1][k] == theBoard[2][k] && theBoard[1][k] == oppSymbol && theBoard[0][k] == "") {
                row = 0; col = k;
                return box3x3Id[0][k];
            }

            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][0] == oppSymbol && theBoard[k][2] == "") {
                row = k; col = 2;
                return box3x3Id[k][2];
            }
            else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][0] == oppSymbol && theBoard[k][1] == "") {
                row = k; col = 1;
                return box3x3Id[k][1];
            }
            else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][1] == oppSymbol && theBoard[k][0] == "") {
                row = k; col = 0;
                return box3x3Id[k][0];
            }
            else{
                board3x3Defend();
            }

        }

        return -1;
    }

    //find the position of opponent and pick a defending position to avoid loss on 5x5 board
    public int attackToWinBoard5x5(){
        // Check the diagonals
        if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == oppSymbol && theBoard[4][4] == "") {
            row = 4; col = 4;
            return box5x5Id[4][4];
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == oppSymbol && theBoard[3][3] == "") {
            row = 3; col = 3;
            return box5x5Id[3][3];
        }
        else if (theBoard[0][0] == theBoard[1][1] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == oppSymbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            return box5x5Id[2][2];
        }
        else if (theBoard[0][0] == theBoard[2][2] && theBoard[0][0] == theBoard[3][3] && theBoard[0][0] == theBoard[4][4] && theBoard[0][0] == oppSymbol && theBoard[1][1] == "") {
            row = 1; col = 1;
            return box5x5Id[1][1];
        }
        else if (theBoard[1][1] == theBoard[2][2] && theBoard[1][1] == theBoard[3][3] && theBoard[1][1] == theBoard[4][4] && theBoard[1][1] == oppSymbol && theBoard[0][0] == "") {
            row = 0; col = 0;
            return box5x5Id[0][0];
        }

        if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == oppSymbol && theBoard[4][0] == "") {
            row = 4; col = 0;
            return box5x5Id[4][0];
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == oppSymbol && theBoard[3][1] == "") {
            row = 3; col = 1;
            return box5x5Id[3][1];
        }
        else if (theBoard[0][4] == theBoard[1][3] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == oppSymbol && theBoard[2][2] == "") {
            row = 2; col = 2;
            return box5x5Id[2][2];
        }
        else if (theBoard[0][4] == theBoard[2][2] && theBoard[0][4] == theBoard[3][1] && theBoard[0][4] == theBoard[4][0] && theBoard[0][4] == oppSymbol && theBoard[1][3] == "") {
            row = 1; col = 3;
            return box5x5Id[1][3];
        }
        else if (theBoard[1][3] == theBoard[2][2] && theBoard[1][3] == theBoard[3][1] && theBoard[1][3] == theBoard[4][0] && theBoard[1][3] == oppSymbol && theBoard[0][4] == "") {
            row = 0; col = 4;
            return box5x5Id[0][4];
        }

        for (int k = 0; k < 5; k++) {
            // check the columns
            if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[0][k] == oppSymbol && theBoard[4][k] == "") {
                row = 4; col = k;
                return box5x5Id[4][k];
            }
            else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[4][k] && theBoard[0][k] == oppSymbol && theBoard[3][k] == "") {
                row = 3; col = k;
                return box5x5Id[3][k];
            }
            else if (theBoard[0][k] == theBoard[1][k] && theBoard[1][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == oppSymbol && theBoard[2][k] == "") {
                row = 2; col = k;
                return box5x5Id[2][k];
            }
            else if (theBoard[0][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[0][k] == oppSymbol && theBoard[1][k] == "") {
                row = 1; col = k;
                return box5x5Id[1][k];
            }
            else if (theBoard[1][k] == theBoard[2][k] && theBoard[2][k] == theBoard[3][k] && theBoard[3][k] == theBoard[4][k] && theBoard[1][k] == oppSymbol && theBoard[0][k] == "") {
                row = 0; col = k;
                return box5x5Id[0][k];
            }


            // check the rows
            if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][0] == oppSymbol && theBoard[k][4] == "") {
                row = k; col = 4;
                return box5x5Id[k][4];
            }
            else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][4] && theBoard[k][0] == oppSymbol && theBoard[k][3] == "") {
                row = k; col = 3;
                return box5x5Id[k][3];
            }
            else if (theBoard[k][0] == theBoard[k][1] && theBoard[k][1] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == oppSymbol && theBoard[k][2] == "") {
                row = k; col = 2;
                return box5x5Id[k][2];
            }
            else if (theBoard[k][0] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][0] == oppSymbol && theBoard[k][1] == "") {
                row = k; col = 1;
                return box5x5Id[k][1];
            }
            else if (theBoard[k][1] == theBoard[k][2] && theBoard[k][2] == theBoard[k][3] && theBoard[k][3] == theBoard[k][4] && theBoard[k][1] == oppSymbol && theBoard[k][0] == "") {
                row = k; col = 0;
                return box5x5Id[k][0];
            }
            else{
                board5x5Defend();
            }

        }

        return -1;
    }
*/

}
