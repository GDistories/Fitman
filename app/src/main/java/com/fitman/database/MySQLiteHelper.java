package com.fitman.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "Fitman.db";

    public MySQLiteHelper(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT, " +
                "username text NOT NULL, password text NOT NULL, firstName text NOT NULL, " +
                "lastName text NOT NULL, phoneNum text NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
