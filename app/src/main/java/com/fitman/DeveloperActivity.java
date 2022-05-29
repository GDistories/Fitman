package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fitman.database.Attendance.AttendanceDao;
import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;

public class DeveloperActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        showActionBar();
    }

    public void clearAll(View view) {
        UserDao userDao = new UserDao(this);
        userDao.deleteUserTable(this);

        AttendanceDao attendanceDao = new AttendanceDao(this);
        attendanceDao.deleteAttendanceTable(this);

        SharedPreferencesUtils.clear();
        restartApp(100);
    }

    public void clearPreferences(View view) {
        SharedPreferencesUtils.clear();
        restartApp(100);
    }

    public void clearDatabase(View view) {
        UserDao userDao = new UserDao(this);
        userDao.deleteUserTable(this);

        AttendanceDao attendanceDao = new AttendanceDao(this);
        attendanceDao.deleteAttendanceTable(this);

        restartApp(100);
    }
}