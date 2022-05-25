package com.fitman;

import static com.fitman.utils.Util.getSysLang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fitman.utils.SharedPreferencesUtils;
import com.fitman.utils.Util;


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





}