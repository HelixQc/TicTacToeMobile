package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerSetup extends AppCompatActivity {

    private EditText player1;
    private EditText player2;
    private Toolbar theToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.player_setup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setWidget();
        setSupportActionBar(theToolBar);
        setListeners();
    }

    private void setWidget() {
        player1 = findViewById(R.id.player1Name);
        player2 = findViewById(R.id.player2Name);
        theToolBar = findViewById(R.id.toolbar2);
    }

    public void submitButtonClick(View view){
        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();

        if(player1Name.equalsIgnoreCase("")){
            player1Name = "Player1";
        }

        if(player2Name.equalsIgnoreCase("")){
            player2Name = "Player2";
        }

        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("PLAYER_NAMES", new String[] {player1Name, player2Name});
        startActivity(intent);
    }

    private void setListeners() {

        theToolBar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        theToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerSetup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_playersetup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.editText){
            Toast.makeText(PlayerSetup.this, "Nice to have...", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}