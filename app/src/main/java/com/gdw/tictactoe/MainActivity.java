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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton board3x3button, board5x5button, single, multiple, xSymbol, oSymbol;
    public static final String DEBUGTAG = "GDW";
    private String opponent="";
    private String board = "";
    private String symbol = "";
    private String[] options;
    public static final String BOARD_OPTIONS = "boardOptions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * start a new game, load the selected board
     * @param view
     */

    public void startGame(View view){
        board3x3button = (RadioButton) findViewById(R.id.board3x3);
        board5x5button = (RadioButton) findViewById(R.id.board5x5);
        single = (RadioButton) findViewById(R.id.single);
        multiple = (RadioButton) findViewById(R.id.multiple);
        xSymbol = (RadioButton) findViewById(R.id.symbolX);
        oSymbol = (RadioButton) findViewById(R.id.symbolO);

        if( (board3x3button.isChecked() || board5x5button.isChecked()) && (single.isChecked() || multiple.isChecked()) && (xSymbol.isChecked() || oSymbol.isChecked()) ) {
            //start board activity and pass board type and opponent data
            options = new String[]{opponent, board, symbol};
            Intent i = new Intent(this, Board.class);
            i.putExtra(BOARD_OPTIONS, options);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please select all available options", Toast.LENGTH_SHORT).show();
        }
    }

    public void opponentSelect(View view) {
        // Is the button now checked?
        //Log.e(DEBUGTAG, "activity");
        boolean checked = ((RadioButton) view).isChecked();

        // Check which opponent button was clicked
        switch(view.getId()) {
            case R.id.single:
                if (checked)
                    //computer opponent selected
                    opponent = "single";
                    break;
            case R.id.multiple:
                if (checked)
                    //human opponent selected
                    opponent = "multiple";
                    break;
        }
    }

    public void boardSelect(View view) {
        // Is the button now checked?
        //Log.e(DEBUGTAG, "activity");
        boolean checked = ((RadioButton) view).isChecked();

        // Check which board button was clicked
        switch(view.getId()) {
            case R.id.board3x3:
                if (checked)
                    // 3 x 3 board checked
                    board = "3x3";
                break;
            case R.id.board5x5:
                if (checked)
                    // 5 x 5 board checked
                    board = "5x5";
                break;
        }
    }

    public void symbolSelect(View view) {
        // select the symbol X or O
        //Log.e(DEBUGTAG, "activity");
        boolean checked = ((RadioButton) view).isChecked();

        // Check which board button was clicked
        switch(view.getId()) {
            case R.id.symbolX:
                if (checked)
                    // 3 x 3 board checked
                    symbol = "X";
                break;
            case R.id.symbolO:
                if (checked)
                    // 5 x 5 board checked
                    symbol = "O";
                break;
        }
    }

}
