package com.fitman.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private SQLiteDatabase sqLiteDatabase;

    public UserDao(Context context){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }

    public boolean insertUser(String username, String password){
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",password);

        long id = sqLiteDatabase.insert("users",null,values);
        return id > 0;
    }

    public boolean deleteUser(String username){
        long id = sqLiteDatabase.delete("users","username=?", new String[]{username});
        return id > 0;
    }

    public UserBean querryUser(String username, String password){
        Cursor cursor = sqLiteDatabase.query("users", new String[]{username,password},"username=? AND password=?", new String[]{username, password},null,null,null);

        UserBean userBean = new UserBean();
        while (cursor.moveToNext()){
            userBean.setUsername(cursor.getString(0));
            userBean.setPassword(cursor.getString(1));
        }
        return userBean;
    }

    public boolean updateUser(String username, String newPassword){
        ContentValues values = new ContentValues();
        values.put("password",newPassword);

        long id = sqLiteDatabase.update("users",values,"username=?",new String[]{username});
        return id > 0;
    }


}
