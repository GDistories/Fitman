package com.fitman.database.Attendance;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fitman.database.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    private SQLiteDatabase sqLiteDatabase;

    public AttendanceDao(Context context){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }

    public void deleteAttendanceTable(Context context){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(context);
        mySQLiteHelper.dropAttendanceTable(sqLiteDatabase);
    }

    public int insertAttendance(String username, String attendanceDate, String attendanceMonth){
        if(isAttendanceExist(username, attendanceDate)){
            //出勤已位于数据库中
            return -2;
        }
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("attendanceDate",attendanceDate);
        values.put("attendanceMonth", attendanceMonth);

        long id = sqLiteDatabase.insert("attendance",null,values);
        if(id>0){
            //插入成功
            return 1;
        }
        //插入失败
        else return -1;
    }

    public boolean isAttendanceExist(String username, String attendanceDate){
        Cursor cursor = sqLiteDatabase.query("attendance", null,"username=? and attendanceDate=?", new String[]{username, attendanceDate},null,null,null);
        return cursor.moveToNext();
    }

    public boolean deleteAttendance(String username, String attendanceDate){
        long id = sqLiteDatabase.delete("attendance","username=? and attendanceDate=?", new String[]{username, attendanceDate});
        return id > 0;
    }

    public List<String> getAttendance(String username){
        Cursor cursor = sqLiteDatabase.query("attendance", new String[]{"attendanceDate"},"username=? ", new String[]{username},null,null,null);
        List<String> attendanceList = new ArrayList<>();
        while (cursor.moveToNext()){
            String attendanceDate = cursor.getString(0);
            attendanceList.add(attendanceDate);
        }
        return attendanceList;
    }

    public int getMonthAttendanceCount(String username, String attendanceMonth){
        Cursor cursor = sqLiteDatabase.query("attendance", new String[]{"attendanceDate"},"username=? and attendanceMonth = ?", new String[]{username, attendanceMonth},null,null,null);
        return cursor.getCount();
    }
}
