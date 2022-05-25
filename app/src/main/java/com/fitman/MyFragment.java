package com.fitman;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.User.UserBean;
import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;


public class MyFragment extends BaseFragment {
    private static final String TAG = "MyFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        ImageView im_profile = getView().findViewById(R.id.im_profile);
        TextView tv_username = getView().findViewById(R.id.tv_username);
        ImageView im_sex = getView().findViewById(R.id.im_sex);
        TextView tv_myData = getView().findViewById(R.id.tv_myData);
        TextView tv_profile = getView().findViewById(R.id.tv_profile);
        TextView tv_setting = getActivity().findViewById(R.id.txt_setting);
        TextView tv_update = getActivity().findViewById(R.id.txt_update);
        TextView tv_about = getActivity().findViewById(R.id.txt_about);

        UserDao userDao = new UserDao(getActivity());

        if (isLogin()){
            //已登录
            Log.e(TAG, "Login");
            im_profile.setImageResource(R.drawable.defaultprofilephoto);
            tv_username.setText(userDao.getLastName(getUsername())+" "+userDao.getFirstName(getUsername()));
            im_sex.setVisibility(View.VISIBLE);
            switch (userDao.getGender(getUsername())) {
                case "":
                    im_sex.setVisibility(View.GONE);
                    break;
                case "Female":
                    im_sex.setImageResource(R.drawable.ic_sex_female);
                    break;
                case "Male":
                    im_sex.setImageResource(R.drawable.ic_sex_male);
                    break;
            }
        }else {
            //未登录
            Log.e(TAG, "UnLogin");
            im_profile.setImageResource(R.drawable.default_profile_pic);
            tv_username.setText(getString(R.string.username_unlogin));
            im_sex.setVisibility(View.GONE);
        }


        im_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    startActivity(intent);
            }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });


        tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        tv_myData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), MyDataActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.searching_update, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), R.string.no_update, Toast.LENGTH_SHORT).show();
            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
}