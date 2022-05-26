package com.fitman;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.fitman.utils.SharedPreferencesUtils;

import java.util.List;
import java.util.Locale;

public class LanguageChangeActivity extends BaseActivity {
    private static final String TAG = "LanguageChangeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change);


        //TODO
    }

    public void change1(View view) {
        String language = "zh_CN";
        SharedPreferencesUtils.setParam("language", language);
        startActivity(new Intent(this, LanguageChangeActivity.class));
        finish();
    }

    public void change2(View view) {
        String language = "zh_TW";
        SharedPreferencesUtils.setParam("language", language);
        startActivity(new Intent(this, LanguageChangeActivity.class));
        finish();
    }

    public void change3(View view) {
        String language = "en_US";
        SharedPreferencesUtils.setParam("language", language);
        startActivity(new Intent(this, LanguageChangeActivity.class));
        finish();
    }

    public void clear(View view) {
        SharedPreferencesUtils.clear();
        startActivity(new Intent(this, LanguageChangeActivity.class));
        finish();
    }

    public void setting(View view) {
        startActivity(new Intent(this, AboutActivity.class));
        finish();
    }



}
