package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import Models.Player;
import dao.DbAdapter;

public class MainActivity extends AppCompatActivity {


    private DbAdapter dbAdapter;
    private Button leaderBoardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbAdapter = new DbAdapter(MainActivity.this);
        setWidget();
        setBtnView();
    }

    public void playButtonClick(View view){
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent);
    }

    public void leaderBoardButtonClick(View view){

        ArrayList<Player> players = dbAdapter.getAllWinningPlayer();
        ArrayList<String> winners = new ArrayList<>();
        ArrayList<Integer> wins = new ArrayList<>();


       for(Player p : players){
            String winner = p.getPlayerName();
            winners.add(winner);
            wins.add(p.getNbWins());
        }

        Intent intent = new Intent(this, Leaderboard.class);
        intent.putExtra("PLAYERS" , winners);
        intent.putExtra("WINS" , wins);
        startActivity(intent);
    }

    public void setWidget(){
        leaderBoardBtn = findViewById(R.id.leaderBoardBtn);
    }

    public void setBtnView(){
        if(dbAdapter.getAllWinningPlayer().isEmpty()){
            leaderBoardBtn.setVisibility(View.GONE);
        }else{
            leaderBoardBtn.setVisibility(View.VISIBLE);
        }

    }
}