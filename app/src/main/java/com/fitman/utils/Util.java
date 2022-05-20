package com.fitman.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import java.util.Locale;

public class Util {
    private static final String TAG = "Util";
    public static ContextWrapper changeLang(Context context, String lang) {

        String[] langs = lang.split("_");
        String lang_code = langs[0];
        String country_code = langs[1];
        Locale sysLocale;
        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();
        sysLocale = config.getLocales().get(0);
        if (!lang_code.equals("") && !sysLocale.getCountry().equals(country_code)) {
            Locale locale = new Locale(lang_code, country_code);
            Locale.setDefault(locale);
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        }

        return new ContextWrapper(context);
    }

    public static String getSysLang(Context context){
        Locale sysLocale;
        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();
        sysLocale = config.getLocales().get(0);
        return sysLocale.toString();
    }

    public static String getSysCountry(Context context){
        Locale sysLocale;
        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();
        sysLocale = config.getLocales().get(0);
        return sysLocale.getCountry().toString();

    }
}
