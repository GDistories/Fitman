package com.fitman;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.fitman.utils.SharedPreferencesUtils;

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: "+getClass().getSimpleName() );
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
}

