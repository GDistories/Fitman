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
                        "firstName text NOT NULL, " +
                        "lastName text NOT NULL, " +
                        "phoneNum text NOT NULL, " +
                        "gender text NOT NULL, " +
                        "height text NOT NULL, " +
                        "weight text NOT NULL, " +
                        "birthday text NOT NULL, " +
                        "email text NOT NULL)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE steps(" +
                        "stepId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "stepNum integer NOT NULL, " +
                        "stepDate text NOT NULL)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE weights(" +
                        "weightId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "weightNum integer NOT NULL, " +
                        "weightDate text NOT NULL)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE attendance(" +
                        "attendanceId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "attendanceDate text NOT NULL, " +
                        "attendanceMonth text NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS steps");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS weights");
        onCreate(sqLiteDatabase);
    }

    public void dropUserTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS steps");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS weights");
        sqLiteDatabase.execSQL(
                "CREATE TABLE users(" +
                        "userId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "password text NOT NULL, " +
                        "firstName text NOT NULL, " +
                        "lastName text NOT NULL, " +
                        "phoneNum text NOT NULL, " +
                        "gender text NOT NULL, " +
                        "height text NOT NULL, " +
                        "weight text NOT NULL, " +
                        "birthday text NOT NULL, " +
                        "email text NOT NULL)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE steps(" +
                        "stepId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "stepNum integer NOT NULL, " +
                        "stepDate text NOT NULL)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE weights(" +
                        "weightId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "weightNum integer NOT NULL, " +
                        "weightDate text NOT NULL)"
        );
    }

    public void dropAttendanceTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS attendance");
        sqLiteDatabase.execSQL(
                "CREATE TABLE attendance(" +
                        "attendanceId integer PRIMARY KEY AUTOINCREMENT, " +
                        "username text NOT NULL, " +
                        "attendanceDate text NOT NULL, " +
                        "attendanceMonth text NOT NULL)"
        );
    }
}
