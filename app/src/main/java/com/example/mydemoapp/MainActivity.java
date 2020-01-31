package com.example.mydemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0=yellow ,1=red;
    int activeplayer = 0;
    boolean gameIsActive = true;

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedCounter] == 2 && gameIsActive) {
            gamestate[tappedCounter] = activeplayer;

            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.right);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activeplayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningPositions : winningPositions) {
                if (gamestate[winningPositions[0]] == gamestate[winningPositions[1]] &&
                        gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                        gamestate[winningPositions[0]] != 2) {

                    gameIsActive = false;
                    String winner = "cross";
                    if (gamestate[winningPositions[0]] == 0) {

                        winner = "right";
                    }


                    TextView winningMessage = findViewById(R.id.winnerMessage);
                    winningMessage.setText(winner + " has won!");

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }else {
                    boolean gameIsOver=true;
                    for (int counterState : gamestate){
                        if (counterState == 2)
                            gameIsOver=false;
                    }
                    if (gameIsOver){
                        TextView winningMessage = findViewById(R.id.winnerMessage);
                        winningMessage.setText("It's a draw");

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);



                    }

                }
            }
        }
    }

    public void playAgainButton(View view) {
        gameIsActive = true;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activeplayer = 0;
        for (int i = 0; i < gamestate.length; i++) {

            gamestate[i] = 2;
        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.grid_Layout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
