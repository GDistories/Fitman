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
        sqLiteDatabase.execSQL(
                "CREATE TABLE users(" +
                        "userId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "password text NOT NULL, " +
                        "firstName text , " +
                        "lastName text , " +
                        "phoneNum text , " +
                        "gender text, " +
                        "height text, " +
                        "weight text, " +
                        "birthday text, " +
                        "email text)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE steps(" +
                        "stepId integer PRIMARY KEY AUTOINCREMENT, " +
                        "userId integer, " +
                        "stepNum integer NOT NULL, " +
                        "stepDate text NOT NULL)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE weights(" +
                        "weightId integer PRIMARY KEY AUTOINCREMENT, " +
                        "userId integer, " +
                        "weightNum integer NOT NULL, " +
                        "weightDate text NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
