package com.fitman.utils;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesUtils {


    private static SharedPreferences sharedPreferences;
    //private static SharedPreferences.OnSharedPreferenceChangeListener listener;

    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("com.fitman_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public static void setParam(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static String getParam(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    public static void clear(){
    	editor.clear();
    	editor.apply();
    }

}
