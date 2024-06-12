package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Models.Player;

public class DbAdapter {

    private SQLiteDatabase db;
    private DbHelper dbHelper;
    private Context context;

    public DbAdapter(Context context){
        this.context = context;
        dbHelper = new DbHelper(context,DbHelper.BD_NAME, null , DbHelper.VERSION );
    }

    public void openDb(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb(){
        db.close();
    }

    public void addWinningPlayer(String p) {
        openDb();
        int win = 1;
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.COL_NAME, p);
        cv.put(DbHelper.COL_WINS, win);
        db.insert(DbHelper.TABLE_PLAYER, null, cv);
        closeDb();
    }


    public ArrayList<Player> getAllWinningPlayer(){
        openDb();
        int id;
        String playerName;
        int win;
        ArrayList<Player> winnersPlayers = new ArrayList<>();

        String[] cols = { DbHelper.COL_ID , DbHelper.COL_NAME, DbHelper.COL_WINS};
        Cursor cursor = db.query(DbHelper.TABLE_PLAYER, cols, null, null, null,
                null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            id = cursor.getInt(0);
            playerName = cursor.getString(1);
            win = cursor.getInt(2);
            Player p = new Player(playerName, win);
            winnersPlayers.add(p);
            //Toast.makeText(context, id+"-"+playerName, Toast.LENGTH_LONG).show();
            cursor.moveToNext();
        }

        cursor.close();
        closeDb();
        return winnersPlayers;
    }

    public boolean checkLeaderboard(){
        if(getAllWinningPlayer().isEmpty()){
            return true;
        }
        return false;
    }

    public void addWin(String name){

        Player p = new Player();
        for(Player player : getAllWinningPlayer()){
            if(name.equalsIgnoreCase(player.getPlayerName())){
                p = player;
                break;
            }
        }

        openDb();
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.COL_WINS, p.getNbWins() + 1);
        db.update(DbHelper.TABLE_PLAYER, cv, DbHelper.COL_NAME + " = ?", new String[] {name});
        closeDb();
    }
}
