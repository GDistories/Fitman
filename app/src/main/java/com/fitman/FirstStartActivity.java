package com.fitman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fitman.utils.SharedPreferencesUtils;

public class FirstStartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);
        SharedPreferencesUtils.setParam("isFirstStart", "false");
    }

    public void start(View view) {
        if(!hasAllPermission())
        {
            Toast.makeText(this, getString(R.string.permission_not_all), Toast.LENGTH_SHORT).show();
            restartApp(2000);
            return;
        }
        startActivity(new Intent(this, NavigationBottomActivity.class));
        finish();
    }
}