package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import Models.Player;
import dao.DbAdapter;

public class Leaderboard extends AppCompatActivity {

    private DbAdapter dbAdapter;
    private Toolbar theToolBar;
    private ListView winnerNameList;
    private ArrayAdapter<String> monAdapter;
    private ArrayList<String> allWinners;
    private ArrayList<Integer> wins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.leaderboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setWidget();
        setSupportActionBar(theToolBar);
        setListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.startGameText) {
            Intent intent = new Intent(Leaderboard.this, PlayerSetup.class);
            startActivity(intent);
        }
        if (id == R.id.editText) {
            Toast.makeText(Leaderboard.this, "Nice to have...", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {

        theToolBar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        theToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Leaderboard.this, MainActivity.class);
                startActivity(intent);
            }
        });

        winnerNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Leaderboard.this, allWinners.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setWidget() {
        theToolBar = findViewById(R.id.toolbar);
        winnerNameList = findViewById(R.id.LeaderBoardList);
        dbAdapter = new DbAdapter(Leaderboard.this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("PLAYERS")) {
            allWinners = intent.getStringArrayListExtra("PLAYERS");
        }

        if (intent != null && intent.hasExtra("WINS")) {
            wins = intent.getIntegerArrayListExtra("WINS");
        }

        if (allWinners != null && wins != null) {
            List<String> combinedList = new ArrayList<>();
            for (int i = 0; i < allWinners.size(); i++) {
                combinedList.add(allWinners.get(i) + " Wins: " + wins.get(i));
            }
            monAdapter = new ArrayAdapter<>(Leaderboard.this, android.R.layout.simple_list_item_1, combinedList);
            winnerNameList.setAdapter(monAdapter);
        }

    }

}