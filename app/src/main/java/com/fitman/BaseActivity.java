package com.fitman;

import static com.fitman.utils.Util.getSysCountry;
import static com.fitman.utils.Util.getSysLang;

import androidx.annotation.Nullable;
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
        Log.d(TAG, "onCreate: " + this.getClass().getSimpleName());
        getSupportActionBar().hide();
        hideStatusAndActionBar();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferencesUtils.init(newBase);
        String lang_code = SharedPreferencesUtils.getParam("language", getSysLang(newBase)).toString();
        String country_code = SharedPreferencesUtils.getParam("country", getSysCountry(newBase)).toString();
        Context context = Util.changeLang(newBase, lang_code, country_code);
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

    public void showStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }






}