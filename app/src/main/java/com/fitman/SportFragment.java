package com.fitman;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fitman.utils.SharedPreferencesUtils;

import java.text.DecimalFormat;


public class SportFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView tv_distance_num = getActivity().findViewById(R.id.tv_distance_num);
        TextView tv_duration_num = getActivity().findViewById(R.id.tv_duration_num);
        TextView tv_calories_num = getActivity().findViewById(R.id.tv_calories_num);
        TextView tv_pace_num = getActivity().findViewById(R.id.tv_pace_num);

        if(!SharedPreferencesUtils.getParam("duration_record", "null").equals("null") &&
        SharedPreferencesUtils.getParam("run_record_username", "").equals(getUsername())) {
            tv_duration_num.setText(SharedPreferencesUtils.getParam("duration_record", "00:00:00"));
            tv_distance_num.setText(SharedPreferencesUtils.getParam("distance_record", "0.00"));
            tv_calories_num.setText(SharedPreferencesUtils.getParam("calories_record", "0"));
            tv_pace_num.setText(SharedPreferencesUtils.getParam("pace_record", "0.00"));
        }

        Button btn_start = getActivity().findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RunActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport, container, false);
    }
}