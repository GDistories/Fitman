package com.fitman;

import android.util.Log;

import com.fitman.utils.SharedPreferencesUtils;


public class BaseApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("BaseApplication", "onCreate");
    }
}
