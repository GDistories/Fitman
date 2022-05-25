package com.fitman;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MyFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        TextView tv_setting = getActivity().findViewById(R.id.txt_setting);
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        TextView tv_update = getActivity().findViewById(R.id.txt_update);
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.searching_update, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), R.string.no_update, Toast.LENGTH_SHORT).show();
            }
        });

        TextView tv_about = getActivity().findViewById(R.id.txt_about);
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