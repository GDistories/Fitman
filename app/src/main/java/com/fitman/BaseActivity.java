package com.fitman;

import static com.fitman.utils.Util.getSysLang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.fitman.utils.SharedPreferencesUtils;
import com.fitman.utils.Util;

import java.util.ArrayList;
import java.util.List;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String language = getSysLang(this);
        if (language.equals("zh_CN_#Hans")) {
            language = "zh_CN";
        }else if(language.equals("zh_TW_#Hant")){
            language = "zh_TW";
        }
        SharedPreferencesUtils.setParam("language", language);
        Log.d(TAG, "onCreate: " + this.getClass().getSimpleName());
        getSupportActionBar().hide();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = SharedPreferencesUtils.getParam("language", getSysLang(newBase)).toString();
        Context context = Util.changeLang(newBase, lang);
        super.attachBaseContext(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + this.getClass().getSimpleName());
    }

    public void hideStatusAndActionBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Hide the action bar.
        getSupportActionBar().hide();
    }

    public void showActionBar() {
        getSupportActionBar().show();
    }

    public void showBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    public void hideBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowCustomEnabled(false);
        }
    }

    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public Boolean isLogin(){
        return SharedPreferencesUtils.getParam("isLogin","false").equals("true");
    }

    public String getUsername(){
        return SharedPreferencesUtils.getParam("username","");
    }

    public boolean hasPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission)== PackageManager.PERMISSION_GRANTED;
    }

    public void restartApp(Integer delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(getApplication().getPackageName());
                LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(LaunchIntent);
            }
        }, delay);
    }


    public List<String> getPermission(){
        List<String> permission = new ArrayList<String>();
//            <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
//    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
//    <uses-permission android:name="android.permission.INTERNET" />
        if (!hasPermission(this, "android.permission.ACTIVITY_RECOGNITION")) {
            permission.add("android.permission.ACTIVITY_RECOGNITION");
        }

        if (!hasPermission(this, "android.permission.INTERNET")) {
            permission.add("android.permission.INTERNET");
        }

        if (!hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) {
            permission.add("android.permission.READ_EXTERNAL_STORAGE");
        }

        if (!hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            permission.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        return permission;
    }

    public Boolean hasAllPermission(){
        List<String> permission = getPermission();
        for (String p : permission) {
            if (!hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }



}