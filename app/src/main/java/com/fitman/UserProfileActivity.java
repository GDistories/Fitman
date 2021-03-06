package com.fitman;

import androidx.annotation.IdRes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fitman.database.User.UserBean;
import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;

import java.util.Calendar;
import java.util.Date;

public class UserProfileActivity extends BaseActivity {

    NumberPicker np_height;
    NumberPicker np_weight;
    NumberPicker np_year;
    NumberPicker np_month;
    NumberPicker np_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        showActionBar();
        setActionBarTitle(getString(R.string.profile));
        showBackButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        UserDao userDao = new UserDao(this);
        Button btn_logout = findViewById(R.id.btn_logout);
        Button btn_cancel = findViewById(R.id.btn_cancel);

        if(!SharedPreferencesUtils.getParam("isLogin","").equals("false")) {
            //未登录
            if (SharedPreferencesUtils.getParam("isRegistered", "false").equals("true")) {
                //正在注册
                btn_logout.setVisibility(View.INVISIBLE);
                btn_cancel.setVisibility(View.INVISIBLE);
            } else {
                //未在注册
                btn_logout.setVisibility(View.VISIBLE);
                btn_cancel.setVisibility(View.VISIBLE);
            }
        }
        else{
            //未登录
            btn_logout.setVisibility(View.INVISIBLE);
        }

        np_height = findViewById(R.id.np_height);
        np_weight = findViewById(R.id.np_weight);
        np_year = findViewById(R.id.np_year);
        np_month = findViewById(R.id.np_month);
        np_day = findViewById(R.id.np_day);
        RadioGroup rg_gender = findViewById(R.id.rg_sex);

        EditText firstName = findViewById(R.id.et_firstName);
        EditText lastName = findViewById(R.id.et_lastName);
        EditText email = findViewById(R.id.et_email);
        EditText phone = findViewById(R.id.et_phone);
        RadioButton rb_male = findViewById(R.id.rb_male);
        RadioButton rb_female = findViewById(R.id.rb_female);

        //从数据库读取数据
        if(SharedPreferencesUtils.getParam("isLogin","").equals("true"))
        {
            UserBean userBean = userDao.queryUserByUsername(getUsername());
            if(!userBean.getFirstName().isEmpty()){
                firstName.setText(userBean.getFirstName());
            }
            if(!userBean.getLastName().isEmpty()){
                lastName.setText(userBean.getLastName());
            }
            if(!userBean.getEmail().isEmpty()){
                email.setText(userBean.getEmail());
            }
            if(!userBean.getPhone().isEmpty()){
                phone.setText(userBean.getPhone());
            }
            if(!userBean.getGender().isEmpty()) {
                if (userBean.getGender().equals("Male")) {
                    rb_male.setChecked(true);
                } else if (userBean.getGender().equals("Female")) {
                    rb_female.setChecked(true);
                }
            }
            if (!userBean.getHeight().isEmpty()){
                np_height.setValue(Integer.parseInt(userBean.getHeight()));
            }
            if (!userBean.getWeight().isEmpty()){
                np_weight.setValue(Integer.parseInt(userBean.getWeight()));
            }
        }


        np_height.setMinValue(0);
        np_height.setMaxValue(300);
        String height = userDao.getHeight(getUsername());
        if (height != null && !height.isEmpty() && SharedPreferencesUtils.getParam("isLogin","").equals("true")) {
            np_height.setValue(Integer.parseInt(height));
        }else {
            np_height.setValue(170);
        }
        np_height.setWrapSelectorWheel(false);
        np_height.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SharedPreferencesUtils.setParam("height", Integer.toString(newVal));
            }
        });

        np_weight.setMinValue(0);
        np_weight.setMaxValue(300);
        String weight = userDao.getWeight(getUsername());
        if (weight != null && !weight.isEmpty() && SharedPreferencesUtils.getParam("isLogin","").equals("true")) {
            np_weight.setValue(Integer.parseInt(weight));
        }else {
            np_weight.setValue(60);
        }
        np_weight.setWrapSelectorWheel(false);
        np_weight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SharedPreferencesUtils.setParam("weight", Integer.toString(newVal));
            }
        });

        Calendar today = Calendar.getInstance();
        np_year.setMinValue(1900);
        np_year.setMaxValue(today.get(Calendar.YEAR));
        np_year.setWrapSelectorWheel(false);
        np_month.setMinValue(1);
        np_month.setMaxValue(12);
        np_month.setWrapSelectorWheel(false);

        if (userDao.getBirthday(getUsername()) != null && SharedPreferencesUtils.getParam("isLogin","").equals("true")){
            Date birth = userDao.getBirthday(getUsername());
            Calendar birthday = Calendar.getInstance();
            birthday.setTime(birth);
            np_year.setValue(birthday.get(Calendar.YEAR));
            np_month.setValue(birthday.get(Calendar.MONTH) + 1);
            fixDay(np_month.getValue(), np_day);
            np_day.setValue(birthday.get(Calendar.DAY_OF_MONTH));
        }else {
            np_year.setValue(today.get(Calendar.YEAR));
            np_month.setValue(today.get(Calendar.MONTH) + 1);
            fixDay(np_month.getValue(), np_day);
            np_day.setValue(today.get(Calendar.DAY_OF_MONTH));
        }
        np_year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    SharedPreferencesUtils.setParam("birthday", np_year.getValue() + "-" + np_month.getValue() + "-" + np_day.getValue());
                }
            }
        );
        np_month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                fixDay(newVal, np_day);
                SharedPreferencesUtils.setParam("birthday", np_year.getValue() + "-" + np_month.getValue() + "-" + np_day.getValue());
            }
        });
        np_day.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SharedPreferencesUtils.setParam("birthday", np_year.getValue() + "-" + np_month.getValue() + "-" + np_day.getValue());
            }
        });

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                Log.e("TAG", Integer.toString(i));
            }
        });

    }

    public void fixDay(int np_month, NumberPicker np_day){
        if (np_month == 2){
            np_day.setMaxValue(28);
        }else if (np_month == 4 || np_month == 6 || np_month == 9 || np_month == 11){
            np_day.setMaxValue(30);
        }else {
            np_day.setMaxValue(31);
        }
        np_day.setWrapSelectorWheel(false);
    }

    public void submit(View view) {
        String username = getUsername();
        UserDao userDao = new UserDao(this);
        EditText firstName = findViewById(R.id.et_firstName);
        EditText lastName = findViewById(R.id.et_lastName);
        EditText email = findViewById(R.id.et_email);
        EditText phone = findViewById(R.id.et_phone);
        RadioGroup rg_gender = findViewById(R.id.rg_sex);
        RadioButton rb_gender = findViewById(rg_gender.getCheckedRadioButtonId());

        if (username.isEmpty())
        {
            Toast.makeText(this, getString(R.string.username_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (firstName.getText().toString().isEmpty())
        {
            Toast.makeText(this, getString(R.string.firstname_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastName.getText().toString().isEmpty())
        {
            Toast.makeText(this, getString(R.string.lastname_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.getText().toString().isEmpty())
        {
            Toast.makeText(this, getString(R.string.phone_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.getText().toString().isEmpty())
        {
            Toast.makeText(this, getString(R.string.email_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (rb_gender == null)
        {
            Toast.makeText(this, getString(R.string.gender_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        userDao.updateFirstName(username, firstName.getText().toString());
        userDao.updateLastName(username, lastName.getText().toString());
        userDao.updateEmail(username, email.getText().toString());
        userDao.updatePhone(username, phone.getText().toString());
        userDao.updateGender(username, rb_gender.getText().toString());

        int a = np_height.getValue();
        int b = np_weight.getValue();
        String defaultHeight = Integer.toString(a);
        String defaultWeight = Integer.toString(b);

        userDao.updateHeight(username, SharedPreferencesUtils.getParam("height", defaultHeight));
        userDao.updateWeight(username, SharedPreferencesUtils.getParam("weight", defaultWeight));
        String defaultBirthday = np_year.getValue() + "-" + np_month.getValue() + "-" + np_day.getValue();

        userDao.updateBirthdayByString(username, SharedPreferencesUtils.getParam("birthday", defaultBirthday));
        Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
        if(SharedPreferencesUtils.getParam("isRegistered", "false").equals("true")){
            SharedPreferencesUtils.setParam("isRegistered", "false");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    public void logout(View view) {
        SharedPreferencesUtils.setParam("isLogin", "false");
        finish();
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