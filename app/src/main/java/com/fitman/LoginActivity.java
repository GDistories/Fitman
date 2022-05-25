package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.fitman.utils.SharedPreferencesUtils;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO
        SharedPreferencesUtils.setParam("isLogin","true");
        SharedPreferencesUtils.setParam("username","gao");
        startActivity(new Intent(this, NavigationBottomActivity.class));
        finish();
    }
}