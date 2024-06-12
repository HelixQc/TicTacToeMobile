package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

        public static final int  VERSION = 1;
        public static final String BD_NAME = "LeaderBoard";
        public static final String TABLE_PLAYER = "Players";
        public static final  String COL_ID = "id";
        public static final String COL_NAME = "PlayerName";
        public static final String COL_WINS = "Wins";
        public static final String PERSONNE_DDL = "create table "+TABLE_PLAYER +"("+ COL_ID + " INTEGER primary key autoincrement,"
                + COL_NAME + " TEXT, "+ COL_WINS + " INTEGER "+")";

        public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(PERSONNE_DDL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Detecté la version actuel pour faire un alter table ***Update***
        }
    }