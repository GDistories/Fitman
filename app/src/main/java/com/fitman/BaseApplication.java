package com.fitman;

import android.util.Log;

import com.fitman.utils.SharedPreferencesUtils;


public class BaseApplication extends android.app.Application {
    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtils.init(this);
//        SharedPreferencesUtils.setParam("isFirstOpen", "true");
    }
}
