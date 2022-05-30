package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.Attendance.AttendanceDao;
import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;
import com.fitman.views.CustomDate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDataActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        showActionBar();
        showBackButton();
        setActionBarTitle(getString(R.string.my_data));

        TextView tv_height_num = findViewById(R.id.tv_height_num);
        TextView tv_weight_num = findViewById(R.id.tv_weight_num);
        TextView tv_bmi_num = findViewById(R.id.tv_bmi_num);
        TextView tv_birthday_num = findViewById(R.id.tv_birthday_num);
        TextView tv_attendance_num = findViewById(R.id.tv_attendance_num);
        TextView tv_step_num = findViewById(R.id.tv_step_num);
        UserDao userDao = new UserDao(this);
        AttendanceDao attendanceDao = new AttendanceDao(this);
        CustomDate customDate = new CustomDate();

        if(userDao.getHeight(getUsername()) == null || userDao.getWeight(getUsername()) == null || userDao.getBirthday(getUsername()) == null){
            Toast.makeText(this, getString(R.string.fill_in_profile_first), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, UserProfileActivity.class));
            finish();
            return;
        }
        Double height = Double.parseDouble(userDao.getHeight(getUsername())) / 100;
        Double weight = Double.parseDouble(userDao.getWeight(getUsername()));
        Double bmi = weight / (height * height);

        tv_height_num.setText(userDao.getHeight(getUsername()) + " " + getString(R.string.cm));
        tv_weight_num.setText(userDao.getWeight(getUsername()) + " " + getString(R.string.kg));
        tv_bmi_num.setText(String.format("%.2f", bmi));
        Date birthday = userDao.getBirthday(getUsername());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        tv_birthday_num.setText(dateFormat.format(birthday));
        Integer attendance = attendanceDao.getMonthAttendanceCount(getUsername(), customDate.getYearMonth());
        tv_attendance_num.setText(attendance.toString());
        tv_step_num.setText(SharedPreferencesUtils.getParam("step", "0"));



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}