package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fitman.utils.SharedPreferencesUtils;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO
        SharedPreferencesUtils.setParam("isLogin","false");
    }
}