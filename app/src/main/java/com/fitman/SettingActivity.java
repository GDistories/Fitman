package com.fitman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SettingActivity extends BaseActivity {
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        showActionBar();
        setActionBarTitle("Setting");
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        Context context = this;
        SharedPreferences prefs = this.getSharedPreferences("com.fitman_preferences",0);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if(s.equals("language")){
//                    startActivity(new Intent(context, NavigationBottomActivity.class));
//                    finish();

                    // 1秒钟后重启应用
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(getApplication().getPackageName());
                            LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(LaunchIntent);
                        }
                    }, 100);
                }

            }
        };
        prefs.registerOnSharedPreferenceChangeListener(listener);

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

}