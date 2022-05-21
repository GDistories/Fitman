package com.fitman.database.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fitman.database.MySQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDao {
    private SQLiteDatabase sqLiteDatabase;

    public UserDao(Context context){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }

    public int insertUser(String username, String password){
        if(usernameInDatabase(username)){
            //用户已位于数据库中
            return -2;
        }
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",password);

        long id = sqLiteDatabase.insert("users",null,values);
        if(id>0){
            return 1;
        }else return -1;
    }

    public boolean deleteUser(String username){
        long id = sqLiteDatabase.delete("users","username=?", new String[]{username});
        return id > 0;
    }

    public boolean usernameInDatabase(String username){
        Cursor cursor = sqLiteDatabase.query("users", null,"username=?", new String[]{username},null,null,null);
        return cursor.moveToNext();
    }

    public UserBean queryUserByUsernameAndPassword(String username, String password){
        Cursor cursor = sqLiteDatabase.query("users", null,"username=? AND password=?", new String[]{username, password},null,null,null);

        UserBean userBean = new UserBean();
        while (cursor.moveToNext()){
            userBean.setUserId(cursor.getInt(0));
            userBean.setUsername(cursor.getString(1));
            userBean.setPassword(cursor.getString(2));
            userBean.setFirstName(cursor.getString(3));
            userBean.setLastName(cursor.getString(4));
            userBean.setPhone(cursor.getString(5));
            userBean.setGender(cursor.getString(6));
            userBean.setHeight(cursor.getString(7));
            userBean.setWeight(cursor.getString(8));
            userBean.setBirthday(cursor.getString(9));
            userBean.setEmail(cursor.getString(10));
        }
        return userBean;
    }

    public boolean updateUsername(Integer userId, String newUsername){
        String userid = userId.toString();
        ContentValues values = new ContentValues();
        values.put("username", newUsername);
        long id = sqLiteDatabase.update("users", values, "userId=?", new String[]{userid});
        return id > 0;
    }

    public boolean updateUserPassword(String username, String newPassword){
        ContentValues values = new ContentValues();
        values.put("password",newPassword);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }

    public Integer getUserIdByUsername(String username){
        Cursor cursor = sqLiteDatabase.query("users", new String[]{"userId"},"username=?", new String[]{username},null,null,null);

        UserBean userBean = new UserBean();
        while (cursor.moveToNext()){
            userBean.setUserId(cursor.getInt(0));
        }
        return userBean.getUserId();
    }

    public boolean updateEmail(String username, String newEmail){
        ContentValues values = new ContentValues();
        values.put("email",newEmail);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updateFirstName(String username, String newFirstName){
        ContentValues values = new ContentValues();
        values.put("firstName",newFirstName);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updateLastName(String username, String newLastName){
        ContentValues values = new ContentValues();
        values.put("lastName",newLastName);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updatePhone(String username, String newPhone){
        ContentValues values = new ContentValues();
        values.put("phoneNum",newPhone);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updateGender(String username, String newGender){
        ContentValues values = new ContentValues();
        values.put("gender",newGender);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updateHeight(String username, String newHeight){
        ContentValues values = new ContentValues();
        values.put("height",newHeight);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updateWeight(String username, String newWeight){
        ContentValues values = new ContentValues();
        values.put("weight",newWeight);

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }
    public boolean updateBirthday(String username, Date newBirthday){
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        values.put("birthday", dateFormat.format(newBirthday));

        long id = sqLiteDatabase.update("users",values,"username=?", new String[]{username});
        return id > 0;
    }

}
