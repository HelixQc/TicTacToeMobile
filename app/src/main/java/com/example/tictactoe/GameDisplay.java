package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import Models.Player;
import dao.DbAdapter;

public class GameDisplay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard;
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurns;
    private String[] playersNames;
    DbAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setWidget();
        dbAdapter = new DbAdapter(GameDisplay.this);
        ticTacToeBoard.setUpGame(playAgainBtn, homeBtn, playerTurns, playersNames);
        if (playersNames != null) {
            playerTurns.setText(playersNames[0]+ " " +getString(R.string.turn));
        }

        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);

    }

    public void playAgainBtnClick(View view) {
        String winner = playerTurns.getText().toString().replace(" "+getString(R.string.won), "");
        updateWinningPlayer(winner);
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeBtnClick(View view) {
        String winner = playerTurns.getText().toString().replace(getString(R.string.won), "");
        updateWinningPlayer(winner);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void updateWinningPlayer(String playerName) {

        if (dbAdapter.checkLeaderboard()) {
            dbAdapter.addWinningPlayer(playerName);
        } else {
            boolean playerFound = false;
            // Check if the player is in the database
            for (Player p : dbAdapter.getAllWinningPlayer()) {
                if (p.getPlayerName().equalsIgnoreCase(playerName) && playerName.contains(getString(R.string.tied_game))) {
                    dbAdapter.addWin(playerName);
                    playerFound = true;
                    break; // Once player is found, no need to continue loop
                }
            }
            if (!playerFound) {
                dbAdapter.addWinningPlayer(playerName);
            }
        }
    }

    private void setWidget() {
        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        homeBtn = findViewById(R.id.homeBtn);
        playerTurns = findViewById(R.id.playerNameLabel);
        playersNames = getIntent().getStringArrayExtra("PLAYER_NAMES");
    }
}

