package com.fitman;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.fitman.utils.SharedPreferencesUtils;
import com.fitman.utils.Util;

import java.util.Locale;
import java.util.ResourceBundle;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + this.getClass().getSimpleName());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferencesUtils.init(newBase);
        String lang_code = SharedPreferencesUtils.getParam("language", "en").toString();
        String country_code = SharedPreferencesUtils.getParam("country", "US").toString();
        Context context = Util.changeLang(newBase, lang_code, country_code);
        super.attachBaseContext(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + this.getClass().getSimpleName());
    }




}