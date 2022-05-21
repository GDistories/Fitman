package com.fitman.database.Step;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fitman.database.MySQLiteHelper;

public class StepDao {
    private SQLiteDatabase sqLiteDatabase;

    public StepDao(Context context){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }

    public int insertStep(String username){
        ContentValues values = new ContentValues();
        values.put("userId", 2);
        values.put("stepNum", 50);
        values.put("stepDate", username);

        long id = sqLiteDatabase.insert("steps",null,values);
        if(id>0){
            return 1;
        }else return -1;
    }
}
