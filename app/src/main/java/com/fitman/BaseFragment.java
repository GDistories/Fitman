package com.fitman;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.fitman.utils.SharedPreferencesUtils;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public Boolean isLogin(){
        return SharedPreferencesUtils.getParam("isLogin","false").equals("true");
    }
}

