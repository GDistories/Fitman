package com.fitman.utils;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesUtils {

    //1、通过上下文对象获得共享参数的对象
    private static SharedPreferences sharedPreferences;
    //private static SharedPreferences.OnSharedPreferenceChangeListener listener;
    //2、获得共享参数的编辑对象
    private static SharedPreferences.Editor editor;

    /**
     * 初始化操作 一般在自定义的application中执行
     */
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
